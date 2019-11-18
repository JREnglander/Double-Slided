package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
	//methods to test
	
	//getCol
	@Test
	public void testGetCol() {
		Location l = new Location(1,2);
		assertEquals(2, l.getCol(), 0);
	}
	//getRow
	@Test
	public void testGetRow() {
		Location l = new Location(0,2);
		assertEquals(0, l.getRow(), 0);
	}
	//what happens if the desired direction to shift is not in the array?
	//shiftLeft*
	@Test
	public void testPossibleShiftLeft() {
		//a shift left with a tile that can shift left
		Location l = new Location(1,1);
		l.shiftLeft();
		assertEquals(0,l.col,0);
	}
	@Test
	public void testNotPossibleShiftLeft() {
		Location l = new Location(1,0);
		l.shiftLeft();
		assertEquals(0, l.col,0);
	}
	//shiftRight*
	@Test
	public void testPossibleShiftRight() {
		//a shift left with a tile that can shift left
		Location l = new Location(1,1);
		l.shiftRight();
		assertEquals(2,l.col,0);
	}
	
	@Test
	public void testNotPossibleShiftRight() {
		Location l = new Location(1,2);
		l.shiftRight();
		assertEquals(2, l.col,0);
	}
	//shiftUp*
	@Test
	public void testPossibleShiftUp() {
		Location l = new Location(1,1);
		l.shiftUp();
		assertEquals(0, l.row,0);
	}
	@Test
	public void testNotPossibleShiftUp() {
		Location l = new Location(0,1);
		l.shiftUp();
		assertEquals(0,l.row,0);
	}
	//shiftDown*
	@Test
	public void testPossibleShiftDown() {
		Location l = new Location(1,1);
		l.shiftDown();
		assertEquals(2, l.row,0);
	}
	@Test
	public void testNotPossibleShiftDown() {
		Location l = new Location(2,1);
		l.shiftDown();
		assertEquals(2,l.row,0);
	}
	//equals
	@Test
	public void testEquals() {
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,0);
		assertTrue(l1.equals(l2));
	}
	@Test
	public void testEqualsSameObject() {
		Location l1 = new Location(0,0);
		assertTrue(l1.equals(l1));
	}
	@Test
	public void testEqualsDiffClasses() {
		Location l1 = new Location(0,0);
		Model m = new Model(null);
		assertFalse(l1.equals(m));
	}
	@Test
	public void testEqualsDiffCols() {
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		assertFalse(l1.equals(l2));
	}
	@Test
	public void testEqualsDiffRows() {
		Location l1 = new Location(0,0);
		Location l2 = new Location(1,0);
		assertFalse(l1.equals(l2));
	}
}
