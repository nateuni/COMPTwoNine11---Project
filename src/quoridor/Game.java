package quoridor;
	
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class Game implements GameInterface, Serializable {
	protected Board board = null;
	protected Boolean gameOver = false;
	protected boolean consoleGame;	//i changed validatorGame to consoleGame only to show we can use the validatorGame class to run all sorts of test
	
	/**
	 * This function controls the flow of game play.
	 */
	public boolean playGame() {
		boolean movePlayed;
		while (!gameOver) {
			System.out.println(board);
			movePlayed = false;
			while(!movePlayed) {
				try {
					playNextTurn();
					movePlayed = true;
				}
				catch (RuntimeException e) {
					if (this instanceof ValidatorGame) return false;
					else System.out.println("Error: " + e.getMessage());
				}
			}
		}
		// need to make a way to check if game is over, eg check if queue is empty for the validator.
		return true;
	}

	/**
	 * Play the next turn in the game.
	 * Method: prompt user for a move
	 * attempt to play the move on the board
	 * prompt user again if the move is invalid
	 */
	protected void playNextTurn() {
	 
			System.out.println(board.whoseTurn()+"'s Turn:");
			board.makeMoveFromInput(this.getFromUser());
			if (board.checkWin() != 0) {
				gameOver = true;
				System.out.println(board);
				if (board.checkWin() == 1) System.out.println(board.players._1().getName() + " wins!");
				else System.out.println(board.players._2().getName() + " wins!");
			}
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

	
	protected boolean save(){
		System.out.println("Saving game....");
		try {
		      FileOutputStream fOut = new FileOutputStream("saveLoadTest.qdr");
		      ObjectOutputStream objOutput = new ObjectOutputStream(fOut);
		      objOutput.writeObject(this.board);
		      objOutput.close();
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected Board load(){
		System.out.println("Loading game....");
		try {
			FileInputStream fInput = new FileInputStream("saveLoadTest.qdr");
			ObjectInputStream objInput = new ObjectInputStream(fInput);
			this.board = (Board) objInput.readObject();
			objInput.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null;
		}
		return board;
	}
}
