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
		//check if they are asking for move or wall
		movePlayer(command);
		return true;
	}
	
	private void movePlayer(String coords) {
		Space newSpace = new Space(coords);
		currentPlayer.setSpace(newSpace);
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
