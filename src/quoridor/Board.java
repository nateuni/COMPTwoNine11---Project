package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * A board has Two Players and up two 20 walls (10 per player) and tracks each of their coordinates.
 */
public class Board {
	private Two<Player> players;
	private List<Wall> wallList;
 	
	/**
	 * A board takes Two players as a pair as defined by the Two Class.
	 * @param players
	 * 			The Two players for the this board instance.
	 */
	public Board(Two<Player> players) {
		this.players = players;
		wallList = new LinkedList<Wall>();
	}
	
	/**
	 * Returns the Two players for this particular board instance.
	 * @return The Two players
	 */
	public Two<Player> getPlayers (){
		return this.players;
	}
	
	/**
	 * Returns a pointer to the wall list. 
	 * @return The list of all walls at time of calling
	 */
	public List<Wall> getWallList(){
		return this.wallList;
	}

	// What are we doing with these? Are they going to the GameInterface?
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
	
	// What are we doing with these? Isn't this redundant? 
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
	/**
	 * Sets an individual player's coordinates on the board after checking that in coordinates are valid. 
	 * @param player - The players who's coordinates are to be set.
	 * @param x The x-coordinate as an int.
	 * @param y y-coordinate as an int.
	 * @return The result of the call as a boolean.
	 */
	public boolean setCoord (Player player, int x, int y) {
		if(checkPointVals(x,y)) {
			player.getSpace().setCoords(x, y);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks that both the x and y coordinates are with in the confides of the 9 x 9 board, by calling checkPointsVals.
	 * @param x The x-coordinate as an int.
	 * @param y The y-coordinate as an int.
	 * @return The result.
	 */
	private boolean checkPointVals (int x, int y) {
		return(checkPoint(x)&&checkPoint(y));
	}
	
	/**
	 * Checks the individual passed value to see if it is valid input.
	 * @param i the individual coordinate that is passed as an int.
	 * @return The result. 
	 */
	private boolean checkPoint(int i) {
		return( i>0 && i<= 9);
	}
	
	/**
	 * Adds an individual wall to the boards list, by constructing a new wall and appending it to the list. 
	 * @param x the walls x coordinate as an int.
	 * @param y the walls y coordinate as an int.
	 * @param vertical if the wall is vertical or not, as a boolean. 
	 */
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

