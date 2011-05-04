package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Board {
	private Two<Player> players;
	private List<Wall> wallList;
 	
	public Board(Two<Player> players) {
		this.players = players;
		wallList = new LinkedList<Wall>();
	}
	
	public Two<Player> getPlayers (){
		return this.players;
	}
	
	public List<Wall> getWallList(){
		return this.wallList;
	}

	void getMoves(Player player) {
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
	
	private boolean handleInput(Player player, String input) {
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
	public boolean setCoord (Player player, int x, int y) {
		if(checkPointVals(x,y)) {
			player.getSpace().setCoords(x, y);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean checkPointVals (int x, int y) {
		return(checkPoint(x)&&checkPoint(y));
	}
	
	private boolean checkPoint(int i) {
		return( i>0 && i<= 9);
	}
	
	public void addWall(int x, int y, Boolean vertical) {
		wallList.add(new Wall(x, y, vertical));
	}
	
	public String toString() {
		return BoardPrinter.printBoard(this);
	}

	public void print() {
		System.out.println(this);
	}

}

