package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Game implements Serializable {

	private Board board = null;
	private Boolean gameOver = false;

	/**
	 *Constructor
	 *Will only return once game has been set up and is ready to play 
	 */
	public Game() {
		while (!setUp());
	}

	
	/**
	 * Determines the type of players needed for this game (AI, human)
	 * Sets the board/players according to the user's specification
	 * @return true once set up is complete.
	 */
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

	/**
	 * this function controls the flow of game play.
	 */
	public void playGame() {
		while (!gameOver) {
			System.out.println(board);
			playNextTurn();
		}
	}

	/**
	 * Play the next turn in the game.
	 * Method: prompt user for a move
	 * attempt to play the move on the board
	 * prompt user again if the move is invalid
	 */
	protected void playNextTurn() {
		
	}

	/**
	 * perform necessary responsibilities for an end of turn:
	 * check for a win
	 * alternate the current player
	 */
	protected void onTurnOver() {
		// switch current player
		board.nextPlayer();
		// check for win
	}

	/**
	 * Read one line from user input
	 * @return the read line as a String
	 */
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
