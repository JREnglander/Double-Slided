package Controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Boundary.PuzzleView;
import Entity.Location;
import Entity.Model;
import Entity.MoveCounter;
import Entity.Puzzle;
import Entity.Tile;

public class ResetControllerTest {
	
	//mock objects that show the real environment
	Tile t1 = new Tile("1", "4", 1, new Location(0, 0), false, true, false);
	Tile t2 = new Tile("1", "4", 2, new Location(0, 1), true, false, false);
	Tile t3 = new Tile("3", "2", 3, new Location(0, 2), false, false, false);
	Tile t4 = new Tile("2", "3", 4, new Location(1, 0), false, false, false);
	Tile t5 = new Tile("4", "1", 5, new Location(1, 1), true, false, false);
	Tile t6 = new Tile("4", "1", 6, new Location(1, 2), false, false, false);
	Tile t7 = new Tile("", "", 7, new Location(2, 0), false, false, true);
	Tile t8 = new Tile("2", "3", 8, new Location(2, 1), false, false, false);
	Tile t9 = new Tile("3", "2", 9, new Location(2, 2), false, false, false);
	
	Tile[][] ts;
	Tile[][] tsorig;
	MoveCounter mcDefault;
	Puzzle pDefault;
	Model mDefault;
	PuzzleView pv;
	
	@Before
	public void setUp() {
		mcDefault = new MoveCounter();
		ts = new Tile[3][3];
		pDefault = new Puzzle(mcDefault, ts);
		mDefault = new Model(pDefault);
		pDefault.addTile(t1);
		pDefault.addTile(t2);
		pDefault.addTile(t3);
		pDefault.addTile(t4);
		pDefault.addTile(t5);
		pDefault.addTile(t6);
		pDefault.addTile(t7);
		pDefault.addTile(t8);
		pDefault.addTile(t9);
		
		//making 2d array of tiles that does not change
		tsorig = new Tile[3][3];
		tsorig[0][0] = t1;
		tsorig[0][1] = t2;
		tsorig[0][2] = t3;
		tsorig[1][0] = t4;
		tsorig[1][1] = t5;
		tsorig[1][2] = t6;
		tsorig[2][0] = t7;
		tsorig[2][1] = t8;
		tsorig[2][2] = t9;
		
		pv = new PuzzleView(mDefault);
		pv.setVisible(true);
	}
	@After
	public void tearDown() {
		pv.dispose();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testResetOneMove() {
		new moveCursorController(mDefault, pv).moveCursorDown();
		new FlipController(mDefault, pv).flip();
		assertNotEquals(ts, tsorig);//comparing the puzzle that has been modified to the puzzle that hasnt before reseting
		new ResetController(mDefault, pv).resetPuzzle();
		assertEquals(ts, tsorig);//comparing the puzzle that has been modified to the puzzle that hasnt after reseting
	}

	@Test
	public void testResetWin() {
		new FlipController(mDefault, pv).flip();
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorRight();
		new FlipController(mDefault, pv).flip();//1
		new moveCursorController(mDefault, pv).moveCursorRight();
		new FlipController(mDefault, pv).flip();//2
		new moveCursorController(mDefault, pv).moveCursorUp();
		new FlipController(mDefault, pv).flip();//3
		new moveCursorController(mDefault, pv).moveCursorLeft();//move the location to continue the win
		new FlipController(mDefault, pv).flip();//4
		new moveCursorController(mDefault, pv).moveCursorLeft();
		new FlipController(mDefault, pv).flip();//5
		new moveCursorController(mDefault, pv).moveCursorUp();
		new FlipController(mDefault, pv).flip();//6
		new moveCursorController(mDefault, pv).moveCursorRight();
		new FlipController(mDefault, pv).flip();//7
		new moveCursorController(mDefault, pv).moveCursorDown();
		new FlipController(mDefault, pv).flip();//8
		assertNotEquals(ts, tsorig);//comparing the puzzle that has been modified to the puzzle that hasnt before reseting
		new ResetController(mDefault, pv).resetPuzzle();
		assertArrayEquals(ts, tsorig);//comparing the puzzle that has been modified to the puzzle that hasnt after reseting
	}

}
