package Entity;

public class Puzzle {

	MoveCounter moves;
	Tile[][] tiles;
	boolean Won;
	boolean Lost;

	public Puzzle(MoveCounter moves, Tile[][] tiles) {
		this.moves = new MoveCounter();
		this.tiles = tiles;
		this.Won = false;
		this.Lost = false;
	}

	public MoveCounter getMoveCounter() {
		return moves;
	}

	// used in tile panel for making every tile
	public Tile getTile(int row, int col) {
		try {
			return tiles[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public Tile getTileByKey(int key) {
		Tile rt = null;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getKey() == key) {
					rt = tiles[i][j];
				}
			}
		}
		return rt;
	}

	public Tile getEmptyTile() {
		Tile rt = null;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].isEmpty()) {
					rt = tiles[i][j];
				}
			}
		}
		return rt;
	}

	public Tile getSelectedTile() {
		Tile rt = null;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].isSelected()) {
					rt = tiles[i][j];
				}
			}
		}
		return rt;
	}

	public void addTile(Tile t) {
		int col = t.getLoc().getCol();
		int row = t.getLoc().getRow();
		tiles[row][col] = t;
	}

	public void replaceTile(Tile t1, Tile t2) {
		tiles[t1.loc.row][t1.loc.col] = t2;
		tiles[t2.loc.row][t2.loc.col] = t1;
		
		Location t1orig = t1.loc;
		Location t2orig = t2.loc;
		
		t1.loc = t2orig;
		t2.loc = t1orig;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
	//to compare two tile arrays for resetting
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof Tile[][])) {
			return false;
		}
		
		Tile[][] t = (Tile[][]) o;
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t.length; j++){
				if(t[i][j].key == this.tiles[i][j].key) {
					continue;
				}else return false;
			}
		}
		return true;
	}

	public boolean isMovable() {
		return isSelectedRightOfEmpty() || isSelectedLeftOfEmpty() || isSelectedAboveEmpty() || isSelectedBelowEmpty();
	}

	public boolean isSelectedRightOfEmpty() {
		return getSelectedTile().loc.col == (getEmptyTile().loc.col + 1)
				&& getSelectedTile().loc.row == getEmptyTile().loc.row;
	}

	public boolean isSelectedLeftOfEmpty() {
		return getSelectedTile().loc.col == (getEmptyTile().loc.col - 1)
				&& getSelectedTile().loc.row == getEmptyTile().loc.row;
	}

	public boolean isSelectedAboveEmpty() {
		return getSelectedTile().loc.row == (getEmptyTile().loc.row - 1)
				&& getSelectedTile().loc.col == getEmptyTile().loc.col;
	}

	public boolean isSelectedBelowEmpty() {
		return getSelectedTile().loc.row == (getEmptyTile().loc.row + 1)
				&& getSelectedTile().loc.col == getEmptyTile().loc.col;
	}

	// used for the controller
	public Tile getLeftTile() {
		try {
			return tiles[getSelectedTile().getLoc().getRow()][getSelectedTile().getLoc().getCol() - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return getSelectedTile();
		}
	}

	public Tile getRightTile() {
		try {
			return tiles[getSelectedTile().getLoc().getRow()][getSelectedTile().getLoc().getCol() + 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return getSelectedTile();
		}
	}

	public Tile getAboveTile() {
		try {
			return tiles[getSelectedTile().getLoc().getRow() - 1][getSelectedTile().getLoc().getCol()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return getSelectedTile();
		}
	}

	public Tile getBelowTile() {
		try {
			return tiles[getSelectedTile().getLoc().getRow() + 1][getSelectedTile().getLoc().getCol()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return getSelectedTile();
		}
	}
	
	//when the player wins or loses they cannot move any tiles anymore so this function will do that
	//by making the empty tile "not empty" to the other tiles
	public void stopGame() {
		getEmptyTile().makeFull();
	}
	
	public void Won() {
		this.Won = true;
	}
	public boolean hasWon() {
		return this.Won;
	}

	public void Lost() {
		this.Lost = true;
	}
	public boolean hasLost() {
		return this.Lost;
	}
	
	public void resetToEndState() {
		if(Won || Lost) {
			for(int i = 2; i < 10; i++) {
				getTileByKey(i).changeSelectedState();
			}
		}
		else {
			getSelectedTile().changeSelectedState();
			getTile(0, 0).changeSelectedState();
		}
		//make the puzzle neither won or lost again
		this.Won = false;
		this.Lost = false;
	}

}
