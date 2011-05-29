package quoridor;

/**
 * Represents a move where a wall is placed on the board.
 * @author Team Stump
 */
public class WallMove extends Move {

	private Wall wall;
	
	public WallMove(Wall wall) {
		this.wall = wall;
	}
	
	public Wall wall() {
		return wall;
	}
	
	@Override
	public String toString(){
		return this.wall.toOriginalString();
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof WallMove)) {
			return false;
		}
		WallMove other = (WallMove) obj;
		return (this.wall().equals(other.wall()));	
	}
}