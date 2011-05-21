package quoridor;
	
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Game implements GameInterface {
	protected int checkWin = 0;
	protected Player winner = null;
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
		this.checkWin = board.checkWin();
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

	
	public boolean save(){
		System.out.println("Saving game....");
		String toSave = this.board.getPlayers()._1.getName() +"\n"+ this.board.getPlayers()._2.getName()+"\n"+this.getCurrentListOfMovesAsString();
		System.out.print(toSave);
		try {

		} catch (Exception e) { 
			return false;
		}
		return true;
	}
	
	public boolean load(){
		System.out.println(this.board.moveListToString());
		return true;
	}
	
	public int checkWin() {
		return board.checkWin();
	}
	
	public String getCurrentListOfMovesAsString(){
		return this.board.moveListToString();
	}
}
