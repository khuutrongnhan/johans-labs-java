package lab4.data;

import java.util.Observable;

/** Represents the 2-d game grid
 * @author Johan Stršm
 * @author Afshin Aresh
 * 
 */

public class GameGrid extends Observable {

	public enum squareState {
		EMPTY, ME, OTHER
	};

	private int INROW = 5;
	private int size;
	squareState board[][];

	/**
	 * Constructor
	 * 
	 * @param size
	 *            The width/height of the game grid
	 */
	public GameGrid(int size) {
		super();
		this.size = size;
		board = new squareState[size][size];
	}

	/**
	 * Reads a location of the grid
	 * 
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 * @return the value of the specified location
	 */
	public squareState getLocation(int x, int y) {
		return board[x][y];
	}

	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Enters a move in the game grid
	 * 
	 * @param x
	 *            the x position
	 * @param y
	 *            the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, squareState player) {
		if (((0 <= x) && (x < size)) && ((0 <= y) && (y < size))) {
			if (board[x][y] == squareState.EMPTY) {
				board[x][y] = player;
				this.setChanged();
				this.notifyObservers();
				return true;
				
			}
			return false;
		}
		return false;
	}

	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid() {
		for(int x=0;x<size;x++){
			for(int y=0; y<size;y++){
				board[x][y]= squareState.EMPTY;
			}
			this.setChanged();
			this.notifyObservers();
		}
		
		
	}

	/**
	 * Check if a player has INROW horizontally
	 * 
	 * @param player
	 * @return true if player has INROW in row, false otherwise.
	 */
	private boolean isWinnerHoriz(squareState player) {
		int nrInRow = 0;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (board[x][y] == player) {
					nrInRow++;
					if (nrInRow == INROW){
						return true;
					}
						
				} else
					nrInRow = 0;
			}
			nrInRow = 0;
		}
		return false;
	}

	/**
	 * Check if a player has INROW in row Verically
	 * 
	 * @param player
	 *            the player to check for
	 * @return true if player has INROW in row, false otherwise
	 */
	private boolean isWinnerVert(squareState player) {
		int nrInRow = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == player) {
					nrInRow++;
					if (nrInRow == INROW)
						return true;
				} else
					nrInRow = 0;
			}
			nrInRow = 0;
		}
		return false;
	}

	/**
	 * Check if a player has INROW diagonally between (x0,y0) and (x1,y1)
	 * 
	 * @param x0
	 *            x-coord start
	 * @param y0
	 *            y-coord start
	 * @param x1
	 *            x-coord stop
	 * @param y1
	 *            y-coord stop
	 * @param player
	 * @return true if player has NROW in row diagonally between (x0,y0) and
	 *         (x1,y1)
	 */
	private boolean isWinnerDiagUp(int x0, int y0, int x1, int y1,
			squareState player) {
		int y = y0;
		int nrInRow = 0;
		for (int x = x0; x <= x1; x++) {
			if (board[x][y] == player) {
				nrInRow++;
				if (nrInRow == INROW)
					return true;
			} else
				nrInRow = 0;
			y++;
		}
		return false;
	}

	/**
	 * Check if a player has INROW diagonally between (x0,y0) and (x1,y1)
	 * 
	 * @param x0
	 *            x-coord start
	 * @param y0
	 *            y-coord start
	 * @param x1
	 *            x-coord stop
	 * @param y1
	 *            y-coord stop
	 * @param player
	 * @return true if player has NROW in row diagonally between (x0,y0) and
	 *         (x1,y1)
	 */
	private boolean isWinnerDiagDown(int x0, int y0, int x1, int y1,
			squareState player) {
		int y = y0;
		int nrInRow = 0;
		for (int x = x0; x <= x1; x++) {
			if (board[x][y] == player) {
				nrInRow++;
				if (nrInRow == INROW)
					return true;
			} else
				nrInRow = 0;
			y--;
		}
		return false;
	}

	/**
	 * Check if a player has INROW in diagonally
	 * 
	 * @param player
	 *            the player to check for
	 * @return true if player has INROW in row, false otherwise
	 */
	private boolean isWinnerDiagUp(squareState player) {
		int x0, y0, x1, y1;

		// loop 1 of 2
		x0 = 0;
		y1 = size - 1;
		x1 = size - 1;
		for (int y = 0; y <= size-1; y++) {
			if (isWinnerDiagUp(x0, y, x1, y1, player))
				return true;
			x1--;
		}

		// loop 2 of 2
		y0 = 0;
		x1 = size - 1;
		y1 = size - 1;
		for (int x = 0; x <= size-1; x++) {
			if (isWinnerDiagUp(x, y0, x1, y1, player))
				return true;
			y1--;
		}
		return false;
	}

	/**
	 * Check if a player has INROW in diagonally
	 * 
	 * @param player
	 *            the player to check for
	 * @return true if player has INROW in row, false otherwise
	 */
	private boolean isWinnerDiagDown(squareState player) {
		int x0, y0, x1, y1;

		// loop 1 of 2
		x0 = 0;
		y0 = size - 1;
		x1 = size - 1;
		for (int y = 0; y <= size - 1; y++) {
			if (isWinnerDiagDown(x0, y0, x1, y, player))
				return true;
			x0++;
		}

		// loop 2 of 2
		y0 = size - 1;
		x0 = 0;
		y1 = size - 1;
		for (int x = size - 1; x >= 0; x--) {
			if (isWinnerDiagDown(x0, y0, x, y1, player))
				return true;
			y0--;
		}
		return false;
	}

	/**
	 * Check if a player has INROW in row
	 * 
	 * @param player
	 *            the player to check for
	 * @return true if player has INROW in row, false otherwise
	 */
	public boolean isWinner(squareState player) {
		if (isWinnerHoriz(player) || isWinnerVert(player)
				|| isWinnerDiagUp(player) || isWinnerDiagDown(player)) {
			return true;
		} else {
			return false;
		}
	}

	/** Class tests
	 * @param args
	 */
	public static void main(String[] args) {
		GameGrid spelPlan = new GameGrid(19);
		spelPlan.clearGrid();
		spelPlan.move(0, 4, squareState.ME);
		spelPlan.move(1, 3, squareState.ME);
		spelPlan.move(2, 2, squareState.ME);
		spelPlan.move(3, 1, squareState.ME);
		spelPlan.move(4, 0, squareState.ME);
		
		
		if(spelPlan.isWinner(squareState.ME)){
			System.out.println("Du vann!");
		}
		else{
			System.out.println("Du vann inte!");
		}
			
	}
}
