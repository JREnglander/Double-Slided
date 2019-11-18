package Entity;

public class Location {

	int col;// range 0-2
	int row;// range 0-2

	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
	
	public void shiftLeft() {
		if(this.col != 0) {
			this.col -= 1;
		}
	}
	public void shiftRight() {
		if(this.col != 2) {
			this.col += 1;
		}
	}
	public void shiftUp() {
		if(this.row != 0) {
			this.row -= 1;
		}
	}
	public void shiftDown() {
		if(this.row != 2) {
			this.row += 1;
		}
	}	
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Location)) {
			return false;
		}
		
		Location l = (Location) o;
		
		return l.col == this.col && l.row == this.row;	
	}
}