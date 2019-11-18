package Entity;


public class Tile {

	String facenum; // the sides of the tile
	String backnum;

	boolean flipState;// dictates which side is facing the player
	int flipCounter; //for resetting

	int key; // starting location location 1-9 where 1 is top left and 9 is
				// bottom right this will also be the key for the hashTable
				// also serves as unique identifier
	Location loc;// this is the actual location in the panel used in 2d array

	boolean Selected;

	boolean isEmpty; // for dealing with empty tile

	public Tile(String facenum, String backnum, int key, Location loc, boolean flipState, boolean Selected,
			boolean isEmpty) {
		this.facenum = facenum;
		this.backnum = backnum;
		this.flipState = flipState;
		this.flipCounter = 0;
		this.key = key;
		this.loc = loc;
		this.Selected = Selected;
		this.isEmpty = isEmpty;
	}

	public int getKey() {
		return key;
	}

	public void flip() {// changes the boolean from true to false and vice versa
		flipState = !flipState;
		flipCounter++;
	}
	public void resetFlipState() {//for reseting the tile to origional flip state
		if(!(flipCounter % 2 == 0)) {//if the total number of times a tile has been flipped is odd a 
			this.flip();		//t the time the player wants to reset this tile will need to be flipped again to be showing the origonal side
		}
		

	}
	public boolean isSelected() {
		return Selected;
	}

	// this will be in the controller to unselect a tile and select another when
	// moving the "cursor"
	public void changeSelectedState() {
		Selected = !Selected;
	}

	public String flipped() {
		if (flipState) {
			return backnum;
		}
		return facenum;
	}

	public boolean isFlipped() {
		return flipState;
	}

	public Location getLoc() {
		return loc;
	}

	public boolean isEmpty() {
		return isEmpty;
	} 
	
	//used in controller to move the tiles in the correct direction
	//might be redundant because these are just methods that call other functions
	public void moveTileRight() {
		this.loc.shiftRight();
	}
	
	public void moveTileLeft() {
		this.loc.shiftLeft();
	}
	
	public void moveTileUp() {
		this.loc.shiftUp();
	}
	
	public void moveTileDown() {
		this.loc.shiftDown();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof Tile)) {
			return false;
		}
		Tile t = (Tile) o;
		return t.key == this.key;
	}

	public void makeFull() {
		this.isEmpty = !isEmpty;
	}
}