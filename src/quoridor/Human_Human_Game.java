package quoridor;

import java.util.LinkedList;

public class Human_Human_Game extends AbstractGame{

	@Override
	protected boolean setupGame() {
		System.out.println("WELCOME TO QUORIDOR");
		System.out.print("Enter name of Player 1: ");
		String player1Name = getFromUser();
		System.out.print("Enter name of player 2: ");
		String player2Name = getFromUser();
		if(player1Name.equals(player2Name)) {
			System.out.println("Please enter a different name" +
					" for each player");
			return false;
		}
		board = new Board(player1Name, player2Name);
		return true;
	}

	@Override
	protected void playNextTurn() {
		while(!playHumanTurn()){}
	}
	
	private boolean playHumanTurn() {
		System.out.println("Current Player: "+ board.currentPlayer());
																																					System.out.print("enter move: ");
		String movesString = getFromUser();
		LinkedList<String> movesList = tokenizeString(movesString);
		Boolean wasPlayed = handleTurn(movesList.get(0));
			if(!wasPlayed) {
				System.out.println("incorrect input. Try again");
			}
			else {
				onTurnOver();
		}
		return wasPlayed;
	}
	
	//returns true if player has completed a valid turn.
    private Boolean handleTurn(String command) {
    	// if it is a wall move
    	if(!checkInput(command)) {
    		return false;
    	}
		
		return moveCurrentPlayer(command);
	}
    
    //returns 0 for a valid movement move, 1 for a valid wall move and -1 for an invalid request.
    checkInput(String command) {
    	if(command.substring(0,1).equals("V") || command.substring(0,1).equals("H")){
    		if(!checkValidWall(command)) {
    			return -1;
    		}
    		return 1;
		}
    	else if(command.length() == 2) {
    		if(!checkValidMove(command)) {
    			return -1;
    		}
    		return 0;
    	}
    }

	private boolean checkValidWall(String command) {
		// TODO Auto-generated method stub
		return false;
	}
}
