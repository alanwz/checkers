/*Piece.java*/

/**
 * Represents a Normal Piece in Checkers61bl
 * 
 * @author
 */

public class Piece {
	private int side;
	private Board b;
	private boolean moved;
	private boolean selected;
	private boolean isKing;
	private int xPos;
	private int yPos;
	private int hasCaptured;

	/**
	 * Define any variables associated with a Piece object here. These variables
	 * MUST be private or package private.
	 */

	/**
	 * Returns the side that the piece is on
	 * 
	 * @return 0 if the piece is fire and 1 if the piece is water
	 */

	public int side() {
		return this.side;
	}

	/**
	 * Getter and Setter methods for X and Y pos.
	 */
	public void setX(int x) {
		this.xPos = x;
	}

	public int getX() {
		return this.xPos;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	public int getY() {
		return this.yPos;
	}

	/**
	 * Getter and Setter methods for move, selected, isKing, and board pos.
	 */

	public boolean getMoved() {
		return this.moved;
	}

	public void setMoved(boolean x) {
		if (x == true) {
			this.moved = true;
		} else {
			this.moved = false;
		}
	}

	public boolean getSelected() {
		return this.selected;
	}

	public void setSelected(boolean x) {
		if (x == true) {
			this.selected = true;
		} else {
			this.selected = false;
		}
	}

	public void setKing(boolean x) {
		if (x == true) {
			isKing = true;
		} else {
			isKing = false;
		}
	}

	public boolean isKing() {
		if (isKing == true) {
			return true;
		} else {
			return false;
		}
	}

	public Board getBoard() {
		return this.b;
	}

	public int type() {
		return 0;
	}

	/**
	 * Initializes a Piece
	 * 
	 * @param side
	 *            The side of the Piece
	 * @param b
	 *            The Board the Piece is on
	 */
	Piece(int side, Board b) {
		this.side = side;
		this.b = b;

	}

	/**
	 * Destroys the piece at x, y. ShieldPieces do not blow up
	 * 
	 * @param x
	 *            The x position of Piece to destroy
	 * @param y
	 *            The y position of Piece to destroy
	 */
	void getBlownUp(int x, int y) {
		if (this.type() == 2) {
			return;
		} else {
			getBoard().remove(x, y);
		}
	}

	/**
	 * Does nothing. For bombs, destroys pieces adjacent to it
	 * 
	 * @param x
	 *            The x position of the Piece that will explode
	 * @param y
	 *            The y position of the Piece that will explode
	 */
	void explode(int x, int y) {
		return;
	}

	/**
	 * Signals that this Piece has begun to capture (as in it captured a Piece)
	 */
	void startCapturing() {
		hasCaptured = 1;
	}

	/**
	 * Returns whether or not this piece has captured this turn
	 * 
	 * @return true if the Piece has captured
	 */
	public boolean hasCaptured() {
		if (this.hasCaptured == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Resets the Piece for future turns
	 */
	public void finishCapturing() {
		this.hasCaptured = 0;
	}

}