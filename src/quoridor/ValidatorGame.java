package quoridor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class ValidatorGame extends Game{
	protected Queue<String> q = null;
	int gameType = 3;
	
	public ValidatorGame(String string) {
		if(string == null){
			throw new RuntimeException("String cannot be null");
		}
		this.breakUpStringIntoQueue(string);
		this.consoleGame = false;
		board = Factory.instance().makeBoard(gameType);
	}
	
	private void breakUpStringIntoQueue(String string) {
		q = new LinkedList<String>();
		Pattern p = Pattern.compile("[\\s]");
		String[] result = p.split(string);
		for(int i = 0; i < result.length; i++) {
			q.add(result[i]);
		}
	}

	/**
	 * This function controls the flow of game play.
	 */
	public boolean playGame() {
//		boolean movePlayed;
		while (!gameOver) {
//			movePlayed = false;
			try {
				playNextTurn();
			}
			catch (RuntimeException e) {
				return false;
			}
		}
		this.checkWin = board.checkWin();
		return true;
	}

	protected void playNextTurn() {
		if(q.size() > 0){
			if (board.checkWin() != 0) {
				throw new RuntimeException("Can't play move after win.");
			}
			board.makeMoveFromInput(q.remove());
		}
		else gameOver = true;
	}

}