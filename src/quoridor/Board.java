package quoridor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A board has Two Players and up to 20 placed walls (10 per player) and tracks each of their coordinates.
 * @author Team Stump
 */
public class Board implements Serializable {

	/**
	 * Default UID for saving board
	 */
	private static final long serialVersionUID = 1L;
	public Two<Player> players;
	protected Player currentPlayer;
	protected LinkedList<Wall> wallList = new LinkedList<Wall>();
	protected LinkedList<Move> moveList = new LinkedList<Move>();
	protected int moveListIndex = -1;
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
	 * @return 1 is game is won by player 1, 2 if won by player 2, 0 otherwise
	 */
	public int checkWin() {
		Player player1 = players._1();
		Player player2 = players._2();
		if (player1.getSpace().row() == player2Start.row()) {
			winner = player1;
			return 1;
		} else if (player2.getSpace().row() == player1Start.row()) {
			winner = player2;
			return 2;
		}
		return 0;
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
	private boolean cutsOffPath(WallMove move) {
		boolean blocks = false;
		int i;
		addWall(move.wall());
		ArrayList<Space> exits = new ArrayList<Space>();
		
		for (i=1; i<=9; i++) exits.add(new Space (i, player2Start.row()));
		graph.fillNodeDistances(exits);
		if (graph.getDist(players._1().getSpace()) == -1) blocks = true;

		for (i=1; i<=9; i++) exits.add(new Space (i, player1Start.row()));
		graph.fillNodeDistances(exits);
		if (graph.getDist(players._2().getSpace()) == -1) blocks = true;
		
		removeWall(move.wall());
		return blocks;
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
	 * Moves are passed into this function.
	 * makeMove() will check that the move is valid,
	 * then add it to moveList.
	 * @param move
	 */
	public boolean makeMove(Move move) {
		if (!moveValid(move)) throw new RuntimeException("Invalid move");
		if (moveList.size() > 0) {
			assert (moveListIndex >= 0);
			while (moveList.size() > moveListIndex + 1)
				moveList.removeLast();
		}
		move.owner = currentPlayer;
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
	public boolean moveValid (Move move) {
		if (move instanceof MovementMove) {
			MovementMove mMove = (MovementMove) move;
			if (!wallIsHere(mMove.from(), mMove.to()) 
					&& mMove.from().equals(currentPlayer.getSpace()) 
					&& !isOccupied(mMove.to()) 
					&& (adjacentSpaces(mMove.from(), mMove.to()) || jumpValid(mMove))) {
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
			if (wallList.contains(new Wall(wMove.wall().getSpace(), !wMove.wall().isVertical()))) return false;
			if (proposedWall.isHorizontal()
					&&!wallIsHere(proposedWall.getSpace(), proposedWall.getSpace().getDown())
					&&!wallIsHere(proposedWall.getSpace().getRight(), proposedWall.getSpace().getDown().getRight())) {
				if (cutsOffPath((WallMove) move)) return false;
				return true;
			}
			else if (!proposedWall.isHorizontal()
					&&!wallIsHere(proposedWall.getSpace(), proposedWall.getSpace().getRight())
					&&!wallIsHere(proposedWall.getSpace().getDown(), proposedWall.getSpace().getRight().getDown())) {
				if (cutsOffPath((WallMove) move)) return false;
				return true;
			}
		}
		return false;
	}
					
	/**
	 * Checks that a jump move is valid.
	 * If the move is a regular (adjacent) move, returns true.
	 * @param move
	 * @return True if valid jump move
	 */
	private boolean jumpValid(MovementMove move) {
		if (!move.isJump()) return true;
		Space middleSpace;
		Space behindSpace;
		// Horizontal jump move
		if (move.from().row() == move.to().row()) {
			assert (Math.abs(move.from().col() - move.to().col()) == 2);
			middleSpace = new Space(Math.min(move.from().col(), move.to().col()) + 1, move.from().row());
			if (players.other(move.owner).getSpace().equals(middleSpace)
					&& !wallIsHere(move.from(), middleSpace)
					&& !wallIsHere(move.to(), middleSpace)) {
				return true;
			}
			return false;
		}
		// Vertical jump move
		else if (move.from().col() == move.to().col()) {
			assert (Math.abs(move.from().row() - move.to().row()) == 2);
			middleSpace = new Space(move.from().col(), Math.min(move.from().row(), move.to().row()) + 1);
			if (players.other(move.owner).getSpace().equals(middleSpace)
					&& !wallIsHere(move.from(), middleSpace)
					&& !wallIsHere(move.to(), middleSpace)) {
				return true;
			}
			return false;
		}
		// Diagonal jump move
		else if (Math.abs(move.from().row()-move.to().row()) + Math.abs(move.from().col()-move.to().col()) == 2) {
			if (players.other(move.owner).getSpace().row() == move.from().row()
					&& Math.abs(players.other(move.owner).getSpace().col() - move.from().col()) == 1
					|| players.other(move.owner).getSpace().col() == move.from().col()
					&& Math.abs(players.other(move.owner).getSpace().row() - move.from().row()) == 1) {
				middleSpace = players.other(move.owner).getSpace();
				if (wallIsHere(middleSpace, move.to())) return false;
				try {
					// Try to construct the space behind the opponent
					behindSpace = new Space(2 * middleSpace.col() - move.from().col(), 2 * middleSpace.row() - move.from().row());
				}
				catch (RuntimeException e) {
					// Behind opponent is the edge of the board - treat as wall
					return true;
				}
				return wallIsHere(middleSpace, behindSpace);
			}
			else return false;
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
		if (moveListIndex < 0) throw new RuntimeException("No moves to undo");
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
		if (moveList.size() == 0 || moveListIndex == moveList.size()-1) throw new RuntimeException("No moves to redo");

		moveListIndex++;
		applyMove(moveList.get(moveListIndex));
	}
	
	public String whoseTurn(){
		return this.currentPlayer.toString();
	}
	
	public void makeMoveFromInput(String moveInput) {
		Move move;
		if (moveInput.equalsIgnoreCase("undo")) {
			undo();
		} else if(moveInput.equalsIgnoreCase("redo")) {
			redo();
		} else if (moveInput.length() >= 7 && moveInput.substring(0, 6).equalsIgnoreCase("style ")) {
			BoardPrinter.setStyle(moveInput.substring(6, 7));
		} 
		else {	// Regular move
			if (moveInput.length() == MOVEMENT_MOVE) {
				move = new MovementMove(this.currentPlayer.getSpace(), new Space(moveInput));
			} else if (moveInput.length() == WALL_MOVE) {
				move = new WallMove(new Wall(moveInput));
			} else {
				throw new RuntimeException("Invalid input");
			}
			makeMove(move);
		}
	}

	@SuppressWarnings("unchecked")
	public Board clone() {
		Board cloneBoard = new Board(this.players);
		cloneBoard.wallList = (LinkedList<Wall>) this.wallList.clone();
		cloneBoard.moveList = (LinkedList<Move>) this.moveList.clone();
		cloneBoard.moveListIndex = this.moveListIndex;
		cloneBoard.winner = this.winner;
		cloneBoard.graph = new BoardGraph();
		for (Wall wall : wallList) {
			cloneBoard.graph.addWall(wall);
		}
		return cloneBoard;
	}
}
