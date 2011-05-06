package 	quoridor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class GameInterface {
	Board board = null;				
	Player currentPlayer;
	
	public void play() {
		setupGame();
		
		while(true) {
			board.toString();
			getMoves();
		}	
		
	}
	
	void setupGame() {
		System.out.println("WELCOME TO QUORIDOR");
		System.out.print("Enter name of Player 1: ");
		String player1Name = getFromUser();
		System.out.print("Enter name of player 2: ");
		String player2Name = getFromUser();
		board = new Board(player1Name, player2Name);
		currentPlayer = Board.player1;\
	}
	
	String getFromUser() {
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
	
	private void getMoves() {
		System.out.println("Current Player: "+ currentPlayer);
		System.out.print("enter moves: ");
		String movesString = getFromUser();
		ArrayList<String> movesList = tokenizeString(movesString);
		for(int i=0; i<movesList.size();i++) {
			playTurn(movesList.get(i));
			updateCurrentPlayer();
		}
	}
	
	void playTurn(String command) {
		//check if they are asking for move or wall
		makeMove(command);
	}
	
	void makeMove(String coords) {
		Space newSpace = new Space(coords);
		currentPlayer.setSpace(newSpace);
	}
 	
	void updateCurrentPlayer() {
		if( currentPlayer == board.getPlayer1()) {
			currentPlayer = board.getPlayer2();
		}
		else{
			currentPlayer = board.getPlayer1();
		}
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
