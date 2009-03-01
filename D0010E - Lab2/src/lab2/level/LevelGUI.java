package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {

	private Level lv;
	private Display d;
	private int doorSize = 10;
	private int playerSize = 10;
	private int displaySize = 600;

	public LevelGUI(Level level, String name) {

		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: You should change 200 to a value
		// depending on the size of the level
		d = new Display(lv, displaySize, displaySize);

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(100, 100);
		frame.setVisible(true);
		lv.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}

	private class Display extends JPanel {

		public Display(Level fp, int x, int y) {

			addKeyListener(new Listener());

			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(x + 20, y + 20));
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Room Ri : lv.roomVector) {
				g.setColor(Ri.floorColor);
				g.fillRect(Ri.x0, Ri.y0, Ri.deltaX, Ri.deltaY);
				g.setColor(Color.BLACK);
				g.drawRect(Ri.x0, Ri.y0, Ri.deltaX, Ri.deltaY);

				// Draw North door
				if (!(Ri.northRoom == Ri)) {
					g.drawLine(Ri.northDoorX, Ri.northDoorY,
							Ri.northRoom.southDoorX, Ri.northRoom.southDoorY
									+ doorSize / 2);
					g.setColor(Color.GREEN);
					g.fillOval(Ri.northDoorX - doorSize / 2, Ri.northDoorY
							- doorSize / 2, doorSize, doorSize);
					g.setColor(Color.BLACK);
					g.drawOval(Ri.northDoorX - doorSize / 2, Ri.northDoorY
							- doorSize / 2, doorSize, doorSize);
				} else {
					g.setColor(Color.BLACK);
					g.fillOval(Ri.northDoorX - doorSize / 2, Ri.northDoorY
							- doorSize / 2, doorSize, doorSize);
				}

				// Draw East door
				if (!(Ri.eastRoom == Ri)) {
					g.drawLine(Ri.eastDoorX, Ri.eastDoorY,
							Ri.eastRoom.westDoorX - doorSize / 2,
							Ri.eastRoom.westDoorY);
					g.setColor(Color.GREEN);
					g.fillOval(Ri.eastDoorX - doorSize / 2, Ri.eastDoorY
							- doorSize / 2, doorSize, doorSize);
					g.setColor(Color.BLACK);
					g.drawOval(Ri.eastDoorX - doorSize / 2, Ri.eastDoorY
							- doorSize / 2, doorSize, doorSize);
				} else {
					g.setColor(Color.BLACK);
					g.fillOval(Ri.eastDoorX - doorSize / 2, Ri.eastDoorY
							- doorSize / 2, doorSize, doorSize);
				}

				// Draw South door
				if (!(Ri.southRoom == Ri)) {
					g.drawLine(Ri.southDoorX, Ri.southDoorY,
							Ri.southRoom.northDoorX, Ri.southRoom.northDoorY
									- doorSize / 2);
					g.setColor(Color.GREEN);
					g.fillOval(Ri.southDoorX - doorSize / 2, Ri.southDoorY
							- doorSize / 2, doorSize, doorSize);
					g.setColor(Color.BLACK);
					g.drawOval(Ri.southDoorX - doorSize / 2, Ri.southDoorY
							- doorSize / 2, doorSize, doorSize);
				} else {
					g.setColor(Color.BLACK);
					g.fillOval(Ri.southDoorX - doorSize / 2, Ri.southDoorY
							- doorSize / 2, doorSize, doorSize);
				}

				// Draw West door
				if (!(Ri.westRoom == Ri)) {
					g.drawLine(Ri.westDoorX, Ri.westDoorY,
							Ri.westRoom.eastDoorX + doorSize / 2,
							Ri.westRoom.eastDoorY);
					g.setColor(Color.GREEN);
					g.fillOval(Ri.westDoorX - doorSize / 2, Ri.westDoorY
							- doorSize / 2, doorSize, doorSize);
					g.setColor(Color.BLACK);
					g.drawOval(Ri.westDoorX - doorSize / 2, Ri.westDoorY
							- doorSize / 2, doorSize, doorSize);
				} else {
					g.setColor(Color.BLACK);
					g.fillOval(Ri.westDoorX - doorSize / 2, Ri.westDoorY
							- doorSize / 2, doorSize, doorSize);
				}
				g.setColor(Color.YELLOW);
				g.fillOval(lv.youAreHere.northDoorX - playerSize / 2,
						lv.youAreHere.westDoorY - playerSize / 2, playerSize,
						playerSize);

			}

		}

		private class Listener implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent event) {

				switch (event.getKeyChar()) {
				case 'w':
					if (lv.youAreHere != lv.youAreHere.northRoom) {
						lv.youAreHere = lv.youAreHere.northRoom;
						lv.flytta();
					}
					break;
				case 'd':
					if (lv.youAreHere != lv.youAreHere.eastRoom) {
						lv.youAreHere = lv.youAreHere.eastRoom;
						lv.flytta();
					}
					break;
				case 's':
					if (lv.youAreHere != lv.youAreHere.southRoom) {
						lv.youAreHere = lv.youAreHere.southRoom;
						lv.flytta();
					}
					break;
				case 'a':
					if (lv.youAreHere != lv.youAreHere.westRoom) {
						lv.youAreHere = lv.youAreHere.westRoom;
						lv.flytta();
					}
					break;
				default:
					break;
				}
			}
		}

	}

}
