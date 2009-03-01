package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;
import lab4.data.GameGrid.squareState;

/**
 * A panel providing a graphical view of the game board
 * @author Johan Stršm
 * @author Afshin Aresh
 */
public class GamePanel extends JPanel implements Observer{

	static int UNIT_SIZE = 20;
	private GameGrid grid;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	
	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		int[] gridPos =  new int[2];
		gridPos[0] = x/UNIT_SIZE; // Note: Java rounds the result down!
		gridPos[1] = y/UNIT_SIZE;
		return gridPos;
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	/** Draw the GameGrid and placed pieces.
	 * @param g The JFrame to draw on
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int gridSize = grid.getSize();
		for(int x = 0; x <= (gridSize * UNIT_SIZE); x+= UNIT_SIZE){
			g.drawLine(x, 0, x, (gridSize * UNIT_SIZE));
		}
		for(int y = 0; y <= (gridSize * UNIT_SIZE); y+= UNIT_SIZE){
			g.drawLine(0, y, (gridSize * UNIT_SIZE),y);
		}
		for(int x = 0; x < gridSize; x++){
			for (int y = 0; y < gridSize; y++){
				if(grid.getLocation(x,y) != squareState.EMPTY){
					if(grid.getLocation(x,y) == squareState.ME){
						g.setColor(Color.GREEN);
					}
					else{
						g.setColor(Color.RED);
					}
					g.fillOval(UNIT_SIZE*x+1, UNIT_SIZE*y+1, UNIT_SIZE-2, UNIT_SIZE-2);
				}
				
			}
		}
	}
}
	
	

