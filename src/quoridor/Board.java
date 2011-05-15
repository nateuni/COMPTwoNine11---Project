package quoridor;

import java.util.LinkedList;

/**
 * A board has Two Players and up to 20 placed walls (10 per player) and tracks each of their coordinates.
 * @author Team Stump
 */
public class Board {

	public Two<Player> players;
	private Player currentPlayer;
	private LinkedList<Wall> wallList = new LinkedList<Wall>();
	private LinkedList<Move> moveList = new LinkedList<Move>();
	private int moveListIndex = -1;
	static final Space player1Start = new Space("e1");
	static final Space player2Start = new Space("e9");
	Player winner = null;
	public BoardGraph graph = new BoardGraph();
	private final int MOVEMENT_MOVE = 2;
	private final int WALL_MOVE = 3;

	/**
	 * A board takes Two players as a pair as defined by the Two Class.
	 * @param players
	 * 			The Two players for the this board instance.
	 */
	public Board(Two<Player> players) {
		this.players = players;
		currentPlayer = players._1();
	}

	/**
	 * A constructor with no args - used for testing purposes
	 */
	public Board() {
		Player player1 = new AIPlayer(1);
		Player player2 = new AIPlayer(2);
		this.players = new Two<Player>(player1, player2);
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
	 *Switch the currentPlayer (for next player's turn) 
	 */
	public void nextPlayer() {
		currentPlayer = players.other(currentPlayer);
	}

	/**
	 * Tests if game has been won.
	 * @return true is game is over, false otherwise
	 */
	public boolean checkWin() {
		Player player1 = players._1();
		Player player2 = players._2();
		if (player1.getSpace().row() == player2Start.row()) {
			winner = player1;
			return true;
		} else if (player2.getSpace().row() == player1Start.row()) {
			winner = player2;
			return true;
		}
		return false;
	}

	
	/**
	 * accessor for current player
	 * @return currentPlayer
	 */
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
	 * Checks if a wall exists between two given spaces.
	 * @param space a, b the spaces that represent the junction being queried
	 * @return true if a wall exists between these two walls.
	 */
	public boolean wallIsHere(Space a, Space b) {
		if (!(adjacentSpaces(a, b))) {
			return false;
		}
		// swap them if out of order (a should always be above B /left of B)
		if ((b.col() < a.col()) || (b.row() < a.row())) {
			Space temp = a;
			a = b;
			b = temp;
		}
		LinkedList<Wall> toConsider = new LinkedList<Wall>();
		// obtain the walls that we need to consider in order for
		// a->b to be blocked by a wall.

		// for a,b to in same row
		if (b.equals(a.getRight())) {
			if (a.getDown() != null)
				toConsider.add(new Wall(a, true));
			if (a.getUp() != null)
				toConsider.add(new Wall(a.getUp(), true));
		}

		// for a,b in same column
		if (b.equals(a.getDown())) {
			if (a.getRight() != null)
				toConsider.add(new Wall(a, false));
			if (a.getLeft() != null)
				toConsider.add(new Wall(a.getLeft(), false));
		}
		// see if any of these walls actually exist
		while (toConsider.size() > 0) {
			if (wallList.contains(toConsider.remove())) {
				return true;
			}
		}
		return false;
	}


	
	/**
	 * Tests if space a is adjacent to space b on the board
	 * @param a
	 * @param b
	 * @return true if a and b are adjacent
	 */
	public boolean adjacentSpaces(Space a, Space b) {
		// adjacent spaces will differ by at most 1 coordinate from each other
		if ((Math.abs(a.col() - b.col()) + (Math.abs(a.row() - b.row())) == 1)) {
			return true;
		}
		return false;
	}

	public boolean wallOverlaps(Wall wall) {
		Space sideA = wall.getSpace();	//the top left space for this wall
		Space sideB = null;//the space on opposite side of wall to sideA
		if(wall.isHorizontal()) {
			sideB = sideA.getDown();
		}
		else {
			sideB = sideA.getRight();
		}
		return wallIsHere(sideA, sideB);
	}
	
	/**
	 * Adds a given wall to the board (assumes valid)
	 * @param wall the wall that is to be added to the list
	 */ 
	public void addWall(Wall wall) {
		wallList.add(wall);
		graph.addWall(wall);
		currentPlayer.decrementWallTally();
	}



	/**
	 * Checks whether a placed wall will cut off the path.
	 * @param wall
	 * @return
	 * TODO: Implementation
	 */
	private boolean cutsOffPath(Wall wall) {
		return false;
	}	

	/**
	 * Remove a previously played wall from the board
	 * @param wall
	 */
	private void removeWall(Wall wall) {
		wallList.remove(wall);
		graph.removeWall(wall);
		currentPlayer.incrementWallTally();
	}

	/**
	 * Returns a String representation of the board's current state
	 * for printing purposes
	 */
	public String toString() {
		return BoardPrinter.buildBoardString(this);
	}

	/**
	 * Print the board layout to console
	 */
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
			while (moveList.size() > moveListIndex + 1)
				moveList.removeLast();
		}
		moveList.add(move);
		moveListIndex++;
		applyMove(move);
		return true;
	}

	/**
	 * Check that a move is valid with respect to the current board state.
	 * @param move
	 * @return True if the move is valid.
	 * TODO: Make it throw exceptions for invalid moves.
	 */
	private boolean moveValid(Move move) {
		if (move instanceof MovementMove) {
			MovementMove mMove = (MovementMove) move;
			if (!wallIsHere(mMove.from(), mMove.to()) 
				&& mMove.from().equals(currentPlayer.getSpace()) 
				&& !isOccupied(mMove.to())){
			return true;
			}
			return false;
		}	
		if (move instanceof WallMove) {
			if(!currentPlayer.hasWallsLeft()) {
				return false;
			}
			WallMove wMove = (WallMove) move;
			Wall proposedWall = wMove.wall();
				if(proposedWall.isHorizontal()
				   &&!wallIsHere(proposedWall.getSpace(), proposedWall.getSpace().getDown())) {
					return true;
				}
				else if(!proposedWall.isHorizontal() 
						&&!wallIsHere(proposedWall.getSpace(), proposedWall.getSpace().getRight())) {
					if (cutsOffPath(wMove.wall())) return false;
					return true;
				}
			}
		return false;
	}
					
	/**
	 * Updates the board state with the move.
	 * Assumes that the move is valid.
	 * Used for new (validated) moves or redo.
	 * @param move to be played
	 */
	private void applyMove(Move move) {
		if (move instanceof MovementMove) {
			move.owner.setSpace(((MovementMove) move).to());
		}
		if (move instanceof WallMove) {
			this.addWall(((WallMove) move).wall());
		}
		currentPlayer = players.other(currentPlayer);
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
			this.removeWall(((WallMove) move).wall());
		}
		currentPlayer = players.other(currentPlayer);
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
	
	public String whosTurn(){
		return this.currentPlayer.toString();
	}
	
	public boolean makeMoveFromInput(String moveInput){
		Move move;
		if(moveInput.length() == MOVEMENT_MOVE){
			try {
				move = new MovementMove(this.currentPlayer.getSpace(), new Space(moveInput));
				move.owner = currentPlayer;
			} catch(Exception e) {
				return false;
			}
		} else if(moveInput.length() == WALL_MOVE && (moveInput.substring(2).equalsIgnoreCase("v") || moveInput.substring(2).equalsIgnoreCase("h"))) {
			try {
				move = new WallMove(new Wall(moveInput));
				move.owner = currentPlayer;
			} catch(Exception e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return this.makeMove(move);
	}
}
