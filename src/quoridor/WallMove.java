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
}
