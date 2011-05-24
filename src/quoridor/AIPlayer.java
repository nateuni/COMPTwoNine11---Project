package quoridor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AIPlayer extends Player {

	protected abstract int distanceWeight();
	protected abstract int wallsLeftWeight();

	/**Constructor
	 * @param playerNumber delegates player1 or player2 default construction
	 */
	public AIPlayer(int playerNumber) {
		super(playerNumber);
	}
	
	public List<Move> allMoves(Board board) {
		LinkedList<Move> potentialMoves = new LinkedList<Move>();
		if (board.getSpace(this).getUp() != null) {
			potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getUp()));
			if (board.getSpace(this).getUp().getRight() != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getUp().getRight()));
			if (board.getSpace(this).getUp().getUp()    != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getUp().getUp()));
		}
		if (board.getSpace(this).getRight() != null) {
			potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getRight()));
			if (board.getSpace(this).getRight().getDown()  != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getDown()));
			if (board.getSpace(this).getRight().getRight() != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getRight()));
		}
		if (board.getSpace(this).getDown() != null) {
			if (board.getSpace(this).getDown().getLeft() != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getDown().getLeft()));
			if (board.getSpace(this).getDown().getDown() != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getDown().getDown()));
			potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getDown()));
		}
		if (board.getSpace(this).getLeft() != null) {
			if (board.getSpace(this).getLeft().getUp()   != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getLeft().getUp()));
			if (board.getSpace(this).getLeft().getLeft() != null) potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getLeft().getLeft()));
			potentialMoves.add(new MovementMove(board.getSpace(this), board.getSpace(this).getLeft()));
		}
		if (board.hasWallsLeft(this)) {
			for (int col = 1; col <= 8; col++) {
				for (int row = 1; row <= 8; row++) {
					potentialMoves.add(new WallMove(new Wall(new Space(col, row), true)));
					potentialMoves.add(new WallMove(new Wall(new Space(col, row), false)));
				}
			}
		}
		return potentialMoves;
	}
	
	@SuppressWarnings("static-access")
	protected int awesomeness(Board board, Player player) {
		int awesomeness = 0;
		
		awesomeness += board.getWallsLeft(player) * wallsLeftWeight();
		
		Space otherSide;
		if (player.equals(board.players._1())) otherSide = board.player2Start;
		else otherSide = board.player1Start;
		ArrayList<Space> exits = new ArrayList<Space>();
		for (int i = 1; i <=9; i++) exits.add(new Space(i, otherSide.row()));
		board.graph.fillNodeDistances(exits);
		int distance = board.graph.node[board.getSpace(player).row()-1][board.getSpace(player).col()-1].distanceToExit;
		awesomeness -= distance * distanceWeight();
		
		if (player.equals(board.winner())) awesomeness = Integer.MAX_VALUE/2;
		
		return awesomeness;
	}
}
