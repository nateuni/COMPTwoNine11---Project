package quoridor;

import java.util.ArrayList;
import java.util.List;

/**
 * A graph structure representing the spaces on the board.
 * Used for finding shortest paths.
 */

public class BoardGraph {
	protected BoardNode [][] node;
	
	/**
	 * Constructs a BoardGraph for a new board with no walls.
	 */
	public BoardGraph() {
		int row, col;
		node = new BoardNode [9][9];
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				node[row][col] = new BoardNode(new Space(col + 1, row + 1));
			}
		}
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				if (row == 0) node[row][col].up = null;
				else node[row][col].up = node[row-1][col];
				if (row == 8) node[row][col].down = null;
				else node[row][col].down = node[row+1][col];
				if (col == 0) node[row][col].left = null;
				else node[row][col].left = node[row][col-1];
				if (col == 8) node[row][col].right = null;
				else node[row][col].right = node[row][col+1];
			}
		}
	}
	
	/**
	 * Removes edges between nodes that have been separated by a wall.
	 * @param wall The wall that has been added.
	 */
	public void addWall(Wall wall) {
		int row = wall.getSpace().row() - 1;
		int col = wall.getSpace().col() - 1;
		assert(row>=0 && row<9 && col>=0 && col<9);
		if (wall.isVertical()) {
			node[row][col].right = null;
			node[row+1][col].right = null;
			node[row][col+1].left = null;
			node[row+1][col+1].left = null;
		}
		else {
			node[row][col].down = null;
			node[row][col+1].down = null;
			node[row+1][col].up = null;
			node[row+1][col+1].up = null;
		}
	}
	
	/**
	 * Restores edges between nodes when a wall is removed (with undo).
	 * @param wall The removed wall.
	 */
	public void removeWall(Wall wall) {
		int row = wall.getSpace().row() - 1;
		int col = wall.getSpace().col() - 1;
		assert(row>=0 && row<9 && col>=0 && col<9);
		if (wall.isVertical()) {
			node[row][col].right = node[row][col+1];
			node[row+1][col].right = node[row+1][col+1];
			node[row][col+1].left = node[row][col];
			node[row+1][col+1].left = node[row+1][col];
		}
		else {
			node[row][col].down = node[row+1][col];
			node[row][col+1].down = node[row+1][col+1];
			node[row+1][col].up = node[row][col];
			node[row+1][col+1].up = node[row][col+1];
		}
	}
	
	@SuppressWarnings("unchecked")
	public void fillNodeDistances(List<Space> exits) {
		ArrayList<BoardNode> currentNodes = new ArrayList<BoardNode>();
		ArrayList<BoardNode> nextNodes = new ArrayList<BoardNode>();
		BoardNode checkNode;
		int row, col;
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				node[row][col].distanceToExit = -1;
			}
		}
		for (Space space : exits) {
			node[space.row()-1][space.col()-1].distanceToExit = 0;
			currentNodes.add(node[space.row()-1][space.col()-1]);
		}
		
		while (currentNodes.size() > 0) {
			nextNodes.clear();
			for (BoardNode thisNode : currentNodes) {
				checkNode = tryNode(thisNode.up, thisNode.distanceToExit);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.right, thisNode.distanceToExit);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.down, thisNode.distanceToExit);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.left, thisNode.distanceToExit);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
			}
			currentNodes = (ArrayList<BoardNode>) nextNodes.clone();
		}
	}

	private BoardNode tryNode(BoardNode thisNode, int distance) {
		if (thisNode == null) return null;
		if (thisNode.distanceToExit != -1 && distance + 1 >= thisNode.distanceToExit) return null;
		thisNode.distanceToExit = distance + 1;
		return thisNode;
	}
	
	public void printDistanceFills() {
		int row, col;
		for (row = 0; row < 9; row++) {
			System.out.print(" ");
			for (col = 0; col < 9; col++) {
				System.out.printf("%4d", node[row][col].distanceToExit);
			}
			System.out.println();
		}
	}
	
	public int getDist(Space space) {
		return node[space.row()-1][space.col()-1].distanceToExit;
	}
}
