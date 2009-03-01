package lab4.gui;
import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;
import lab4.data.GameGrid.squareState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JTextField;
import javax.swing.SpringLayout;

import javax.swing.JLabel;

/*
 * The GUI class
 * @author Johan Stršm
 * @author Afshin Aresh
 */

public class GomokuGUI implements Observer, MouseListener, ActionListener{

	static int nrOfGUIs = 0;
	private GomokuClient client;
	private GomokuGameState gamestate;
	private JFrame window;
	private JButton connectButton, newGameButton, disconnectButton;
	private JLabel messageLabel;
	private GameGrid gg;
	private ConnectionWindow cw;
	

	private int paddingSize = 10;
	private int nrOfSquares = 19;
	private int margin = 11;
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		
		// Create a window and make it a container 
		nrOfGUIs++;
		JFrame window = new JFrame("Gomoku Game Client (Listening on port 440"+nrOfGUIs +")");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension d = new Dimension((nrOfSquares * GamePanel.UNIT_SIZE) + paddingSize * margin, (nrOfSquares * GamePanel.UNIT_SIZE) + paddingSize * margin);
		window.setSize(d);
		window.setMinimumSize(d);
		window.setPreferredSize(d);
	    // set window as a container
	    Container content = window.getContentPane();
	    content.setBackground(Color.white);
	    SpringLayout layout = new SpringLayout();
	    content.setLayout(layout);
	    
	    // Create a GameGrid and add to window
	    gg = g.getGameGrid();
	    gg.clearGrid();
	    GamePanel gp = new GamePanel(gg);
	    gp.addMouseListener(this);
	    content.add(gp);
	    
	    // Create 3 buttons and add them
	    connectButton = new JButton("Connect");
	    connectButton.addActionListener(this);
	    newGameButton = new JButton("New Game");
	    newGameButton.addActionListener(this);
	    disconnectButton = new JButton("Disconnect");
	    disconnectButton.addActionListener(this);
	    
	    // Disable newGameButton and disconnectButton
	    newGameButton.setEnabled(false);
		disconnectButton.setEnabled(false);
	    
	    content.add(connectButton);
	    content.add(newGameButton);
	    content.add(disconnectButton);
	    
	    // Create message label and add it
	    messageLabel = new JLabel("Welcome to Gomoku!");
	    //messageLabel.setOpaque(true);
	    content.add(messageLabel);
	    
	    // Set the required layout
	    layout.putConstraint(SpringLayout.WEST, gp, paddingSize, SpringLayout.WEST, content);
	    layout.putConstraint(SpringLayout.NORTH, gp, paddingSize, SpringLayout.NORTH, content);
	    
	    layout.putConstraint(SpringLayout.NORTH, connectButton, paddingSize, SpringLayout.SOUTH, gp);
	    layout.putConstraint(SpringLayout.NORTH, newGameButton, paddingSize, SpringLayout.SOUTH, gp);
	    layout.putConstraint(SpringLayout.NORTH, disconnectButton, paddingSize, SpringLayout.SOUTH, gp);
	    
	    layout.putConstraint(SpringLayout.WEST, messageLabel, paddingSize, SpringLayout.WEST, content);
	    layout.putConstraint(SpringLayout.NORTH, messageLabel, paddingSize, SpringLayout.SOUTH, connectButton);
	    
	    
	    layout.putConstraint(SpringLayout.WEST, connectButton, paddingSize, SpringLayout.WEST, content);
	    layout.putConstraint(SpringLayout.WEST, newGameButton, paddingSize, SpringLayout.EAST, connectButton);
	    layout.putConstraint(SpringLayout.WEST, disconnectButton, paddingSize, SpringLayout.EAST, newGameButton); 
	    window.pack();
	    window.setVisible(true);
		
	    
		this.client = c;
		this.gamestate = g;
		
		client.addObserver(this);
		gamestate.addObserver(this);
	}
	
	public void update(Observable arg0, Object arg1) {
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}


	public void mouseClicked(MouseEvent e) {
		int xMouse = e.getX();
		int yMouse = e.getY();
		
		if ((0 <= xMouse) && (xMouse <= (nrOfSquares * GamePanel.UNIT_SIZE))) {
			if ((0 <= yMouse) && (yMouse <= (nrOfSquares * GamePanel.UNIT_SIZE))) {
				gamestate.move(xMouse / GamePanel.UNIT_SIZE, yMouse/GamePanel.UNIT_SIZE);
			}
		}
	}
			
		
		
		



	// Ignore
	public void mouseEntered(MouseEvent e) {}

	// Ignore
	public void mouseExited(MouseEvent e) {}

	// Ignore
	public void mousePressed(MouseEvent e) {}

	// Ignore
	public void mouseReleased(MouseEvent e) {}


	public void actionPerformed(ActionEvent e) {
		String op = e.getActionCommand();
		if(op.equals("Connect")){
			new ConnectionWindow(client);
		}
		else if(op.equals("New Game")){
			gamestate.newGame(); 
		}
		else if(op.equals("Disconnect")){
			gamestate.disconnect();
		}
		
	}
	
}
