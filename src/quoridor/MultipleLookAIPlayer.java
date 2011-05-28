package quoridor;

import java.util.LinkedList;
import java.util.List;

public class MultipleLookAIPlayer extends AIPlayer {

	int depth = 1;
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
		int bestCase = Integer.MIN_VALUE;
		int evaluation;
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				evaluation = negamax(newBoard, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
				//evaluation = minimax(newBoard, depth);
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
	
	/**
	 * Evaluates a board to a given depth, and returns the minimax value
	 * @param board
	 * @param depth
	 * @return minimax value
	 */
	private int minimax(Board board, int depth) {
		i++;
		if (depth == 0) return evaluate(board);
		if (board.checkWin() == 1) return Integer.MAX_VALUE;
		else if (board.checkWin() == 2) return Integer.MIN_VALUE;
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

	private int negamax(Board board, int alpha, int beta, int depth) {
		i++;
		if (depth == 0) return board.currentPlayer().minMax() * evaluate(board);
		if (board.currentPlayer().equals(board.winner())) return Integer.MIN_VALUE;
		else if (board.players.other(board.currentPlayer()).equals(board.winner())) return Integer.MAX_VALUE;
		Board newBoard;
		List<Move> potentialMoves = board.currentPlayer().allMoves(board);
		int bestCase = Integer.MIN_VALUE;
		int evaluation;
		for (Move move : potentialMoves) {
			try {
				newBoard = board.clone();
				newBoard.makeMove(move);
				evaluation = negamax(newBoard, -beta, -alpha, depth - 1);
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
		return 1;
	}

}
