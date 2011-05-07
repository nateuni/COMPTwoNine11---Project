package quoridor;

import java.util.LinkedList;


/**
 * A board has Two Players and up two 20 walls (10 per player) and tracks each of their coordinates.
 */
public class Board {
	private Two<Player> players;
	private LinkedList<Wall> wallList;
	static final Space player1Start = new Space("e1");
	static final Space player2Start = new Space("e9");

	/**
	 * A board takes Two players as a pair as defined by the Two Class.
	 * @param players
	 * 			The Two players for the this board instance.
	 */
	public Board(Two<Player> players) {
		this.players = players;
		wallList = new LinkedList<Wall>();
	}
	
	
	public Board(String player1Name, String player2Name) {
		Player player1 = new Player(player1Name, player1Start);
		Player player2 = new Player(player2Name, player2Start);
		this.players = new Two<Player>(player1,player2);
		wallList = new LinkedList<Wall>();
	}
	
	/**
	 * Returns the Two players for this particular board instance.
	 * @return The Two players
	 */
	public Two<Player> getPlayers (){
		return this.players;
	}
	
	public Player getPlayer(int refNumber) {
		if(refNumber == 1) {
			return players._1();
		}
		if(refNumber == 2) {
			return players._2();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Returns a pointer to the wall list. 
	 * @return The list of all walls at time of calling
	 */
	public LinkedList<Wall> getWallList(){
		return this.wallList;
	}

	/**
	 * Sets an individual player's coordinates on the board after checking that in coordinates are valid. 
	 * @param player - The players who's coordinates are to be set.
	 * @param x The x-coordinate as an int.
	 * @param y y-coordinate as an int.
	 * @return The result of the call as a boolean.
	 */
	
	/**
	 * Checks that both the x and y coordinates are with in the confides of the 9 x 9 board, by calling checkPointsVals.
	 * @param x The x-coordinate as an int.
	 * @param y The y-coordinate as an int.
	 * @return The result.
	 */
	protected boolean checkMove (Space space, Player player) {
		return(checkBounds(space) && checkSpaceForOtherPlayer(space, player) && checkSpaceIsAdjacent(space, player) && checkIsNotSameSpace(space, player));
	}
	
	/**
	 * Checks the space is within the bounds of the board
	 * @return The result. 
	 */
	private boolean checkBounds(Space space) {
		return(space.row>0 && space.row<=9 && space.col>0 && space.col<=9);
	}
	
	/**
	 * Checks if the players move is occupied by another player.
	 * @param space the space that the player wishes to move to.
	 * @param player the player that is doing the moving.
	 * @return The result. 
	 */
	private boolean checkSpaceForOtherPlayer(Space space, Player player) {
		return(!players.other(player).getSpace().equals(space));
	}
	
	/**
	 * Checks that the space that the player wished to move to is is adjacent
	 * @param space the space that the player wishes to move to.
	 * @param player the player that is doing the moving.
	 * @return The result.
	 */
	private boolean checkSpaceIsAdjacent(Space space, Player player){
		return ((player.getSpace().col == space.col +1 || player.getSpace().col == space.col -1) || (player.getSpace().row == space.row +1 || player.getSpace().row == space.row -1));
	}
	
	/**
	 * Check that the player is actually making a move, not remaining stationary. 
	 * @param space the space that the player wishes to move to.
	 * @param player the player that is doing the moving.
	 * @return The result.
	 */
	private boolean checkIsNotSameSpace(Space space, Player player){
		return !player.getSpace().equals(space);
	}
	
	/**
	 * Adds an individual wall to the boards list, by constructing a new wall and appending it to the list. 
	 * @param x the walls x coordinate as an int.
	 * @param y the walls y coordinate as an int.
	 * @param vertical if the wall is vertical or not, as a boolean. 
	 */
	public void addWall(int x, int y, Boolean vertical) {
		wallList.add(new Wall(new Space(x,y), vertical));
	}
	
	public String toString() {
		return BoardPrinter.buildBoardString(this);
	}

	public void print() {
		System.out.println(this);
	}

}

