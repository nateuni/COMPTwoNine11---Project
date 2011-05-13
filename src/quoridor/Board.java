package quoridor;

import java.util.LinkedList;

/**
 * A board has Two Players and up to 20 walls (10 per player) and tracks each of their coordinates.
 */
public class Board {

	private Two<Player> players;
	private Player currentPlayer;
	private LinkedList<Wall> wallList;
	private LinkedList<Move> moveList;
	private int moveListIndex = -1;
	static final Space player1Start = new Space("e1");
	static final Space player2Start = new Space("e9");
	Player winner = null;
	public BoardGraph graph = new BoardGraph();

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

	//AI scenario
	public Board() {
		Player player1 = new AIPlayer(1);
		Player player2 = new AIPlayer(2);
		this.players = new Two<Player>(player1, player2);
		wallList = new LinkedList<Wall>();
		currentPlayer = players._1();
	}

	/**
	 * Returns the Two players for this particular board instance.
	 * @return The Two players
	 */
	public Two<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns a pointer to the wall list.
	 * @return The list of all walls at time of calling
	 */
	public LinkedList<Wall> getWallList() {
		return this.wallList;
	}

	/**
	 * Checks that both the x and y coordinates are with in the confides of the 9 x 9 board, by calling checkPointsVals.
	 * @param x The x-coordinate as an int.
	 * @param y The y-coordinate as an int.
	 * @return The result.
	 */
	//protected boolean checkMove (Space space, Player player) {
	//	return(checkBounds(space) && checkSpaceForOtherPlayer(space, player) && checkSpaceIsAdjacent(space, player) && checkIsNotSameSpace(space, player));
	//}
	//switch the current player (next player's turn)
	public void nextPlayer() {
		currentPlayer = players.other(currentPlayer);
	}

