package Entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ModelTest {
	
	//methods to test
	//getPuzzle
	@Test
	public void testGetPuzzle() {
		Puzzle p = new Puzzle(null, null);
		Model m = new Model(p);
		assertTrue(m.getPuzzle() instanceof Puzzle);
		//test the getPuzzle method to make sure it returns a puzzle
	}
}
