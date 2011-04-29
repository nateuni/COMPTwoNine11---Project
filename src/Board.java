import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Board {
	static Player player1;
	static Player player2;
	LinkedList<Wall> wallList;
 	
	Board() {
		player1 = new Player("MINDY", 1, 5);
		player2 = new Player("RALPH", 9, 5);
		wallList = new LinkedList<Wall>();
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		while(true) {
			printBoard();
			getMoves(player1);
			getMoves(player2);
		}	
	}
	
	static void getMoves(Player player) {
		try {
			while(true) {
				System.out.println("Enter player " + player.getName() + " move: ");
				BufferedReader userReader =
					new BufferedReader(new InputStreamReader(System.in));
				String fromUser = userReader.readLine();
				if (handleInput(player, fromUser.toUpperCase())) {
					return;
				}
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private static boolean handleInput(Player player, String input) {
		Boolean valid = true;
		int newX = player.getSpace().getX();
		int newY = player.getSpace().getY();
		
		if( input.equals("UP")) {
			newY--;
		}	
		else if( input.equals("DOWN")) {
			newY++;
		}
		else if( input.equals("LEFT")) {
			newX--;
		}
		else if( input.equals("RIGHT")) {
			newX++;
		}
		else {
			valid = false;
		}
		if(valid&&checkPointVals(newX,newY)) {
			setCoord (player, newX, newY);
			return true;
		}
		else {
			System.out.println("invalid move");
			return false;
		}
		
	}
	public static boolean setCoord (Player player, int x, int y) {
		if(checkPointVals(x,y)) {
			player.getSpace().setCoords(x, y);
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean checkPointVals (int x, int y) {
		return(checkPoint(x)&&checkPoint(y));
	}
	
	private static boolean checkPoint(int i) {
		return( i>0 && i<= 9);
	}
	

	static void printBoard() {
		int y1 = player1.getSpace().getY();
		int y2 = player2.getSpace().getY();
		Boolean player1Row = false;
		Boolean player2Row = false;
		for(int row =1; row<= 9; row++){
			printEdge();
			if( y1 == row) {
				player1Row = true;
			}
			if( y2 == row) {
				player2Row = true;
			}
			printFill(player1Row, player2Row);
			printFill(player1Row, player2Row);
			player1Row = false;
			player2Row = false;
			}
		printEdge();
	}
	
	static void printEdge() {
		final String boxEdge = "......";
		for(int i =1; i<=9; i++) {
			System.out.print(boxEdge);
		}
		System.out.println(".");
	}
	
	static void printFill(Boolean player1Row, Boolean player2Row) {
		final String boxFill = ":     ";
		int x1 = player1.getSpace().getX();
		int x2 = player2.getSpace().getX();
		for(int i =1; i<=9; i++) {
			if( player1Row && x1 == i) {
				String fill = (":  "+ player1.getToken() + "  ");
				System.out.print( fill);
			}
			else if( player2Row && x2 == i) {
				String fill = (":  "+ player2.getToken() + "  ");
				System.out.print(fill);
			}
			else {
				System.out.print(boxFill);	
			}
			}
		System.out.println(":");
	}
}



//private static boolean validMove(Player player, int newX, int newY) {
//int x = player.getSpace().getX();
//int y = player.getSpace().getY();
//if( (newX-x == 1 || newX-x == -1)&&(newY == y)){
//	return true;
//}
//else if((newY-y == 1 || newY-y == -1)&&(newX==x)) {
//	return true;
//}
//else {
//	return false;
//}
//}
//
