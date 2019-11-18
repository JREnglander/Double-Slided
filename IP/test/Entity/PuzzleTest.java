package Entity;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PuzzleTest {
	
	
	//mock objects
	MoveCounter mc = new MoveCounter();
	// constructor arguements: face, back, key, loc, flipState, selected, isEmpty
	// Tile tile = new Tile("1", "4", 1, new Location(0,0), false, false, false);
	
	Tile[][] ts = new Tile[3][3];
	// all the tiles
	Tile t1 = new Tile("1", "4", 1, new Location(0, 0), false, true, false);
	Tile t2 = new Tile("1", "4", 2, new Location(0, 1), true, false, false);
	Tile t3 = new Tile("3", "2", 3, new Location(0, 2), false, false, false);
	Tile t4 = new Tile("2", "3", 4, new Location(1, 0), false, false, false);
	Tile t5 = new Tile("4", "1", 5, new Location(1, 1), true, false, false);
	Tile t6 = new Tile("4", "1", 6, new Location(1, 2), false, false, false);
	Tile t7 = new Tile("", "", 7, new Location(2, 0), false, false, true);
	Tile t8 = new Tile("2", "3", 8, new Location(2, 1), false, false, false);
	Tile t9 = new Tile("3", "2", 9, new Location(2, 2), false, false, false);
	
	@Before
	public void setup() {
		ts[0][0] = t1;
		ts[0][1] = t2;
		ts[0][2] = t3;
		ts[1][0] = t4;
		ts[1][1] = t5;
		ts[1][2] = t6;
		ts[2][0] = t7;
		ts[2][1] = t8;
		ts[2][2] = t9;
	}
	
	
	Puzzle p = new Puzzle(mc, ts);
	
	
	
	//methods to test
	//getMoveCounter
	@Test
	public void testGetMoveCounter() {
		assertEquals(mc, p.getMoveCounter());
	}
	//getTile
	@Test
	public void testGetTile() {
		assertEquals(t4, p.getTile(1, 0));
	}
	@Test
	public void testGetTileOutOfArray() {
		assertNull(p.getTile(3, 0));
		assertNull(p.getTile(0, 3));
	}
	//getEmptyTile
	@Test
	public void testGetEmptyTile() {
		assertEquals(t7, p.getEmptyTile());
	}
	//getSelectedTile
	@Test
	public void testGetSelectedTile() {
		assertEquals(t1, p.getSelectedTile());//since it is the first tile this might be a fluke
		t1.changeSelectedState();
		t6.changeSelectedState();
		assertEquals(t6, p.getSelectedTile()); //confirms that it can find the tile in the second row third column
		t6.changeSelectedState();
		t8.changeSelectedState();
		assertEquals(t8, p.getSelectedTile()); // confirms it can find a tile in the third row  and second column
	}
	//addTile*
	@Test
	public void testAddTile() {
		Tile[][] t = new Tile[3][3];
		Puzzle p1 = new Puzzle(mc, t);
		p1.addTile(t1);
		p1.addTile(t2);
		p1.addTile(t4);
		assertEquals(t1, p1.getTile(0, 0));
		assertEquals(t2, p1.getTile(0, 1));
		assertEquals(t4, p1.getTile(1, 0));
	}
	//replaceTile*
	//horizontal replace
	@Test 
	public void testReplaceTileH() {
		p.replaceTile(t1, t2);
		assertEquals(t1, p.getTile(0, 1));
		assertEquals(t2, p.getTile(0, 0));
	}
	//vertical replace
	@Test 
	public void testReplaceTileV() {
		p.replaceTile(t1, t4);
		assertEquals(t1, p.getTile(1, 0));
		assertEquals(t4, p.getTile(0, 0));
	}
	//isMovable*
	//isSelectedRightOfEmpty*
	@Test
	public void testisSelectedRightOfEmpty() {
		//t1 is currently selected
		assertFalse(p.isSelectedRightOfEmpty());
		t1.changeSelectedState();
		t2.changeSelectedState();
		//have fault with t2 specifically
		assertFalse(p.isSelectedRightOfEmpty());
		t2.changeSelectedState();
		//a tile that is right of empty tile 
		t8.changeSelectedState();
		assertTrue(p.isSelectedRightOfEmpty());
	}
	//isSelectedLeftOfEmpty*
	@Test
	public void testisSelectedLeftOfEmpty() {
		//t1 is currently selected
		assertFalse(p.isSelectedLeftOfEmpty());
		t1.changeSelectedState();
		//
//		t8.changeSelectedState();
//		assertFalse(p.isSelectedLeftOfEmpty());
//		t8.changeSelectedState();
	}
	@Test
	public void testisSelectedLeftOfEmptyTrue() {
		//a tile that is located left of the empty tile (make new puzzle with a tile left of the empty tile)
		//Note: initial piece orientation puts empty tile on bottom left with means that the emoty tile has no left
		//or down tiles
		Tile t1 = new Tile("1", "4", 1, new Location(0, 0), false, false, false);
		Tile t2 = new Tile("1", "4", 2, new Location(0, 1), true, false, false);
		Tile t3 = new Tile("3", "2", 3, new Location(0, 2), false, false, false);
		Tile t4 = new Tile("2", "3", 4, new Location(1, 0), false, true, false);//selected tile
		Tile t5 = new Tile("4", "1", 5, new Location(1, 1), true, false, true);//"empty" tile
		Tile t6 = new Tile("4", "1", 6, new Location(1, 2), false, false, false);
		Tile t7 = new Tile("", "", 7, new Location(2, 0), false, false, false);
		Tile t8 = new Tile("2", "3", 8, new Location(2, 1), false, false, false);
		Tile t9 = new Tile("3", "2", 9, new Location(2, 2), false, false, false);
		Tile[][] ts2 = new Tile[3][3];
		Puzzle p2 = new Puzzle(mc,ts2);
		p2.addTile(t1);
		p2.addTile(t2);
		p2.addTile(t3);
		p2.addTile(t4);
		p2.addTile(t5);
		p2.addTile(t6);
		p2.addTile(t7);
		p2.addTile(t8);
		p2.addTile(t8);
		p2.addTile(t9);
		assertTrue(p2.isSelectedLeftOfEmpty());//when the selected tile is left of empty
		t4.changeSelectedState();
		t6.changeSelectedState();
		assertFalse(p.isSelectedLeftOfEmpty());//when the selected tile is not in the col but is in the row
		t6.changeSelectedState();
		t1.changeSelectedState();
		assertFalse(p.isSelectedLeftOfEmpty());//when selected tile is in right col but wrong row
		
	}
	//isSelectedAboveEmpty*
	@Test
	public void testisSelectedAboveEmpty() {
		//t1 is currently selected(wrong row right column)
		assertFalse(p.isSelectedAboveEmpty());
		t1.changeSelectedState();
		t2.changeSelectedState();
		//have fault with t2 specifically (wrong row right column)
		assertFalse(p.isSelectedAboveEmpty());
		t2.changeSelectedState();
		//a tile that is above of empty tile (true)
		t4.changeSelectedState();
		assertTrue(p.isSelectedAboveEmpty());
		t4.changeSelectedState();
		t5.changeSelectedState();//wrong column 
		assertFalse(p.isSelectedAboveEmpty());
	}
	//isSelectedBelowEmpty*
	@Test
	public void testisSelectedBelowEmpty() {
		//t1 is currently selected
		assertFalse(p.isSelectedBelowEmpty());
		t1.changeSelectedState();
		//a tile that is adjacent to the empty tile but in the wrong direction
		t5.changeSelectedState();
		assertFalse(p.isSelectedBelowEmpty());
	}
	
	@Test
	public void testisSelectedBelowEmptyTrue() {
		Tile t1 = new Tile("1", "4", 1, new Location(0, 0), false, false, false);
		Tile t2 = new Tile("1", "4", 2, new Location(0, 1), true, false, false);
		Tile t3 = new Tile("3", "2", 3, new Location(0, 2), false, false, false);
		Tile t4 = new Tile("2", "3", 4, new Location(1, 0), false, false, false);
		Tile t5 = new Tile("4", "1", 5, new Location(1, 1), true, false, true);//"empty" tile
		Tile t6 = new Tile("4", "1", 6, new Location(1, 2), false, false, false);
		Tile t7 = new Tile("", "", 7, new Location(2, 0), false, false, false);
		Tile t8 = new Tile("2", "3", 8, new Location(2, 1), false, true, false);//selected tile
		Tile t9 = new Tile("3", "2", 9, new Location(2, 2), false, false, false);
		Tile[][] ts2 = new Tile[3][3];
		Puzzle p2 = new Puzzle(mc,ts2);
		p2.addTile(t1);
		p2.addTile(t2);
		p2.addTile(t3);
		p2.addTile(t4);
		p2.addTile(t5);
		p2.addTile(t6);
		p2.addTile(t7);
		p2.addTile(t8);
		p2.addTile(t8);
		p2.addTile(t9);
		assertTrue(p2.isSelectedBelowEmpty());
		t8.changeSelectedState();
		t7.changeSelectedState();
		assertFalse(p.isSelectedBelowEmpty());
	}
	//all these tests below are in relation to the selected tile
	//getLeftTile*
	@Test
	public void testGetLeftTile() {
		assertEquals(t1,p.getLeftTile());//when there is no left tile
		t1.changeSelectedState();
		t2.changeSelectedState();
		assertEquals(t1, p.getLeftTile());//when there is a left tile
	}
	//getRightTile*
	@Test
	public void testGetRightTile() {
		assertEquals(t2, p.getRightTile());//when there is a right tile
		t1.changeSelectedState();
		t3.changeSelectedState();
		assertEquals(t3, p.getRightTile());//when there is no right tile
	}
	//getAboveTile*
	@Test
	public void testGetAboveTile() {
		assertEquals(t1, p.getAboveTile());//when there is no up tile
		t1.changeSelectedState();
		t4.changeSelectedState();
		assertEquals(t1, p.getAboveTile());//when there is up tile
	}
	//getBelowTile*
	@Test
	public void testGetBelowTile() {
		assertEquals(t4, p.getBelowTile());//when there is a down tile
		t1.changeSelectedState();
		t7.changeSelectedState();
		assertEquals(t7,p.getBelowTile());//when there is no down tile
	}
	
	//isMovable
	@Test
	public void testIsMovable() {
		assertFalse(p.isMovable());//when tile is not next to empty tile
		t1.changeSelectedState();
		t4.changeSelectedState();
		assertTrue(p.isMovable());//when tile is movable in first direction(up)
		t4.changeSelectedState();
		t8.changeSelectedState();
		assertTrue(p.isMovable());//when tile is movable in second direction(right)
	}
	@Test
	public void testIsMovableP2() {
		Tile t1 = new Tile("1", "4", 1, new Location(0, 0), false, false, false);
		Tile t2 = new Tile("1", "4", 2, new Location(0, 1), true, false, false);
		Tile t3 = new Tile("3", "2", 3, new Location(0, 2), false, false, false);
		Tile t4 = new Tile("2", "3", 4, new Location(1, 0), false, true, false);//selected tile
		Tile t5 = new Tile("4", "1", 5, new Location(1, 1), true, false, true);//"empty" tile
		Tile t6 = new Tile("4", "1", 6, new Location(1, 2), false, false, false);
		Tile t7 = new Tile("", "", 7, new Location(2, 0), false, false, false);
		Tile t8 = new Tile("2", "3", 8, new Location(2, 1), false, false, false);
		Tile t9 = new Tile("3", "2", 9, new Location(2, 2), false, false, false);
		Tile[][] ts2 = new Tile[3][3];
		Puzzle p2 = new Puzzle(mc,ts2);
		p2.addTile(t1);
		p2.addTile(t2);
		p2.addTile(t3);
		p2.addTile(t4);
		p2.addTile(t5);
		p2.addTile(t6);
		p2.addTile(t7);
		p2.addTile(t8);
		p2.addTile(t8);
		p2.addTile(t9);
		
		assertTrue(p2.isMovable());//when selected tile is movable in third direction(left)
		t4.changeSelectedState();
		t8.changeSelectedState();
		assertTrue(p2.isMovable());//when selected tile is movable in fourth direction(down)
		
	}
	//getTiles
	@Test
	public void testGetTiles() {
		assertArrayEquals(ts,p.getTiles());
	}
	
	//stopGame
	@Test
	public void testStopGame() {
		p.stopGame();
		assertNull(p.getEmptyTile());
	}
	
	//Lost
	@Test
	public void testLost() {
		assertFalse(p.Lost);
		p.Lost();
		assertTrue(p.Lost);
	}
	//Win
	@Test
	public void testWin() {
		assertFalse(p.Won);
		p.Won();
		assertTrue(p.Won);
	}
	//getTiles
	@Test
	public void testGetTileByKey() {
		assertEquals(t9, p.getTileByKey(9));
		assertNotEquals(t8, p.getTileByKey(5));
	}
	//hasWon
	@Test
	public void testHasWon() {
		assertFalse(p.hasWon());		
	}
	//hasLost
	@Test
	public void testHasLost() {
		assertFalse(p.hasLost());
	}
	//equals (for tile arrays)
	@Test
	public void testEquals() {
		Tile [][] ts2 = new Tile[3][3];
		ts2[0][0] = t1;
		ts2[0][1] = t2;
		ts2[0][2] = t3;
		ts2[1][0] = t4;
		ts2[1][1] = t5;
		ts2[1][2] = t6;
		ts2[2][0] = t7;
		ts2[2][1] = t8;
		ts2[2][2] = t9;
		assertArrayEquals(ts,ts2);
	}
}
