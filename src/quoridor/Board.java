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
     * Checks the space is within the bounds of the board
     * @return The result.
     */
    private boolean withinBounds(Space space) {
        if (space.row > 0 && space.row <= 9 && space.col > 0 && space.col <= 9) {
            return true;
        }
        return false;
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
        if ((b.col < a.col) || (b.row < a.row)) {
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
        if ((Math.abs(a.col - b.col) + (Math.abs(a.row - b.row)) == 1)) {
            return true;
        }
        return false;
    }

    /**
     * If less then 20 walls, it adds the individual wall to the boards list, and returns the result.
     * @param wall the wall that is to be added to the list
     * @return The result.
     */
    public boolean addWall(Wall wall) {
        if (wallList.size() < 20) {
            wallList.add(wall);
            return true;
        }
        return false;
    }

    public String toString() {
        return BoardPrinter.buildBoardString(this);
    }

    public void print() {
        System.out.println(this);
    }
}