	public boolean checkWin() {
		Player player1 = players._1();
		Player player2 = players._2();
		if (player1.getSpace().equals(player2Start)) {
			winner = player1;
			return true;
		} else if (player2.getSpace().equals(player1Start)) {
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
	 * Checks if the players move is occupied by another player.
	 * @param space the space that the player wishes to move to.
	 * @param player the player that is doing the moving.
	 * @return The result.
	 */
	private boolean isOccupied(Space space) {
		Player otherPlayer = players.other(currentPlayer);
		return (otherPlayer.getSpace().equals(space)
				|| currentPlayer.getSpace().equals(space));
	}

	/**
	 * Checks if the players move is occupied by another player.
	 * @param space
	 *            the space that is being queried
	 * @return true is space is occupied by player
	 */
	public boolean wallIsHere(Space a, Space b) {
		if (!(adjacentSpaces(a, b))) {
			return false;
		}
		// swap them if necessary
		if ((b.col() < a.col()) || (b.row() < a.row())) {
			Space temp = a;
			a = b;
			b = temp;
		}
		LinkedList<Wall> toConsider = null;
		// obtain the walls that we need to consider in order for
		// a->b to be blocked by a wall.

		// for a,b to in same row
		if (b.equals(a.getRight())) {
			toConsider.add(new Wall(a, true));
			if (a.getUp() != null) {
				toConsider.add(new Wall(a.getUp(), true));
			}
		}

		// for a,b in same column
		if (b.equals(a.getDown())) {
			toConsider.add(new Wall(a, false));
			if (a.getLeft() != null) {
				toConsider.add(new Wall(a.getLeft(), false));
			}
		}
		// see if any of these walls actually exist
		while (toConsider != null) {
			if (wallList.contains(toConsider.remove())) {
				return true;
			}
		}
		return false;
	}

	// returns true if two spaces are adjacent to each other.
	public boolean adjacentSpaces(Space a, Space b) {
		// adjacent spaces will differ by at most 1 coordinate from each other
		if ((Math.abs(a.col() - b.col()) + (Math.abs(a.row() - b.row())) == 1)) {
			return true;
		}
		return false;
	}

	/**
	 * If less then 20 walls, it adds the individual wall to the boards list, and returns the result.
	 * @param wall the wall that is to be added to the list
	 * @return The result.
	 */
	
	/// Guys we will need to have this respond if the wall is not added, otherwise it will just skip over it.
	public boolean addWall(Wall wall) {
		if (wallList.size() < 20 && !existingWall(wall) && !wallOverLap(wall)) {
			wallList.add(wall);
			graph.addWall(wall);
			return true;
		} else {
			return false;
			//throw new RuntimeException("Invalid Wall");
		}
		
	}
	
	private boolean existingWall(Wall wall){
		return wallList.contains(wall);
	}
	
	private boolean wallOverLap(Wall wall){
		Wall tempWall;
		if(wallList.size() == 0) {
			return false;	
		} else if(wallList.contains(rotateWallOrientation(wall))){
			return true;
		} else if((wall.getSpace().col() == 1 && wall.isHorizontal()) || (wall.getSpace().row() == 1 && wall.isVertical())) {
			return false;
		} else if(wall.isVertical()){
				tempWall = new Wall(new Space(wall.getSpace().col(), wall.getSpace().row() - 1), wall.isVertical());
		} else {
				tempWall = new Wall(new Space(wall.getSpace().col() - 1, wall.getSpace().row()), wall.isVertical());
		}
		return (wallList.contains(tempWall));
	}
	
	private Wall rotateWallOrientation(Wall wall){
		Wall tempWall = new Wall(new Space(wall.getSpace().col(), wall.getSpace().row()), !wall.isVertical());
		assert(!wall.equals(tempWall));
		return tempWall;
	}

	private void removeWall(Wall wall) {
		wallList.remove(wall);
		graph.removeWall(wall);
	}


	public String toString() {
		return BoardPrinter.buildBoardString(this);
	}

	public void print() {
		System.out.println(this);
	}

	/**
	 * Moves are passed into the board here.
	 * makeMove() will check that the move is valid,
	 * then add it to moveList.
	 * @param move
	 */
	public boolean makeMove(Move move) {
		if (!moveValid(move)) return false; // Maybe this should throw an exception instead?
		if (moveList.size() > 0) {
			assert (moveListIndex >= 0);
			while (moveListIndex < moveList.size()-1)
				moveList.removeLast();
		}
		moveList.add(move);
		applyMove(move);
		return true;
	}

	/**
	 * Check that a move is valid with respect to the current board state.
	 * @param move
	 * @return True if the move is valid.
	 * TODO: Implementation.
	 */
	private boolean moveValid(Move move) {
		if (move instanceof MovementMove) {
			MovementMove mMove = (MovementMove) move;
			if (wallIsHere(mMove.from(), mMove.to()) && mMove.from().equals(currentPlayer.getSpace()) && !mMove.to().equals(players.other(currentPlayer).getSpace()))
		}
		if (move instanceof WallMove) {
			
		}
		return true;
	}

	/**
	 * Updates the board state with the move.
	 * Assumes that the move is valid.
	 * Used for new (checked) moves or redo.
	 * @param move
	 */
	private void applyMove(Move move) {
		if (move instanceof MovementMove) {
			move.owner.setSpace(((MovementMove) move).to());
		}
		if (move instanceof WallMove) {
			this.addWall(((WallMove) move).wall);
		}
	}

	/**
	 * Undo the last move.	 */
	public void undo() {
		if (moveListIndex < 0) return;
		Move move = moveList.get(moveListIndex);

		if (move instanceof MovementMove) {
			move.owner.setSpace(((MovementMove) move).from());
		}
		if (move instanceof WallMove) {
			this.removeWall(((WallMove) move).wall);
		}

		moveListIndex--;
	}

	/**
	 * Redo the last undone move.
	 */
	public void redo() {
		if (moveList.size() == 0 || moveListIndex == moveList.size()-1) return;

		moveListIndex++;
		applyMove(moveList.get(moveListIndex));
	}
}
