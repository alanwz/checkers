/*Board.java*/

/**
 * Represents a Board configuration of a game of Checkers61bl
 * 
 * @author
 */

public class Board {
	private Piece[][] pieces;
	private int player;
	private Piece selectedPiece;
	private boolean hasMoved;

	/**
	 * Define any variables associated with a Board object here. These variables
	 * MUST be private.
	 */

	/**
	 * Constructs a new Board
	 * 
	 * @param shouldBeEmpty
	 *            if true, add no pieces
	 */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false) {
			for (int i = 0; i < 8; i = i + 2) {
				for (int j = 0; j < 8; j++) {
					if (j == 0) {
						Piece pawn = new Piece(0, this);
						this.place(pawn, i, j);
					}
					if (j == 1) {
						Piece shield = new ShieldPiece(0, this);
						this.place(shield, i + 1, j);
					}
					if (j == 2) {
						Piece bomb = new BombPiece(0, this);
						this.place(bomb, i, j);
					}
					if (j == 5) {
						Piece bomb = new BombPiece(1, this);
						this.place(bomb, i + 1, j);
					}
					if (j == 6) {
						Piece shield = new ShieldPiece(1, this);
						this.place(shield, i, j);
					}
					if (j == 7) {
						Piece pawn = new Piece(1, this);
						this.place(pawn, i + 1, j);
					}
				}
			}
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					pieces[i][j] = null;
				}
			}
		}
	}

	private void drawBoard() {
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[0].length; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				// checks for selected piece and colors white
				// ask question regarding when piece should not be highlight
				// white
				if ((selectedPiece != null) && (pieces[i][j] != null)) {
					if ((pieces[i][j].getX() == selectedPiece.getX())
							&& (pieces[i][j].getY() == selectedPiece.getY())) {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
				}
				if (pieces[i][j] != null) {
					if ((pieces[i][j].type() == 0)
							&& (pieces[i][j].side() == 0)) {
						if (pieces[i][j].isKing() == true) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/pawn-fire-crowned.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/pawn-fire.png", 1, 1);
						}
					} else if ((pieces[i][j].type() == 0)
							&& (pieces[i][j].side() == 1)) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/pawn-water.png", 1, 1);
						if (pieces[i][j].isKing() == true) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/pawn-water-crowned.png", 1, 1);
						}
					} else if ((pieces[i][j].type() == 1)
							&& (pieces[i][j].side() == 0)) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/bomb-fire.png", 1, 1);
						if (pieces[i][j].isKing() == true) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/bomb-fire-crowned.png", 1, 1);
						}
					} else if ((pieces[i][j].type() == 1)
							&& (pieces[i][j].side() == 1)) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/bomb-water.png", 1, 1);
						if (pieces[i][j].isKing() == true) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/bomb-water-crowned.png", 1, 1);
						}
					} else if ((pieces[i][j].type() == 2)
							&& (pieces[i][j].side() == 0)) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/shield-fire.png", 1, 1);
						if (pieces[i][j].isKing() == true) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/shield-fire-crowned.png", 1, 1);
						}
					} else if ((pieces[i][j].type() == 2)
							&& (pieces[i][j].side() == 1)) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/shield-water.png", 1, 1);
						if (pieces[i][j].isKing() == true) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/shield-water-crowned.png", 1, 1);
						}
					}
				}
			}
		}
	}

	/**
	 * gets the Piece at coordinates (x, y)
	 * 
	 * @param x
	 *            X-coordinate of Piece to get
	 * @param y
	 *            Y-coordinate of Piece to get
	 * @return the Piece at (x, y)
	 */
	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7)) {
			return null;
		}
		if ((x < 0) || (y < 0)) {
			return null;
		}
		return this.pieces[x][y];
	}

	/**
	 * Places a Piece at coordinate (x, y)
	 * 
	 * @param p
	 *            Piece to place
	 * @param x
	 *            X coordinate of Piece to place
	 * @param y
	 *            Y coordinate of Piece to place
	 */
	public void place(Piece p, int x, int y) {
		if ((x > 7) || (y > 7)) {
			return;
		}
		if (p == null) {
			return;
		}
		if (p.side() == 0) {
			if (y == 7) {
				p.setKing(true);
			}
		}
		if (p.side() == 1) {
			if (y == 0) {
				p.setKing(true);
			}
		}
		pieces[x][y] = p;
		p.setX(x);
		p.setY(y);

	}

	/**
	 * Determines if a Piece can be selected
	 * 
	 * @param x
	 *            X coordinate of Piece
	 * @param y
	 *            Y coordinate of Piece to select
	 * @return true if the Piece can be selected
	 */
	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null) {
			if ((this.player == pieces[x][y].side())
					&& (this.selectedPiece == null)) {
				return true;
			}
			if ((this.player == pieces[x][y].side())
					&& (this.selectedPiece.getMoved() == false)) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((this.selectedPiece != null)
					&& (this.selectedPiece.getMoved() == false)) {
				if ((validMove(selectedPiece, x, y) || (validCapture(
						selectedPiece, x, y)))) {
					return true;
				}
				return false;
			}
			if ((this.selectedPiece != null)
					&& (this.selectedPiece.hasCaptured() == true)) {
				if (validCapture(selectedPiece, x, y)) {
					selectedPiece.setMoved(false);
					return true;
				}
			}
		}
		return false;
	}

	public boolean validCapture(Piece p, int x, int y) {
		if (p.isKing() == false) {
			if (p.side() == 0) {
				if (p.getY() > y) {
					return false;
				}
				if ((pieceAt(p.getX() + 1, p.getY() + 1) != null)) {
					if (pieceAt(p.getX() + 1, p.getY() + 1).side() != p.side()) {
						if (pieceAt(p.getX() + 2, p.getY() + 2) != null) {
							if ((x == p.getX() + 2) && (y == p.getY() + 2)) {
								return false;
							}
						} else if (pieceAt(p.getX() + 2, p.getY() + 2) == null) {
							if ((x == p.getX() + 2) && (y == p.getY() + 2)) {
								return true;
							}
						}
					}
				}
				if ((pieceAt(p.getX() - 1, p.getY() + 1) != null)) {
					if (pieceAt(p.getX() - 1, p.getY() + 1).side() != p.side()) {
						if (pieceAt(p.getX() - 2, p.getY() + 2) != null) {
							if ((x == p.getX() - 2) && (y == p.getY() + 2)) {
								return false;
							}
						} else if (pieceAt(p.getX() - 2, p.getY() + 2) == null) {
							if ((x == p.getX() - 2) && (y == p.getY() + 2)) {
								return true;
							}
						}
					}
				}
			}
			if (p.side() == 1) {
				if (p.getY() < y) {
					return false;
				}
				if ((pieceAt(p.getX() + 1, p.getY() - 1) != null)) {
					if (pieceAt(p.getX() + 1, p.getY() - 1).side() != p.side()) {
						if (pieceAt(p.getX() + 2, p.getY() - 2) != null) {
							if ((x == p.getX() + 2) && (y == p.getY() - 2)) {
								return false;
							}
						} else if (pieceAt(p.getX() + 2, p.getY() - 2) == null) {
							if ((x == p.getX() + 2) && (y == p.getY() - 2)) {
								return true;
							}
						}
					}
				}
				if ((pieceAt(p.getX() - 1, p.getY() - 1) != null)) {
					if (pieceAt(p.getX() - 1, p.getY() - 1).side() != p.side()) {
						if (pieceAt(p.getX() - 2, p.getY() - 2) != null) {
							if ((x == p.getX() - 2) && (y == p.getY() - 2)) {
								return false;
							}
						} else if (pieceAt(p.getX() - 2, p.getY() - 2) == null) {
							if ((x == p.getX() - 2) && (y == p.getY() - 2)) {
								return true;
							}
						}
					}
				}
			}
		}
		if (p.isKing() == true) {
			if ((pieceAt(p.getX() + 1, p.getY() + 1) != null)) {
				if (pieceAt(p.getX() + 1, p.getY() + 1).side() != p.side()) {
					if ((x == p.getX() + 2) && (y == p.getY() + 2)) {
						return true;
					}
				}
			}
			if ((pieceAt(p.getX() - 1, p.getY() + 1) != null)) {
				if (pieceAt(p.getX() - 1, p.getY() + 1).side() != p.side()) {
					if ((x == p.getX() - 2) && (y == p.getY() + 2)) {
						return true;
					}
				}
			}
			if ((pieceAt(p.getX() + 1, p.getY() - 1) != null)) {
				if (pieceAt(p.getX() + 1, p.getY() - 1).side() != p.side()) {
					if ((x == p.getX() + 2) && (y == p.getY() - 2)) {
						return true;
					}
				}
			}
			if ((pieceAt(p.getX() - 1, p.getY() - 1) != null)) {
				if (pieceAt(p.getX() - 1, p.getY() - 1).side() != p.side()) {
					if ((x == p.getX() - 2) && (y == p.getY() - 2)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean validMove(Piece p, int x, int y) {
		if (p.isKing() == false) {
			if (p.side() == 0) {
				if (p.getY() > y) {
					return false;
				}
				if ((pieceAt(p.getX() + 1, p.getY() + 1) != null)) { // if there
																		// is a
																		// piece
																		// existing
																		// in
																		// path
																		// of
																		// movement
					if ((x == p.getX() + 1) && (y == p.getY() + 1)) { // if
																		// selected
																		// square
																		// already
																		// exists
																		// a
																		// piece
						return false;
					}
				}
				if ((pieceAt(p.getX() - 1, p.getY() + 1) != null)) { // if there
																		// is a
																		// piece
																		// existing
																		// in
																		// path
																		// of
																		// movement
					if ((x == p.getX() - 1) && (y == p.getY() + 1)) { // if
																		// selected
																		// square
																		// already
																		// exists
																		// a
																		// piece
						return false;
					}
				}
				if ((x == (p.getX() + 1)) && (y == (p.getY() + 1))) { // the
																		// condition
																		// for
																		// simple
																		// +1
																		// movement
					return true;
				}
				if ((x == (p.getX() - 1)) && (y == (p.getY() + 1))) {
					return true;
				}
				return false;
			}
			if (p.side() == 1) {
				if (p.getY() < y) {
					return false;
				}
				if ((pieceAt(p.getX() + 1, p.getY() - 1) != null)) { // if there
																		// is a
																		// piece
																		// existing
																		// in
																		// path
																		// of
																		// movement
					if ((x == p.getX() + 1) && (y == p.getY() - 1)) { // if
																		// selected
																		// square
																		// already
																		// exists
																		// a
																		// piece
						return false;
					}
				}
				if ((pieceAt(p.getX() - 1, p.getY() - 1) != null)) { // if there
																		// is a
																		// piece
																		// existing
																		// in
																		// path
																		// of
																		// movement
					if ((x == p.getX() - 1) && (y == p.getY() - 1)) { // if
																		// selected
																		// square
																		// already
																		// exists
																		// a
																		// piece
						return false;
					}
				}
				if ((x == p.getX() + 1) && (y == p.getY() - 1)) { // the
																	// condition
																	// for no
																	// piece in
																	// the way
																	// and
																	// simple +1
																	// movement
					return true;
				}
				if ((x == p.getX() - 1) && (y == p.getY() - 1)) {
					return true;
				}
				return false;
			}
		}
		if (p.isKing() == true) { // if isKing == true
			if ((pieceAt(p.getX() + 1, p.getY() + 1) != null)) { // if there is
																	// a piece
																	// existing
																	// in path
																	// of
																	// movement
				if ((x == p.getX() + 1) && (y == p.getY() + 1)) { // if selected
																	// square
																	// already
																	// exists a
																	// piece
					return false;
				}
				else {
					return false;
				}
			}
			if (pieceAt(p.getX() - 1, p.getY() + 1) != null) {
				if ((x == p.getX() - 1) && (y == p.getY() + 1)) {
					return false;
				}
				else {
					return false;
				}
			}
			if ((pieceAt(p.getX() + 1, p.getY() - 1) != null)) { // if there is
																	// a piece
																	// existing
																	// in path
																	// of
																	// movement
				if ((x == p.getX() + 1) && (y == p.getY() - 1)) { // if selected
																	// square
																	// already
																	// exists a
																	// piece
					return false;
				}
				else {
					return false;
				}
			}
			if (pieceAt(p.getX() - 1, p.getY() - 1) != null) {
				if ((x == p.getX() - 1) && (y == p.getY() - 1)) {
					return false;
				}
				else {
					return false;
				}
			}
			if ((x == (p.getX() + 1)) && (y == (p.getY() + 1))) { // the
																	// condition
																	// for
																	// simple +1
																	// movement
				return true;
			}
			if ((x == (p.getX() - 1)) && (y == (p.getY() + 1))) {
				return true;
			}
			if ((x == p.getX() + 1) && (y == p.getY() - 1)) { // the condition
																// for no piece
																// in the way
																// and simple +1
																// movement
				return true;
			}
			if ((x == p.getX() - 1) && (y == p.getY() - 1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Selects a square. If no Piece is active, selects the Piece and makes it
	 * active. If a Piece is active, performs a move if an empty place is
	 * selected. Else, allows you to reselect Pieces
	 * 
	 * @param x
	 *            X coordinate of place to select
	 * @param y
	 *            Y coordinate of place to select
	 */
	public void select(int x, int y) {
		if ((this.selectedPiece == null) && (pieces[x][y] != null)) {
			this.selectedPiece = pieces[x][y];
			return;
		} else if ((this.selectedPiece != null) && (pieces[x][y] == null)) {
			this.move(selectedPiece.getX(), selectedPiece.getY(), x, y);
			return;
		} else if (this.selectedPiece != null) {
			if ((selectedPiece.getX() == x) && (selectedPiece.getY() == y)) {
				return;
			} else {
				this.selectedPiece = null;
				return;
			}
		}
	}

	/**
	 * Moves the active piece to coordinate (x, y)
	 * 
	 * @param p
	 *            Piece to move
	 * @param x1
	 *            Original X coordinate of p
	 * @param y1
	 *            Origin Y coordinate of p
	 * @param x
	 *            X coordinate to move to
	 * @param y
	 *            Y coordinate to move to
	 */
	public void move(int x1, int y1, int x2, int y2) {
		if ((pieces[x1][y1] != null) && (pieces[x2][y2] == null)) {
			Piece toBeMoved;
			toBeMoved = pieces[x1][y1];
			// capture
			if ((Math.abs(x2 - x1) == 2) && (Math.abs(y2 - y1) == 2)) { 
				Piece captured;
				captured = remove(((x1 + x2) / 2), ((y1 + y2) / 2));
				toBeMoved.startCapturing();
				this.place(toBeMoved, x2, y2);
				hasMoved = true;
				toBeMoved.setMoved(true);
				if (toBeMoved.type() == 1) {
					toBeMoved.explode(x2, y2);
				}
				remove(x1, y1);
				return;
			}
			this.place(toBeMoved, x2, y2);
			hasMoved = true;
			toBeMoved.setMoved(true);
			this.remove(x1, y1);
		}
	}

	/**
	 * Removes a Piece at coordinate (x, y)
	 * 
	 * @param x
	 *            X coordinate of Piece to remove
	 * @param y
	 *            Y coordinate of Piece to remove
	 * @return Piece that was removed
	 */
	public Piece remove(int x, int y) {
		if ((x > 7) || (y > 7)) {
			System.out.println("Index out of bounds.");
			return null;
		}
		if ((this.pieceAt(x, y)) == null) {
			System.out.println("There is no piece at " + x + " " + y
					+ " coordinate.");
			return null;
		}
		Piece removed;
		removed = pieces[x][y];
		pieces[x][y] = null;
		return removed;
	}

	/**
	 * Determines if the turn can end
	 * 
	 * @return true if the turn can end
	 */
	public boolean canEndTurn() {
		if (hasMoved == true) {
			return true;
		}
		return false;
	}

	/**
	 * Ends the current turn. Changes the player.
	 */
	public void endTurn() {
		this.player = 1 - this.player;
		hasMoved = false;
		selectedPiece.finishCapturing();
		if (this.selectedPiece != null) {
			this.selectedPiece.setMoved(false);
			this.selectedPiece = null;
		}

	}

	/**
	 * Returns the winner of the game
	 * 
	 * @return The winner of this game
	 */
	public String winner() {
		int redcount = 0;
		int bluecount = 0;
		for (int i = 0; i < 8; i = i + 2) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].side() == 0) {
						redcount += 1;		
					}
					if (pieces[i][j].side() == 1) {
						bluecount += 1;
					}
				}
			}
		}
		if ((redcount == 0) && (bluecount == 0)) {
			return "Tie";
		}
		else if (redcount == 0) {
			return "Water";
		}
		else if (bluecount == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}
	/**
	 * Starts a game
	 */
	public static void main(String[] args) {
		Board newBoard = new Board(true);
		StdDrawPlus.setScale(0, 8);
		while (true) {
			newBoard.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (newBoard.canSelect(x, y)) {
					newBoard.select(x, y);
				}
				// newBoard.pieces[x][y] = side;
			}
			if ((StdDrawPlus.isSpacePressed()) && (newBoard.canEndTurn())) {
				newBoard.endTurn();
			}
			StdDrawPlus.show(10);
		}
	}
}
