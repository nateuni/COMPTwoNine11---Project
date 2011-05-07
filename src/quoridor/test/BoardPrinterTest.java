package quoridor.test;

import quoridor.*;

public class BoardPrinterTest {

	public static void main(String[] args) {
		 Two<Player> players = Two.two(new Player("Alice", 5, 9), new Player("Bob", 5, 1));
		 Board board = new Board(players);
		
		//I am changing the add wall class to take an actual wall. And being lazy about editing below.  
		/* 
		board.addWall(0, 0, true);
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
