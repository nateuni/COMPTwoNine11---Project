package quoridor.test;

import java.util.ArrayList;

import quoridor.*;

public class BoardGraphTest {

	public static void main(String[] args) {
		Two<Player> players = Two.two(new Player("Alice", 5, 9), new Player("Bob", 5, 1));
		Board board = new Board(players);

		board.addWall(new Wall (new Space (2,2), false));
		board.addWall(new Wall (new Space (5,8), false));
		board.addWall(new Wall (new Space (7,8), false));
		board.addWall(new Wall (new Space (4,1), false));
		board.print();
		
		BoardGraph bg = new BoardGraph();
		bg.addWall(new Wall (new Space (2,2), false));
		bg.addWall(new Wall (new Space (5,8), false));
		bg.addWall(new Wall (new Space (7,8), false));
		bg.addWall(new Wall (new Space (4,1), false));
		ArrayList<Space> fin = new ArrayList<Space>();
		fin.add(new Space (0,0));
		fin.add(new Space (1,0));
		fin.add(new Space (2,0));
		fin.add(new Space (3,0));
		fin.add(new Space (4,0));
		fin.add(new Space (5,0));
		fin.add(new Space (6,0));
		fin.add(new Space (7,0));
		fin.add(new Space (8,0));
		System.out.println(bg.findShortestPath(new Space(4, 8), fin));
	}

}
