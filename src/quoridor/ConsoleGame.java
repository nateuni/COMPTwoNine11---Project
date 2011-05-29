package quoridor;													

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	protected boolean menu() {
		int selection = 0;
		while(selection != 5) {
			System.out.println();
			System.out.print("Please select your choice: \n" +
					"1 - Play Human vs Human Game\n" +
					"2 - Play Human vs AI Game\n" +
					"3 - Play AI vs AI Game\n" +
					"4 - Load previously saved game\n" +
					"5 - Quit\n");
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
					board = this.load(fileName); break;
				}
				case 5: quit(); break;
				default: System.out.println("Invalid Input");
			}
			if(selection >= 1 && selection <= 4 && board != null){
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
		}
		this.checkWin = board.checkWin();
		return true;
	}

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

	protected void quit() {
		System.out.println("Whatever man. Bye...");
		System.exit(0);
	}
	
	public boolean save(String fileName){
		String player1Details = this.board.getPlayers()._1().getClass().getName()+"\n"+this.board.getPlayers()._1().getName()+"\n"+this.board.getPlayers()._1().getToken()+"\n";
		String player2Details = this.board.getPlayers()._2().getClass().getName()+"\n"+this.board.getPlayers()._2().getName()+"\n"+this.board.getPlayers()._2().getToken()+"\n";
		String moveString = this.getCurrentListOfMovesAsString();
		
		System.out.println("Saving game....");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".qdr"));
			out.write(player1Details+player2Details+moveString);
			out.close();
		} catch (IOException e) { 
			System.out.println("Exception: Unable to save file");
			return false;
		}
		return true;
	}
	
	public Board load(String fileName){
		boolean p1CurrentPlayer;
		Scanner inputStream = null;
		int lineCounter = 0;
		String player1Type = null, player1Name = null, player1Token = null, player2Type = null, player2Name = null, player2Token = null, moveString = null;
		
		System.out.println("Loading game....");	
		
		try{
			inputStream = new Scanner(new File(fileName+".qdr"));
		} catch(FileNotFoundException e){
			System.out.println("Exception: Unable to load file, file not found.");
			return null;
		}
		
		try {
			player1Type = inputStream.nextLine().trim();
			player1Name = inputStream.nextLine().trim();
			player1Token = inputStream.nextLine().trim();
			player2Type = inputStream.nextLine().trim();
			player2Name = inputStream.nextLine().trim();
			player2Token = inputStream.nextLine().trim();
			moveString = inputStream.nextLine().trim();
		}
		catch (NoSuchElementException e) {
			System.out.println("Exception: Unable to read save file.");
			return null;
		}
		
		Game game = Factory.instance().makeGame(moveString);
		game.playGame();
		
		if(game.board.players._1().equals(game.board.currentPlayer)){
			p1CurrentPlayer = true;
		} else {
			p1CurrentPlayer = false;
		}
		
		game.board.loadPlayers(player1Type, player1Name, player1Token, player2Type, player2Name, player2Token);
		
		if(p1CurrentPlayer){
			game.board.currentPlayer = game.board.players._1();
		} else {
			game.board.currentPlayer = game.board.players._2();
		}
	 
		return game.board;
	}
	
	protected String getCurrentListOfMovesAsString(){
		return this.board.moveListToString();
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
