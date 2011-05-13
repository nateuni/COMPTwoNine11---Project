package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

	Board board = null;
	Boolean gameOver = false;

	public Game() {
		while (!setUp());
	}

	public Boolean setUp() {
		System.out.print("Enter number of human players: ");
		int numberOfPlayers = Integer.parseInt(getFromUser());
		if (numberOfPlayers == 0) {
			board = new Board();
			return true;
		}
		if (numberOfPlayers == 1) {
			board = new Board(new Two<Player>(new HumanPlayer(1), new AIPlayer(
					2)));
			return true;
		}
		if (numberOfPlayers == 2) {
			board = new Board(new Two<Player>(new HumanPlayer(1),
					new HumanPlayer(2)));
			return true;
		}
		return false;
	}

	// this function controls the flow of gameplay.
	public void playGame() {
		while (!gameOver) {
			System.out.println(board);
			playNextTurn();
		}
	}

	protected void playNextTurn() {
		//Queue<String> q = new LinkedList<String>();
	}

	protected void onTurnOver() {
		// switch current player
		board.nextPlayer();
		// check for win
	}

	protected String getFromUser() {
		try {
			while (true) {
				BufferedReader userReader = new BufferedReader(
						new InputStreamReader(System.in));
				String fromUser = userReader.readLine();
				if (!fromUser.isEmpty()) { // never returns an empty string
					return fromUser;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}