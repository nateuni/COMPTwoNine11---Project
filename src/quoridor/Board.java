package quoridor;

import java.util.LinkedList;


/**
 * A board has Two Players and up to 20 walls (10 per player) and tracks each of their coordinates.
 */
public class Board {
	private Two<Player> players;
	private Player currentPlayer;
	private LinkedList<Wall> wallList;
	static final Space player1Start = new Space("e1");
	static final Space player2Start = new Space("e9");
	Player winner = null;

	/**
	 * A board takes Two players as a pair as defined by the Two Class.
	 * @param players
	 * 			The Two players for the this board instance.
	 */
	public Board(Two<Player> players) {
		this.players = players;
		wallList = new LinkedList<Wall>();
		currentPlayer = players._1();
	}
	
	
	public Board(String player1Name, String player2Name) {
		Player player1 = new Player(player1Name, player1Start);
		Player player2 = new Player(player2Name, player2Start);
		this.players = new Two<Player>(player1,player2);
		wallList = new LinkedList<Wall>();
		currentPlayer = players._1();
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
	public LinkedList<Wall> getWallList(){
		return this.wallList;
	}
	
	/**
	 * Checks that both the x and y coordinates are with in the confides of the 9 x 9 board, by calling checkPointsVals.
	 * @param x The x-coordinate as an int.
	 * @param y The y-coordinate as an int.
	 * @return The result.
	 */
	protected boolean checkMove (Space space, Player player) {
		return(checkBounds(space) && checkSpaceForOtherPlayer(space, player) && checkSpaceIsAdjacent(space, player) && checkIsNotSameSpace(space, player));
	}
	
	//switch the current player (next player's turn)
	public void nextPlayer() {
		currentPlayer = players.other(currentPlayer);
	}
	
	public boolean checkWin() {
		Player player1 = players._1();
		Player player2 = players._2();
		if(player1.getSpace().equals(player2Start)) {
			winner = player1;
			return true;
		}
		else if(player2.getSpace().equals(player1Start)) {
			winner = player2;
			return true;
		}
		return false;
	}
	
	//accessor for currentplayer
	public Player currentPlayer() {
		return currentPlayer;
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
	 * If less then 20 walls, it adds the individual wall to the boards list, and returns the result.
	 * @param wall the wall that is to be added to the list
	 * @return The result.
	 */
	public boolean addWall(Wall wall) {
		if(wallList.size() < 20 && checkBounds(wall.getSpace())){
			wallList.add(wall);
			return true;
		}
		return false;
	}
	
	public LinkedList<Space> getValidMoves(Player player) {
		LinkedList<Space> validMoves = null;
		Space current = player.getSpace();
		Space up = new Space(current.col, current.row+1);
		Space down = new Space(current.col, current.row-1);
		Space left = new Space(current.col-1, current.row);
		Space right = new Space(current.col+1, current.row);
		
		
		return validMoves;
	}
	
	//checks the validity of a space -> returns false if space does not exist on board
	//-> returns false if space is occupied by player.
	private boolean checkSpaceValidity(Space space) {
		if(!checkBounds(space)) {
			return false;
		}
		if((space.equals(players._1()))||(space.equals(players._2()))) {
		return false;
		}
		return true;
	}
	
	public String toString() {
		return BoardPrinter.buildBoardString(this);
	}

	public void print() {
		System.out.println(this);
	}

}

