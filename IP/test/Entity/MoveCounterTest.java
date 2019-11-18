package Entity;

import org.junit.Test;

import static org.junit.Assert.*;


public class MoveCounterTest {
	
	//methods to test
	//getNumMoves
	@Test
	public void testGetNumMoves() {
		MoveCounter mc = new MoveCounter();
		assertEquals(0, mc.getNumMoves(), 0);
	}
	//IncrementCounter
	@Test
	public void testIncrementCounter() {
		MoveCounter mc  = new MoveCounter();
		mc.IncrementMoveCounter();
		assertEquals(1, mc.numOfMoves, 0);
	}
	
	//Equals
	@Test
	public void testMCEquals() {
		MoveCounter mc1 = new MoveCounter();
		MoveCounter mc2 = new MoveCounter();
		MoveCounter mc3 = new MoveCounter();
		mc3.IncrementMoveCounter();
		Integer i = 0;
		//two move counters both ways
		assertTrue(mc1.equals(mc2));
		assertTrue(mc2.equals(mc1));
		//the same object
		assertTrue(mc2.equals(mc2));
		//two different typed objects
		assertFalse(mc2.equals(i));
		//two move counters with different num of moves
		assertFalse(mc1.equals(mc3));
	}

}
