package quoridor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GameInterface {
	Board board = null;				
	Player currentPlayer;
	Boolean gameOver = false;
	
	public void playGame() {
		boolean ready = false;
		while(!ready) {
		ready = setupGame();
		}
		while(!gameOver) {
			System.out.println(board);
			playTurn();
		}	
	}
	
	private boolean setupGame() {
		System.out.println("WELCOME TO QUORIDOR");
		System.out.print("Enter name of Player 1: ");
		String player1Name = getFromUser();
		System.out.print("Enter name of player 2: ");
		String player2Name = getFromUser();
		if(player1Name.equals(player2Name)) {
			System.out.println("Please enter a different token and name" +
					" for each player");
			return false;
		}else if(player1Name.length() < 3 || player2Name.length() < 3) {
			System.out.println("Names must be a minimum of 3 characters in length");
			return false;
		}
		board = new Board(player1Name, player2Name);
		currentPlayer = board.getPlayers()._1();
		return true;
	}
	
	private String getFromUser() {
		try {
			while(true) {
				BufferedReader userReader =
					new BufferedReader(new InputStreamReader(System.in));
				String fromUser = userReader.readLine();
					return fromUser;
				}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	private boolean playTurn() {
		System.out.println("Current Player: "+ currentPlayer);
		System.out.print("enter move: ");
		String movesString = getFromUser();
		ArrayList<String> movesList = tokenizeString(movesString);
		Boolean wasPlayed = handleTurn(movesList.get(0));
			if(!wasPlayed) {
				System.out.println("incorrect input. Try again");
			}
			else {
				endTurn();
		}
		return wasPlayed;
	}
	
	//returns true if player has completed a valid turn.
    private Boolean handleTurn(String command) {
    	// if it is a wall move
		if(command.contains("V") || command.contains("H")){
			return placeWall(command);
		}
		return movePlayer(command);
	}
	
	private boolean movePlayer(String coords) {
		Space newSpace = new Space(coords);
		if(board.checkMove(newSpace, currentPlayer)) {
			currentPlayer.setSpace(newSpace);
			return true;
		}
		return false;
	}
	
	// need to check string is in the right order as H12ab works, but incorrect input of Hab12 throws an error. 
	public boolean placeWall(String coords) {
		if(coords.length() < 5 && !(coords.substring(0,1).equals("H") || coords.substring(0,1).equals("V"))){
			return false;
		}else if(currentPlayer.decrementWallTally()){
			boolean isVertical;
			if(coords.substring(0,1).equals("H")){
				isVertical = false;
			}else {
				isVertical = true;
			}
			int row = Integer.parseInt(coords.substring(1,2));
			String col = coords.substring(3,4);
			return board.addWall(new Wall(new Space(col,row), isVertical));
		}
		return false;
	}
 	
	private void endTurn() {
		//switch current player
		currentPlayer = board.getPlayers().other(currentPlayer);
		
		//check for win
	}
	
	private ArrayList<String> tokenizeString (String string) {
		ArrayList<String> tokenizedList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(string);
	     while (st.hasMoreTokens()) {
	         tokenizedList.add(st.nextToken());
	     }
	     return tokenizedList;
	}
}
