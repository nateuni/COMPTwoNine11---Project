package quoridor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MultipleLookAIPlayer extends AIPlayer {

	int depth = 1;
	
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
		int bestCase = Integer.MIN_VALUE;
		int evaluation;
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				evaluation = board.currentPlayer().minMax() * minimax(newBoard, depth);
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
		return bestMoves.get(pick);
	}
	
	/**
	 * Evaluates a board to a given depth, and returns the minimax value
	 * @param board
	 * @param depth
	 * @return minimax value
	 */
	private int minimax(Board board, int depth) {
		if (depth == 0) return evaluate(board);
		Board newBoard;
		List<Move> potentialMoves = board.currentPlayer().allMoves(board);
		int bestCase = Integer.MIN_VALUE;
		int evaluation;
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				evaluation = board.currentPlayer().minMax() * minimax(newBoard, depth - 1);
				if (evaluation > bestCase) {
					bestCase = evaluation;
				}
			}
			catch (Exception e) {}
		}
		
		// If there are several equal best moves, pick one at random
		return board.currentPlayer().minMax() * bestCase;
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
