import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

	@Test
//	public void () {
//		fail("Not yet implemented");
//	}
	
	public void pieceAtTest() {
		Board b = new Board(false);
		assertTrue((b.pieceAt(0, 0)).type() == 0);
		assertEquals((b.pieceAt(8, 8)), null);
	}
	
	@Test
	public void placeTest() {
		Board b = new Board(false);
		Piece bomb = new BombPiece(0, b);
		b.place(bomb, 0, 0);
		assertTrue((b.pieceAt(0, 0)).type() == 1); // checks for replacement
		b.place(bomb, 8, 8);	
	}
	
	@Test
	public void removeTest() {
		Board b = new Board(false);
		assertTrue((b.remove(0, 0)) instanceof Piece); //check standard removal
		assertTrue((b.remove(8, 8)) == null); //check out of bounds call
		assertTrue((b.remove(0, 1)) == null); //check empty spot
	}
	
	public void removeTest2() {
		Board b = new Board(true);
		Piece p = new Piece(0, b);
		b.place(p, 0, 0);
		b.move(0, 0, 1, 1);
		assertTrue((b.pieceAt(0, 0)) == null);
	}
	
	@Test
	public void winnerTest() {
		Board b = new Board(true);
		assertTrue(b.winner() == "Tie");
	}
	
	public void winnerTestRed() {
		Board b = new Board(false);
		Piece p = new Piece(0, b);
		b.place(p, 0, 0);
		assertTrue(b.winner() == "Fire");
	}
	
	public void winnerTestBlue() {
		Board b = new Board(false);
		Piece p = new Piece(1, b);
		b.place(p, 0, 0);
		assertTrue(b.winner() == "Water");
	}
	
		@Test
		public void tests() {
			Board b = new Board(true);
			Piece waterBombOne = new BombPiece(1, b);
			Piece firePawnOne = new Piece(0, b);
			Piece waterShield = new ShieldPiece(1, b);
			Piece waterBombTwo = new BombPiece(1, b);
			Piece firePawnTwo = new Piece(0, b);
			Piece fireBomb = new BombPiece(0, b);
			Piece firePawnThree = new Piece(0, b);
			Piece fireShield = new ShieldPiece(0, b);
	
			b.place(waterBombOne, 1, 5);       // Captures fire pawn and explodes.
			b.place(firePawnOne, 2, 4);        // Captured by fire pawn.
			b.place(waterShield, 4, 2);        // Performs capture, kinged, and captures again.
			b.place(waterBombTwo, 4, 4);       // Exploded by first bomb, does not explode.
			b.place(firePawnTwo, 5, 3);        // Is not affected.
			b.place(fireBomb, 3, 1);           // Captured by shield piece.
			b.place(firePawnThree, 1, 1);      // Second capture of shield piece.
			b.place(fireShield, 2, 2);         // Not affected by water bomb explosion.

			b.move(1, 5, 3, 3);
			assertTrue(b.pieceAt(1, 5) == null);
			assertTrue(b.pieceAt(2, 4) == null);
			assertTrue(b.pieceAt(3, 3) == null);
			assertTrue(b.pieceAt(4, 4) == null);
			assertTrue(b.pieceAt(4, 2) == waterShield);
			assertTrue(b.pieceAt(2, 2) == fireShield);
	
			b.move(4, 2, 2, 0);
			assertTrue(b.pieceAt(4, 2) == null);
			assertTrue(b.pieceAt(3, 1) == null);
			Piece kingedShield = b.pieceAt(2, 0);
			System.out.println(b.pieceAt(2, 0).side());
			assertTrue(kingedShield.isKing());
			
			b.move(2, 0, 0, 2);
			assertTrue(b.pieceAt(2, 0) == null);
			assertTrue(b.pieceAt(1, 1) == null);
		}

}
