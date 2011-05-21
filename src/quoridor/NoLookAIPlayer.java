package quoridor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An AI player that picks a good move without looking ahead
 * @author Team Stump
 */
public class NoLookAIPlayer extends AIPlayer {
	
	// Weights for the heuristics
	private final int DISTANCE_WEIGHT = 5;
	private final int WALLSLEFT_WEIGHT = 5;

	public NoLookAIPlayer(int playerNumber) {
		super(playerNumber);
	}

	/**
	 * This doesn't work yet, because when trying a MovementMove, the player gets moved on all the boards
	 * TODO: Fix that.
	 * @see quoridor.Player#getMove(quoridor.Board)
	 */
	public Move getMove(Board board) {
		Move chosenMove = null;
		Board newBoard;
		List<Move> potentialMoves = allMoves();
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				move.awesomeness = awesomeness(board, this) - awesomeness(board, board.players.other(this));
			}
			catch (Exception e) {
				potentialMoves.remove(move);
			}
		}
		Collections.sort(potentialMoves, new MoveComparator());
		chosenMove = potentialMoves.get(0);
		return chosenMove;
	}
	
	private int awesomeness(Board board, Player player) {
		int awesomeness = 0;
		
		awesomeness += this.getWallsLeft() * WALLSLEFT_WEIGHT;
		
		Space otherSide;
		if (player.equals(board.players._1())) otherSide = board.player2Start;
		else otherSide = board.player1Start;
		ArrayList<Space> exits = new ArrayList<Space>();
		for (int i = 1; i <=9; i++) exits.add(new Space(i, otherSide.row()));
		board.graph.fillNodeDistances(exits);
		int distance = board.graph.node[player.getSpace().row()][player.getSpace().col()].distanceToExit;
		awesomeness -= distance * DISTANCE_WEIGHT;
		
		if (player.equals(board.winner())) awesomeness = Integer.MAX_VALUE/2;
		
		return awesomeness;
	}
}