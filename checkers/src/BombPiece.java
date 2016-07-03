/*BombPiece.java*/

/**
 * Represents a BombPiece ins Checkers61bl
 * 
 * @author
 */

public class BombPiece extends Piece {

	/**
	 * Define any variables associated with a BombPiece object here. These
	 * variables MUST be private or package private.
	 */

	/**
	 * Constructs a new BombPiece
	 * 
	 * @param side
	 *            what side this BombPiece is on
	 * @param b
	 *            Board that this BombPiece belongs to
	 */
	public BombPiece(int side, Board b) {
		super(side, b);
	}

	@Override
	public int type() {
		return 1;
	}

	@Override
	public void explode(int x, int y) {
		if (getBoard().pieceAt(x - 1, y - 1) != null) {
			getBoard().pieceAt(x - 1, y - 1).getBlownUp(x - 1, y - 1);
		}
		if (getBoard().pieceAt(x + 1, y - 1) != null) {
			getBoard().pieceAt(x + 1, y - 1).getBlownUp(x + 1, y - 1);
		}
		if (getBoard().pieceAt(x - 1, y + 1) != null) {
			getBoard().pieceAt(x - 1, y + 1).getBlownUp(x - 1, y + 1);
		}
		if (getBoard().pieceAt(x + 1, y + 1) != null) {
			getBoard().pieceAt(x + 1, y + 1).getBlownUp(x + 1, y + 1);
		}
		getBoard().remove(x, y);
	}
	// getBoard().remove(x, y);
}
