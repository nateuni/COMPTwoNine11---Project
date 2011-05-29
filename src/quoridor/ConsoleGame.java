package quoridor;													

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Handles all the game that are based around console input, as an extension of the Game class. 
 */
public class ConsoleGame extends Game {

	public static void main(String[] args) {
		new ConsoleGame();
	}

	/**
	 *Constructor
	 *Will only return once game has been set up and is ready to play 
	 */
	public ConsoleGame() {
		System.out.println("Welcome to Quoridor!");
		while (!setUp());
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
	protected boolean menu() {
		int selection = 0;
		while(selection != 6) {
			System.out.println();
			System.out.print("Please select your choice:\n" +
					"1 - Play Human vs Human Game\n" +
					"2 - Play Human vs AI Game\n" +
					"3 - Play AI vs AI Game\n" +
					"4 - Load previously saved game\n" +
					"5 - Advanced options\n" +
					"6 - Quit\n");
			try {
				selection = Integer.parseInt(getFromUser("Enter selection: "));
			}
			catch (Exception e) {
				System.out.println("Invalid Input - type the number corresponding to your selection");
				return false;
			}
			switch(selection){
				case 1: case 2: case 3: board = Factory.instance().makeBoard(selection); break;
				case 4: {
					String fileName = getFromUser("Enter name of saved file (no extension): ");
					this.load(fileName); break;
				}
				case 5: setUpAdvanced(); break;
				case 6: quit(); break;
				default: System.out.println("Invalid Input");
			}
			if(selection >= 1 && selection <= 4 && board != null){
				return true;
			}
		}
		return false;
	}

	/**
	 * Provides a menu for the user to set AI parameters.
	 */
	private void setUpAdvanced() {
		int selection = 0;
		int lookAhead;
		int timeOut;
		while(selection != 6) {
			System.out.println();
			System.out.print("Advanced options\n" +
					"1 - Set AI look-ahead depth\n" +
					"2 - Set AI timeout\n" +
					"3 - Return to main menu\n");
			try {
				selection = Integer.parseInt(getFromUser("Enter selection: "));
			}
			catch (Exception e) {
				System.out.println("Invalid Input - type the number corresponding to your selection");
				continue;
			}
			switch(selection){
				case 1:
					try {
						lookAhead = Integer.parseInt(getFromUser("Enter look-ahead depth: "));
					}
					catch (Exception e) {
						System.out.println("Invalid Input");
						break;
					}
					if (lookAhead < 1) System.out.println("Error: Look-ahead depth must be at least 1");
					else Factory.instance().lookAhead = lookAhead;
					break;
				case 2:
					try {
						timeOut = Integer.parseInt(getFromUser("Enter AI timeout (in milliseconds): "));
					}
					catch (Exception e) {
						System.out.println("Invalid Input");
						break;
					}
					if (timeOut < 100) System.out.println("Error: Timeout must be at least 100 milliseconds");
					else Factory.instance().timeOut = timeOut;
					break;
				case 3: return;
				default: System.out.println("Invalid Input");
			}
		}
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
		}
		this.checkWin = board.checkWin();
		return true;
	}

	/* (non-Javadoc)
	 * @see quoridor.Game#playNextTurn()
	 */
	protected void playNextTurn() {
		System.out.println(board.whoseTurn()+"'s Turn");

		Move move = board.currentPlayer().getMove(board);
		if (move != null) board.makeMove(move);
		else {
			String userInput= getFromUser("Enter Move: ");
			if(userInput.equals("save")) {
					String filename = getFromUser("what should I call this game? : ");
					save(filename);
			}
			else if (userInput.equals("quit")) {
				quit();
			}
			else if (userInput.equals("moves")) {
				System.out.println("List of valid moves:\n" + board.currentPlayer().validMoves(board));
			}
			else {
				board.makeMoveFromInput(userInput);
			}
		}
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
	protected String getFromUser(String message) {
		try {
			while (true) {
				BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print(message);
				String fromUser = userReader.readLine().toLowerCase();
				if (fromUser.trim().length() > 0){
					return fromUser;	
				} else {
					System.out.println("Sorry empty strings are not valid input");
					System.out.print("Please try again: ");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
