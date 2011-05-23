package quoridor;

import java.util.ArrayList;

public class BoardNode {

	public Space space;
	public BoardNode up;
	public BoardNode right;
	public BoardNode down;
	public BoardNode left;
	public ArrayList<BoardNode> path;
	public int distanceToExit;
	
	public BoardNode(Space space) {
		this.space = space;
		up = null;
		right = null;
		down = null;
		left = null;
		path = new ArrayList<BoardNode>();
		distanceToExit = -1;
	}
}
