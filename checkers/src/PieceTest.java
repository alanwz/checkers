import static org.junit.Assert.*;

import org.junit.Test;


public class PieceTest {

	@Test
	public void propertiesTest() {
		Board b = new Board(true);
		Piece test = new Piece(0, b);
		assertTrue(test.side() == 0);
		assertTrue(test.type() == 0);
		assertTrue(test.getX() == 0);
		assertTrue(test.getY() == 0);		
	}
	public void type() {
		Board b = new Board(false);
		assertTrue(b.pieceAt(1, 1).getX() == 1);
		assertTrue(b.pieceAt(1, 1).getY() == 1);
	}

}
