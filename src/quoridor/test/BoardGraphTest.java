package quoridor.test;

import java.util.ArrayList;

import quoridor.*;

public class BoardGraphTest {

	public static void main(String[] args) {
		//Two<Player> players = Two.two(new Player("Alice", 5, 9), new Player("Bob", 5, 1));
		Board board = new Board();

		System.out.println(board.addWall(new Wall(new Space(1,1), false)));
		System.out.println(board.addWall(new Wall(new Space(2,1), false)));
		System.out.println(board.addWall(new Wall(new Space(2,1), true)));
		System.out.println(board.addWall(new Wall(new Space(1,1), true)));
		System.out.println(board.addWall(new Wall(new Space(1,2), true)));
		System.out.println(board.addWall(new Wall(new Space(1,7), false)));

		BoardPrinter.setStyle(1);
		board.print();
		
		ArrayList<Space> fin = new ArrayList<Space>();
		for (int i=1; i<=9; i++) fin.add(new Space (i,1));
		System.out.println(board.graph.findShortestPath(new Space(5, 9), fin));
	}

}
