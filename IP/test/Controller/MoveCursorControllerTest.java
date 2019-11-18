package Controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Boundary.PuzzleView;
import Entity.Location;
import Entity.Model;
import Entity.MoveCounter;
import Entity.Puzzle;
import Entity.Tile;

public class MoveCursorControllerTest {
	
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
	public void testMoveCursorAllDirections() {
		assertEquals(t1, pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorLeft();//when there is no left tile
		assertEquals(t1, pDefault.getSelectedTile());
		mDefault.getPuzzle().getTileByKey(1).changeSelectedState();
		mDefault.getPuzzle().getTileByKey(5).changeSelectedState();
		new moveCursorController(mDefault, pv).moveCursorLeft();//when there is a left tile
		assertEquals(t4, pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorRight();//when there is a right tile
		assertEquals(t5, pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorRight();
		new moveCursorController(mDefault, pv).moveCursorRight();//when there is no right tile
		assertEquals(t6,pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorUp();//when there is a up tile
		assertEquals(t3, pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorUp();//when there is no up tile
		assertEquals(t3, pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorDown();//when there is a down tile
		assertEquals(t6, pDefault.getSelectedTile());
		new moveCursorController(mDefault, pv).moveCursorDown();
		new moveCursorController(mDefault, pv).moveCursorDown();//when there is no down tile
		assertEquals(t9, pDefault.getSelectedTile());
	}

}
