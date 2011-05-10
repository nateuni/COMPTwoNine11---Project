package quoridor;

import java.util.ArrayList;
import java.util.List;

public class BoardGraph {
	private BoardNode [][] node;
	
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
	
	public void addWall(Wall wall) {
		int row = wall.getSpace().row();
		int col = wall.getSpace().col();
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
	
	public void removeWall(Wall wall) {
		int row = wall.getSpace().row();
		int col = wall.getSpace().col();
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
	public List<Space> findShortestPath(Space start, List<Space> finish) {
		ArrayList<BoardNode> currentNodes = new ArrayList<BoardNode>();
		ArrayList<BoardNode> nextNodes = new ArrayList<BoardNode>();
		ArrayList<Space> shortestPath = new ArrayList<Space>();
		BoardNode finalNode = null;
		BoardNode checkNode;
		int row, col;
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				node[row][col].path.clear();
			}
		}
		BoardNode startNode = node[start.row() - 1][start.col() - 1];
		startNode.path.add(startNode);
		currentNodes.add(startNode);
		
		while (finalNode == null && currentNodes.size() > 0) {
			nextNodes.clear();
			for (BoardNode thisNode : currentNodes) {
				if (finish.contains(thisNode.space)) {
					finalNode = thisNode;
					break;
				}
				checkNode = tryNode(thisNode.up, thisNode.path);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.right, thisNode.path);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.down, thisNode.path);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.left, thisNode.path);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
			}
			currentNodes = (ArrayList<BoardNode>) nextNodes.clone();
		}
		
		if (finalNode == null) return null;
		for (BoardNode thisNode : finalNode.path) {
			shortestPath.add(thisNode.space);
		}
		return shortestPath;
	}

	@SuppressWarnings("unchecked")
	private BoardNode tryNode(BoardNode thisNode, ArrayList<BoardNode> pathSoFar) {
		if (thisNode == null) return null;
		if (thisNode.path.size() != 0 && pathSoFar.size() >= thisNode.path.size() + 1) return null;
		thisNode.path = (ArrayList<BoardNode>) pathSoFar.clone();
		thisNode.path.add(thisNode);
		return thisNode;
	}
}
