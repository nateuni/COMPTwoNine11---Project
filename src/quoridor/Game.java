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
	public abstract boolean playGame();

	/**
	 * Play the next turn in the game.
	 * Method: prompt user for a move
	 * attempt to play the move on the board
	 * prompt user again if the move is invalid
	 */
	protected abstract void playNextTurn();
	
	public int checkWin() {
		return board.checkWin();
	}
}
