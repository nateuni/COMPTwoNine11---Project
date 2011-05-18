package quoridor.test;

import quoridor.*;

public class BoardPrinterTest {

	public static void main(String[] args) {
		//Two<Player> players = Two.two(new Player("Alice", 5, 9), new Player("Bob", 5, 1));
		Board board = Factory.instance().makeBoard(3);
		board.addWall(new Wall (new Space (2,2), false));
		board.addWall(new Wall (new Space (4,1), false));
		/*
		board.addWall(1, 0, false);
		board.addWall(3, 0, false);
		board.addWall(0, 3, true);
		board.addWall(3, 7, false);
		board.addWall(5, 7, false);
		board.addWall(4, 7, true);
		board.addWall(7, 4, true);
		board.addWall(7, 6, true);
		board.addWall(7, 5, false);
		*/
		BoardPrinter.setStyle(0);
		board.print();
		BoardPrinter.setStyle(1);
		board.print();
	}
}
