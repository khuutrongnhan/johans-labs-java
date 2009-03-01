package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

/** Place rooms in level, connect them and initiate GUI.
 * @author Johan Stršm 
 * @author Afshin Aresh
 */
public class Driver {
	
	private int rL = 100; 
	private int rH = 100;
	private int rA = 20;
	
	/** This is where most stuff happens.
	 * 
	 */
	public void run() {
	// Create rooms
	Room r1 = new Room(rL,rH,Color.blue);
	Room r2 = new Room(rL,rH,Color.blue);
	Room r3 = new Room(rL,rH,Color.blue);
	Room r4 = new Room(rL,rH,Color.blue);
	// Connect rooms
	r1.connectEastTo(r2); // r1.connectSouthTo(r3);
	r2.connectWestTo(r1); r2.connectSouthTo(r4);
	r3.connectNorthTo(r1); r3.connectEastTo(r4);
	r4.connectNorthTo(r2); r4.connectWestTo(r3);
	
	// Create level
	Level level1 = new Level();
	
	// Place rooms with error checking
	if(!(level1.place(r1, rA, rA))) {
		System.out.println("Wrong placement of room");
		return;
	}
	if(!(level1.place(r2, rL+2*rA, rA))) {
		System.out.println("Wrong placement of room");
		return;
	}	
	if(!(level1.place(r3, rA, rL+2*rA))) {
		System.out.println("Wrong placement of room");
		return;
	}	
	if(!(level1.place(r4, 2*rA+rL, 2*rA+rL))) {
		System.out.println("Wrong placement of room");
		return;
	}	
		
	// Set start room
	level1.firstLocation(r1);
	
	// Start GUI
	new LevelGUI(level1, "Level 1: Green door == Exit, Black door == No Exit that way!");
	}

}