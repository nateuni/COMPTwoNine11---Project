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
    	try {
    	int inputType = checkInput(command);
    	if(inputType <0) {
    		return false;
    	}
    	if(inputType == 0) {
    		checkValidMovement(command);
    		Space newSpace = new Space(command);
    		moveCurrentPlayer(space);
    	}
    	if(inputType == 1) {
    		
    		checkValidWall(command)
    	}
    	}
    	catch( Exception e) {
    		//all exceptions would be from trying to read incorrect formatting on user input
    		return false;
    	}
	}
    
    //returns 0 for a movement move, 1 for a wall move and -1 for an invalid request.
    private int checkInput(String command) {
    	if(command.substring(0,1).equals("V") || command.substring(0,1).equals("H")){
    		return 1;
		}
    	else if(command.length() == 2) {
    		return 0;
    	}
    	return -1;
    }

	private boolean checkValidWall(String command) {
		// TODO Auto-generated method stub
		return false;
	}
}
