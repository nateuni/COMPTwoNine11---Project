package quoridor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MultipleLookAIPlayer extends AIPlayer {

	int depth = 2;
	public static int i = 0;
	
	public MultipleLookAIPlayer(int playerNumber) {
		super(playerNumber);
		}

	/**
	 * Looks at the board and figures out what move to make.
	 * @see quoridor.Player#getMove(quoridor.Board)
	 */
	public Move getMove(Board board) {
		assert(this.equals(board.currentPlayer()));
		Board newBoard;
		List<Move> potentialMoves = allMoves(board);
		List<Move> bestMoves = new LinkedList<Move>();
		Collections.sort(potentialMoves, new MinimaxComparator(board.getSpace(board.players.other(this))));
		int bestCase = Integer.MIN_VALUE;
		int evaluation;
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				evaluation = -negamax(newBoard, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
				if (evaluation > bestCase) {
					bestMoves.clear();
					bestMoves.add(move);
					bestCase = evaluation;
				}
				else if (evaluation == bestCase) {
					bestMoves.add(move);
				}
			}
			catch (Exception e) {}
		}
		
		// If there are several equal best moves, pick one at random
		int pick = (int) Math.floor(bestMoves.size() * Math.random());
		System.out.println(i);
		i = 0;
		return bestMoves.get(pick);
	}

	private int negamax(Board board, int alpha, int beta, int depth) {
		i++;
		if (board.currentPlayer().equals(board.winner())) return Integer.MAX_VALUE;
		else if (board.players.other(board.currentPlayer()).equals(board.winner())) return Integer.MIN_VALUE + 1;
		else if (depth == 0) return board.currentPlayer().minMax() * evaluate(board);
		
		Board newBoard;
		List<Move> potentialMoves = board.currentPlayer().allMoves(board);
		Collections.sort(potentialMoves, new MinimaxComparator(board.getSpace(board.players.other(board.currentPlayer()))));
		int bestCase = Integer.MIN_VALUE;
		int evaluation;
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				evaluation = -negamax(newBoard, -beta, -alpha, depth - 1);
				if (evaluation > bestCase) bestCase = evaluation;
				if (bestCase > alpha) alpha = bestCase;
				if (alpha >= beta) return alpha;
			}
			catch (Exception e) {}
		}
		
		// If there are several equal best moves, pick one at random
		return bestCase;
	}
	
	@Override
	protected int distanceWeight() {
		return 5;
	}

	@Override
	protected int wallsLeftWeight() {
		return 3;
	}

}
