package quoridor;

import java.util.LinkedList;
import java.util.List;

public abstract class AIPlayer extends Player {

	/**Constructor
	 * @param playerNumber delegates player1 or player2 default construction
	 */
	public AIPlayer(int playerNumber) {
		super(playerNumber);
	}
	
	public List<Move> allMoves() {
		LinkedList<Move> potentialMoves = new LinkedList<Move>();
		boolean valid = false;
		if (this.getSpace().getUp()    != null) potentialMoves.add(new MovementMove(this.getSpace(), this.getSpace().getUp()));
		if (this.getSpace().getRight() != null) potentialMoves.add(new MovementMove(this.getSpace(), this.getSpace().getRight()));
		if (this.getSpace().getDown()  != null) potentialMoves.add(new MovementMove(this.getSpace(), this.getSpace().getDown()));
		if (this.getSpace().getLeft()  != null) potentialMoves.add(new MovementMove(this.getSpace(), this.getSpace().getLeft()));
		for (int col = 1; col <= 8; col++) {
			for (int row = 1; row <= 8; row++) {
				potentialMoves.add(new WallMove(new Wall(new Space(col, row), true)));
				potentialMoves.add(new WallMove(new Wall(new Space(col, row), false)));
			}
		}
		return potentialMoves;
	}
}
