package Entity;

public class MoveCounter {

	int numOfMoves;

	public MoveCounter() {
		this.numOfMoves = 0;
	}

	public void IncrementMoveCounter() {
		this.numOfMoves++;
	}

	public int getNumMoves() {
		return this.numOfMoves;
	}
	
	public void resetNumMoves() {
		this.numOfMoves = 0;
	}
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof MoveCounter)) {
			return false;
		}
		
		MoveCounter mc = (MoveCounter) o;
		
		return mc.numOfMoves == this.numOfMoves;	
	}
}