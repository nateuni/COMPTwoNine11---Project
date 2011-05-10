package quoridor;

import java.util.LinkedList;

public class Human_Human_Game extends AbstractGame{
	
	private final int PLACE_WALL = 1;
	private final int MOVE_PLAYER = 0;
	private final int INVALID_INPUT = -1;

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
    	try {
    		if(checkInput(command) == INVALID_INPUT) {
    			return false;
    		} else if(checkInput(command)== MOVE_PLAYER) {
    			Space newSpace = new Space(command);

    			if(!withinBounds(newSpace) || isOccupied(newSpace) || !validMove(newSpace)) {
    				return false;
    			} else {
    				movePlayer(newSpace);
    			}

    		} else if(checkInput(command) == PLACE_WALL) {
    				return checkThenPlaceWall(command);
    		}
    	}
    	catch( Exception e) {
    		//all exceptions would be from trying to read incorrect formatting on user input
    		return false;
    	}
    	return true;
    }
        
    /**
	 * Checks the space is within the bounds of the board
	 * @return The result. 
	 */
    private boolean withinBounds(Space space){
	if(space.row>0 && space.row<=9 && space.col>0 && space.col<=9) {
		return true;
	}
	return false;
    }
        
	/**
	 * Checks if the players move is occupied by another player.
	 * @param space the space that the player wishes to move to.
	 * @param player the player that is doing the moving.
	 * @return The result. 
	 */
	private boolean isOccupied(Space space) {
		Player otherPlayer = board.getPlayers().other(board.currentPlayer());
		return(otherPlayer.getSpace().equals(space));
	}
    
    
    
    // check 
    // returns 0 for a movement move, 1 for a wall move and -1 for an invalid request.
    private int checkInput(String command) {
    	if(command.substring(0,1).equals("V") || command.substring(0,1).equals("H")){
    		///
    		return PLACE_WALL;
		}
    	else if(command.length() == 2) {
    		////
    		return MOVE_PLAYER;
    	}
    	return INVALID_INPUT;
    }
    
}
