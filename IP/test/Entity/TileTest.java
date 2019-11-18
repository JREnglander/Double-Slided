package Entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TileTest {
	
	//list of methods to test
	
	Tile t = new Tile("1", "2", 0, new Location(1,1), false, false, false);
	//getKey
	@Test
	public void testGetKey() {
		assertEquals(0, t.getKey());
	}
	//flip
	@Test
	public void testFlipFtoT() {
		assertFalse(t.flipState);
		t.flip();
		assertTrue(t.flipState);
	}
	//tests that the move counter increments everytime the tile flips
	@Test
	public void testFlipTtoF() {
		assertEquals(0, t.flipCounter);
		t.flip();
		assertTrue(t.flipState);
		assertEquals(1, t.flipCounter);
		t.flip();
		assertFalse(t.flipState);
		assertEquals(2, t.flipCounter);
	}
	//isSelected
	@Test
	public void testIsSelected() {
		assertFalse(t.isSelected());
	}
	//isEmpty
	@Test
	public void testIsEmpty() {
		assertFalse(t.isEmpty());
	}
	//changeSelectedState
	@Test
	public void testChangeSelectedStateFtoT() {
		t.changeSelectedState();
		assertTrue(t.Selected);
	}
	@Test
	public void testChangeSelectedStateTtoF() {
		t.changeSelectedState();
		t.changeSelectedState();
		assertFalse(t.Selected);
	}
	//flipped
	@Test
	public void testFlippedF() {
		assertEquals("1",t.flipped());
	}
	@Test
	public void testFlippedT() {
		t.flip();
		assertEquals("2",t.flipped());
	}
	//isFlipped
	@Test
	public void testIsFlipped() {
		assertEquals(false, t.isFlipped());
	}
	//getLoc
	@Test
	public void testGetLoc() {
		assertEquals(new Location(1,1), t.getLoc());
	}
	//moveTileRight
	@Test
	public void testMoveRight() {
		t.moveTileRight();
		assertEquals(new Location(1,2), t.getLoc());
	}
	@Test
	public void testMoveRightCol2() {
		t.moveTileRight();
		t.moveTileRight();
		assertEquals(new Location(1,2), t.getLoc());
	}
	//moveTileLeft
	@Test
	public void testMoveLeft() {
		t.moveTileLeft();
		assertEquals(new Location(1,0), t.getLoc());
	}
	@Test
	public void testMoveRLeftCol0() {
		t.moveTileLeft();
		t.moveTileLeft();
		assertEquals(new Location(1,0), t.getLoc());
	}
	//moveTileUp
	@Test
	public void testMoveUp() {
		t.moveTileUp();
		assertEquals(new Location(0,1), t.getLoc());
	}
	@Test
	public void testMoveUpRow0() {
		t.moveTileUp();
		t.moveTileUp();
		assertEquals(new Location(0,1), t.getLoc());
	}
	//moveTileDown
	@Test
	public void testMoveDown() {
		t.moveTileDown();
		assertEquals(new Location(2,1), t.getLoc());
	}
	@Test
	public void testMoveDownRow2() {
		t.moveTileDown();
		t.moveTileDown();
		assertEquals(new Location(2,1), t.getLoc());
	}
	//equals
	@Test
	public void testTileEquals() {
		Tile t1 = new Tile("1", "4", 1, new Location(1,1), false, false, false);
		Tile t2 = new Tile("4", "1", 2, new Location(1,2), true, true, false);
		Tile t3 = new Tile("1", "4", 1, new Location(1,1), false, false, false);
		Integer i = 0;
		//two move counters both ways
		assertTrue(t1.equals(t3));
		assertTrue(t3.equals(t1));
		//the same object
		assertTrue(t2.equals(t2));
		//two different typed objects
		assertFalse(t2.equals(i));
		//two move counters with different num of moves
		assertFalse(t1.equals(t2));
	}
	//makeFull
	@Test
	public void testMakeFull() {
		t.makeFull();
		assertTrue(t.isEmpty);
		t.makeFull();
		assertFalse(t.isEmpty);
	}
}
