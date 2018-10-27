package org.millardps.ConnectFour;

public class Coordinate {
	private int x;
	private int y;
	private String coord;
	private boolean played;
	private boolean player;
	private int size;
	
	public Coordinate(int xx, int yy, int s) {
		x = xx + 1;
		y = yy + 1;
		size = s;
		played = false;
		coord = setCoord();
	}

	/**
	 * Sets spacing for coordinates.
	 * @return Coordinates with the proper spacing.
	 */
	public String setCoord() {
		if (this.size < 10) {
			return ("[" + this.x + "," + this.y + "]");
		}
		else if (this.size < 100) {
			if (this.x >= 10 && this.y >= 10) {
				return ("[" + this.x + "," + this.y + "]");
			}
			else if (this.x < 10 && this.y < 10) {
				return ("[ " + this.x + "," + this.y + " ]");
			}
			else if (this.x >= 10) {
				return ("[" + this.x + "," + this.y + " ]");
			}
			else {
				return ("[ " + this.x + "," + this.y + "]");
			}
		}
		return null;
	}
	
	/**
	 * Sets spacing for coordinates.
	 * @param play
	 * @return Coordinates with the proper spacing.
	 */
	public String setCoord(boolean play) {
		String mover;
		if (play) {
			mover = "X";
		}
		else {
			mover = "O";
		}
		if (this.size < 10) {
			return ("[ " + mover + " ]");
		}
		else if (this.size < 100) {
			return ("[  " + mover + "  ]");
		}
		return null;
	}
	
	/**
	 * Prints coordinates.
	 */
	public void printCoord() {
		//if (this.played) {
			System.out.print(this.coord);
//		}
//		else {
//			System.out.print(coord);
//		}
	}
	
	/**
	 * Sets player, played and coord variables.
	 * @param play
	 */
	public void makeMove(boolean play) {
		this.player = play;
		this.played = true;
		this.coord = setCoord(play);
		System.out.println(x);
		System.out.println(y);
		//printCoord();
	}
	
	/**
	 * Returns the coordinates.
	 * @return coord value of the Coordinate.
	 */
	public String getCoord() {
		return this.coord; 
	}
	
}