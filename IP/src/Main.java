import Boundary.PuzzleView;
import Entity.Location;
import Entity.Model;
import Entity.MoveCounter;
import Entity.Puzzle;
import Entity.Tile;

public class Main {

	public static void main(String[] args) {
		MoveCounter mc = new MoveCounter();
		// constructor arguements: face, back, key, loc, flipState, selected, isEmpty
		// Tile tile = new Tile("1", "4", 1, new Location(0,0), false, false, false);
		
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
		//tile storage
		Tile[][] ts = new Tile[3][3];
		
		Puzzle p = new Puzzle(mc, ts);
		Model m = new Model(p);

		p.addTile(t1); 
		p.addTile(t2);
		p.addTile(t3);
		p.addTile(t4);
		p.addTile(t5);
		p.addTile(t6);
		p.addTile(t7);
		p.addTile(t8);
		p.addTile(t9);
		
		PuzzleView pv = new PuzzleView(m);
		pv.setVisible(true);

	}
}
