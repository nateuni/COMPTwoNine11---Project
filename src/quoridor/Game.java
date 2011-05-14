package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class Game implements Serializable {

	private Board board = null;
	private Boolean gameOver = false;
	private Queue<String> q = null;
	private boolean validatorGame = false; 
	private final boolean INVALID_INPUT = false;

	/**
	 *Constructor
	 *Will only return once game has been set up and is ready to play 
	 */
	public Game() {
		while (!setUp());
	}
	
	public Game(String string) {
		if(string == null){
			throw new RuntimeException("String cannot be null");
		}
		this.breakUpStringIntoQueue(string);
		this.validatorGame = true;
		board = new Board(new Two<Player>(new AIPlayer(1), new AIPlayer(2)));
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
	public boolean playGame() {
		while (!gameOver) {
			System.out.println(board);
			if(playNextTurn() == INVALID_INPUT){
				return false;
			}
		}
		// need to make a way to check if game is over, eg check if queue is empty for the validator. 
		return gameOver;
	}

	/**
	 * Play the next turn in the game.
	 * Method: prompt user for a move
	 * attempt to play the move on the board
	 * prompt user again if the move is invalid
	 */
	protected boolean playNextTurn() {
		if(validatorGame){
			if(q.size() > 0){
				return board.makeMoveFromInput(q.remove());
			}
		} else {
			System.out.println(board.whosTurn()+"'s Turn:");
			return board.makeMoveFromInput(this.getFromUser());
		}
		return false;
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
	
	private void breakUpStringIntoQueue(String string){
		q = new LinkedList<String>();
		Pattern p = Pattern.compile("[\\s]");
		String[] result = p.split(string);
		for(int i = 0; i < result.length; i++){
			q.add(result[i]);
		}
	}
}
