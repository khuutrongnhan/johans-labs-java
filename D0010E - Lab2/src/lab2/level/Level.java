
package lab2.level;

import java.util.Observable;
import java.util.Vector;


/**
 * @author Johan Strom
 * @author Afshin Aresh
 *
 */
public class Level extends Observable {

	Vector<Room> roomVector = new Vector<Room>();
	Room youAreHere;
	Boolean levelClosed = false; 
	
	/** Place room in level
	 * @param r The room to place
	 * @param x Upper left x-coord
	 * @param y Upper left y-coord
	 * @return false if room overlaps with already placed room
	 */
	public boolean place(Room r, int x, int y)  {
		
		if(levelClosed) return false;
		
		r.setCoordinates(x, y);
		
		if(roomVector.isEmpty()) {
			roomVector.add(r);
			return true;
		}
		else
		{
			for(Room Ri : roomVector){
				if(r.overlap(Ri)) return false;
			}
			roomVector.add(r);
			return true;
		}
		
		
}
	void flytta() {
		setChanged();
		notifyObservers();
	}	
	
	public void firstLocation(Room r) {
		youAreHere = r;
		levelClosed = true;
	}
	
}
