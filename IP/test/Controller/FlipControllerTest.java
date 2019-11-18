package Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Boundary.PuzzleView;
import Entity.Location;
import Entity.Model;
import Entity.MoveCounter;
import Entity.Puzzle;
import Entity.Tile;

public class FlipControllerTest {
	
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
		
		pv = new PuzzleView(mDefault);
		pv.setVisible(true);
	}
	@After
	public void tearDown() {
		pv.dispose();
	}
	
	@Test
	public void testFlipControllerWin() {
		//this will test when the player makes the moves to win in this order
		//first test that nothig happens if the tile is not movable
		//t1 for example is not movable in the beginning
		new FlipController(mDefault, pv).flip();
		assertEquals(t1, mDefault.getPuzzle().getTile(0,0));
		assertEquals(0,mDefault.getPuzzle().getMoveCounter().getNumMoves());
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorRight();
		assertEquals(t8, pDefault.getSelectedTile());
		assertEquals(pDefault.getTile(2, 1), pDefault.getSelectedTile());
		new FlipController(mDefault, pv).flip();//1
		assertEquals(1,mDefault.getPuzzle().getMoveCounter().getNumMoves());
		assertEquals(pDefault.getTile(2, 1), pDefault.getSelectedTile());//test that the empty tile is in the new location
		new moveCursorController(mDefault, pv).moveCursorRight();
		assertEquals(t9, pDefault.getSelectedTile());
		new FlipController(mDefault, pv).flip();//2
		assertEquals(pDefault.getTile(2, 2), pDefault.getSelectedTile());//test that the empty tile is in the new location
		new moveCursorController(mDefault, pv).moveCursorUp();
		assertEquals(t6, pDefault.getSelectedTile());
		new FlipController(mDefault, pv).flip();//3
		assertEquals(pDefault.getTile(1, 2), pDefault.getSelectedTile());//test that the empty tile is in the new location
		new moveCursorController(mDefault, pv).moveCursorLeft();//move the location to continue the win
		assertEquals(pDefault.getTile(1, 1), pDefault.getSelectedTile());
		new FlipController(mDefault, pv).flip();//4
		assertEquals(pDefault.getTile(1, 1), pDefault.getSelectedTile());//test that the empty tile is in the new location
		new moveCursorController(mDefault, pv).moveCursorLeft();
		assertEquals(pDefault.getTile(1, 0), pDefault.getSelectedTile());
		new FlipController(mDefault, pv).flip();//5
		assertEquals(pDefault.getTile(1, 0), pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorUp();
		new FlipController(mDefault, pv).flip();//6
		assertEquals(pDefault.getTile(0, 0), pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorRight();
		new FlipController(mDefault, pv).flip();//7
		assertEquals(pDefault.getTile(0, 1), pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorDown();
		new FlipController(mDefault, pv).flip();//8
		assertTrue(pDefault.hasWon());
	}
	@Test
	public void testFlipControllerLost() {
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorRight();
		new FlipController(mDefault, pv).flip();
		new moveCursorController(mDefault, pv).moveCursorRight();
		new FlipController(mDefault, pv).flip();
		new moveCursorController(mDefault, pv).moveCursorUp();
		new FlipController(mDefault, pv).flip();
		new moveCursorController(mDefault, pv).moveCursorUp();
		new FlipController(mDefault, pv).flip();
		new moveCursorController(mDefault, pv).moveCursorLeft();
		new FlipController(mDefault, pv).flip();
		assertTrue(mDefault.getPuzzle().hasLost());
	}

}
