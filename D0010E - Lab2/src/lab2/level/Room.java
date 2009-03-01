package lab2.level;

import java.awt.Color;

/**
 * @author Johan Strom
 * @author Afshin Aresh
 */
public class Room {
	Room northRoom, eastRoom, westRoom, southRoom;
	int deltaX, deltaY; // Room length and height.
	int x0, y0, x1, y1; // Room coordinates
	int northDoorX, northDoorY;
	int eastDoorX, eastDoorY;
	int southDoorX, southDoorY;
	int westDoorX, westDoorY;
	Color floorColor;

	/**
	 * @param dx
	 *            Room length
	 * @param dy
	 *            Room width
	 * @param color
	 *            Room floor color
	 */
	public Room(int dx, int dy, Color color) {
		deltaX = dx;
		deltaY = dy;
		floorColor = color;
		northRoom = southRoom = westRoom = eastRoom = this;
	}

	/**
	 * Connect the north side of the room to another room
	 * 
	 * @param r
	 *            The room we connect to
	 */
	public void connectNorthTo(Room r) {
		northRoom = r;
	}

	/**
	 * Connect the east side of the room to another room
	 * 
	 * @param r
	 *            The room we connect to
	 */
	public void connectEastTo(Room r) {
		eastRoom = r;
	}

	/**
	 * Connect the south side of the room to another room
	 * 
	 * @param r
	 *            The room we connect to
	 */
	public void connectSouthTo(Room r) {
		southRoom = r;
	}

	/**
	 * Connect the west side of the room to another room
	 * 
	 * @param r
	 *            The room we connect to
	 */
	public void connectWestTo(Room r) {
		westRoom = r;
	}

	// Sets x0,y0,x1 and y1 coordinates for the rectangle
	// (x0,y0) is upper left corner. (x1,y1) is lower right corner.

	/**
	 * Calculates the room's coordinates and the door's coordinates
	 * 
	 * @param x
	 *            upper left corner x-coordinate
	 * @param y
	 *            upper left corner y-coordinate
	 */
	void setCoordinates(int x, int y) {
		x0 = x;
		y0 = y;
		x1 = x0 + deltaX;
		y1 = y0 + deltaY;

		northDoorX = x0 + deltaX / 2;
		northDoorY = y0;

		eastDoorX = x1;
		eastDoorY = y0 + deltaY / 2;

		southDoorX = x0 + deltaX / 2;
		southDoorY = y1;

		westDoorX = x0;
		westDoorY = y0 + deltaY / 2;

	}

	/**
	 * Checks that rooms doesn't overlap
	 * 
	 * @param otherRoom
	 *            The already placed room
	 * @return True if it overlaps
	 */
	public boolean overlap(Room otherRoom) {

		// return true;
		if (((otherRoom.x0 < x0) && (otherRoom.x1 < x0))
				|| ((otherRoom.x0 > x1) && (otherRoom.x1 > x1)))
			return false;
		if (((otherRoom.y0 < y0) && (otherRoom.y1 < y0))
				|| ((otherRoom.y0 > y1) && (otherRoom.y1 > y1)))
			return false;
		else
			return true;
	}

}
