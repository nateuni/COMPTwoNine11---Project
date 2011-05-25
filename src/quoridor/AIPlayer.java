package quoridor;

import java.util.ArrayList;

public abstract class AIPlayer extends Player {

	protected abstract int distanceWeight();
	protected abstract int wallsLeftWeight();

	/**Constructor
	 * @param playerNumber delegates player1 or player2 default construction
	 */
	public AIPlayer(int playerNumber) {
		super(playerNumber);
	}
	
	protected int evaluate(Board board) {
		return awesomeness(board, board.players._1()) - awesomeness(board, board.players._2());
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
