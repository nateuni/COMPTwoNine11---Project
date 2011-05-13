
package quoridor;

/**
 * Represents a move where a wall is placed on the board.
 * @author Team Stump
 */
public class WallMove extends Move {
	public Wall wall;
	
	public WallMove(Wall wall) {
		this.wall = wall;
	}
	
	public Wall getWall() {
		return wall; 
	}
}