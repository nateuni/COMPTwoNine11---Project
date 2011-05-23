package quoridor;

import java.io.*;
import java.util.*;


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

	protected void quit() {
		System.exit(0);
	}
	
	private String getCurrentListOfMovesAsString(){
		return this.board.moveListToString();
	}
	
	public boolean save(){
		String player1Details = this.board.getPlayers()._1().getName()+"\n"+this.board.getPlayers()._1().getToken()+"\n";
		String player2Details = this.board.getPlayers()._2().getName()+"\n"+this.board.getPlayers()._2().getToken()+"\n";
		String moveString = this.getCurrentListOfMovesAsString();
		
		System.out.println("Saving game....");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("saveLoadTest.qdr"));
			out.write(player1Details+player2Details+moveString);
			out.close();
		} catch (IOException e) { 
			System.out.println("Exception: Unable to save file");
			return false;
		}
		return true;
	}
	
	public Board load(){
		Scanner inputStream = null;
		int lineCounter = 0;
		String player1Name = null, player1Token = null, player2Name = null, player2Token = null, moveString = null;
		
		System.out.println("Loading game....");	
		
		try{
			inputStream = new Scanner(new File("saveLoadTest.qdr"));
		} catch(FileNotFoundException e){
			System.out.println("Exception: Unable to load file, file not found.");
		}
		
		while(inputStream.hasNextLine()){
			switch(lineCounter){
				case 0: player1Name = inputStream.nextLine().trim(); lineCounter++; break;
				case 1: player1Token = inputStream.nextLine().trim(); lineCounter++; break;
				case 2: player2Name = inputStream.nextLine().trim(); lineCounter++; break;
				case 3:	player2Token = inputStream.nextLine().trim(); lineCounter++; break;
				case 4: moveString = inputStream.nextLine().trim(); lineCounter++; break;
			}
		}
		Game game = new ValidatorGame(moveString);
		game.board.players._1().setName(player1Name);
		game.board.players._1().setToken(player1Token);
		game.board.players._1().setName(player2Name);
		game.board.players._1().setToken(player2Token);
		
		return game.board;
	}
}
