package quoridor.test;

import java.util.ArrayList;

import quoridor.*;

public class GraphFillTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board();

		board.addWall(new Wall(new Space(4, 7), false));
		board.addWall(new Wall(new Space(6, 7), false));
		board.addWall(new Wall(new Space(8, 7), false));
		board.addWall(new Wall(new Space(7, 8), true));
		
		ArrayList<Space> exits = new ArrayList<Space>();
		for (int i = 1; i <=9; i++) exits.add(new Space(i, 1));
		board.graph.fillNodeDistances(exits);
		board.print();
		System.out.println();
		board.graph.printDistanceFills();
	}

}
