package quoridor;													

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ConsoleGame extends Game {

	public static void main(String[] args) {
		ConsoleGame thisGame = new ConsoleGame();
	}

	/**
	 *Constructor
	 *Will only return once game has been set up and is ready to play 
	 */
	public ConsoleGame() {
		System.out.println("Welcome to Quoridor!");
		while (!setUp());
		playGame();
	}

	/**
	 * Determines the type of players needed for this game (AI, human)
	 * Sets the board/players according to the user's specification
	 * @return true once set up is complete.
	 */
	private Boolean setUp() {
		while(!this.menu());
		return true;
	}	

	/**
	 * Displays the game menu and allows players to select the type
	 * of game they wish to play. A board will be constructed based on this input
	 * 
	 * @return true if a valid board was constructed, false otherwise
	 */
	protected boolean menu(){
		int selection = 0;
		while(selection != 5) {
			System.out.print("Please select your choice: \n" +
					"1 - Play Human vs Human Game\n" +
					"2 - Play Human vs AI Game\n" +
					"3 - Play AI vs AI Game\n" +
					"4 - Load previously saved game\n" +
			"5 - Quit\n");
			selection = Integer.parseInt(getFromUser());
			switch(selection){
			case 1: case 2: case 3: board = Factory.instance().makeBoard(selection); break;
			case 4: board = this.load(); break;
			case 5: System.exit(0); break;
			default: System.out.println("Invalid Input");
			}
			if(selection >= 1 && selection < 4 && board != null){
				return true;
			}
		}
		return false;
	}

	/**
	 * This function controls the flow of game play.
	 */
	public boolean playGame() {
		boolean movePlayed = false;
		while (!gameOver) {
			System.out.println(board);
			movePlayed = false;
			while(!movePlayed) {
				try {
					playNextTurn();
					movePlayed = true;
				}
				catch (RuntimeException e) {
					System.out.println("Error: " + e.getMessage());
				}
		}
		this.checkWin = board.checkWin();
		return true;
		}
		return movePlayed;
	}

	protected void playNextTurn() {
		System.out.println(board.whoseTurn()+"'s Turn");
		
		Move move = board.currentPlayer().getMove(board);
		if (move != null) board.makeMove(move);
		else board.makeMoveFromInput(this.getFromUser());
		if (board.checkWin() != 0) {
			gameOver = true;
			System.out.println(board);
			if (board.checkWin() == 1) System.out.println(board.players._1().getName() + " wins!");
			else System.out.println(board.players._2().getName() + " wins!");
		}
	}

	/**
	 * Read one line from user input
	 * @return the read line as a String
	 */
	protected String getFromUser() {
		try {
			while (true) {
				BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter move: ");
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
