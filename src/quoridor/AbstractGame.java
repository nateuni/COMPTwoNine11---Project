package quoridor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public abstract class AbstractGame {
	Board board = null;				
	Boolean gameOver = false;
		
	//this function controls the flow of gameplay.
	public void playGame() {
		boolean ready = false;
		while(!ready) {
		ready = setupGame();
		}
		
		while(!gameOver) {
			System.out.println(board);
			playNextTurn();
		}
	}
	
	protected abstract boolean setupGame();
	
	protected abstract void playNextTurn();
//	
//	public boolean genericSetup() {
//		board = new Board(" X ", " O ");
//		return true;
//	}
//	
//	
//	
	protected String getFromUser() {
		try {
			while(true) {
				BufferedReader userReader =
					new BufferedReader(new InputStreamReader(System.in));
		
				
				
				
				String fromUser = userReader.readLine();
				if(!fromUser.isEmpty()) {	//never returns an empty string
					return fromUser;	
				}
				}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	private void movePlayer(String coords) {
		Space newSpace = new Space(coords);
			board.currentPlayer().setSpace(newSpace);
		}
	
	// need to check string is in the right order as H12ab works, but incorrect input of Hab12 throws an error. 
	public boolean placeWall(String coords) {
		if(coords.length() < 5 && !(coords.substring(0,1).equals("H") || coords.substring(0,1).equals("V"))){
			return false;
		}else if(board.currentPlayer().decrementWallTally()){
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
	
	public boolean checkValidWall(String coords) {
		if(coords.length() < 5 ){
			return false;
		}
		int row = Integer.parseInt(coords.substring(1,2));
		String col = coords.substring(3,4);
		Space requestedSpace = new Space(col, row);
		if(!board.currentPlayer().hasWallsLeft()) {
			return false;
		}
		
		
		if(board.getWallsList().contains(requestedSpace)) {
			return false;
		}
		
		return true;
	}
 	
	protected void onTurnOver() {
		//switch current player
		board.nextPlayer();
		//check for win
	}
	
	//Tokenizes a String into a LinkedList of words..
	protected LinkedList<String> tokenizeString (String string) {
		LinkedList<String> tokenizedList = new LinkedList<String>();
		StringTokenizer st = new StringTokenizer(string);
	     while (st.hasMoreTokens()) {
	         tokenizedList.add(st.nextToken());
	     }
	     return tokenizedList;
	}
}
