package quoridor.test;

import java.util.ArrayList;

import quoridor.*;

public class BoardGraphTest {

	public static void main(String[] args) {
		//Two<Player> players = Two.two(new Player("Alice", 5, 9), new Player("Bob", 5, 1));
		Board board = new Board();

		board.addWall(new Wall (new Space (2,2), false));
		board.addWall(new Wall (new Space (5,8), false));
		board.addWall(new Wall (new Space (7,8), false));
		board.addWall(new Wall (new Space (4,1), false));
		board.print();
		
		ArrayList<Space> fin = new ArrayList<Space>();
		for (int i=1; i<=9; i++) fin.add(new Space (i,1));
		System.out.println(board.graph.findShortestPath(new Space(5, 9), fin));
	}

}
