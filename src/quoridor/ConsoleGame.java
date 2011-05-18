package quoridor;													

public class ConsoleGame extends Game {

	/**
	 *Constructor
	 *Will only return once game has been set up and is ready to play 
	 */
	ConsoleGame() {
		System.out.printf("Welcome to Quoridor!");
		while (!setUp());
		playGame();
	}
	
	/**
	 * Determines the type of players needed for this game (AI, human)
	 * Sets the board/players according to the user's specification
	 * @return true once set up is complete.
	 */
	private Boolean setUp() {
		while(!this.menu());
		return true;
	}	

		/**
		 * Displays the game menu and allows players to select the type
		 * of game they wish to play. A board will be constructed based on this input
		 * 
		 * @return true if a valid board was constructed, false otherwise
		 */
		protected boolean menu(){
			int selection = 0;
			while(selection != 5) {
				System.out.print("Please select your choice: \n" +
						"1 - Play Human vs Human Game\n" +
						"2 - Play Human vs AI Game\n" +
						"3 - Play AI vs AI Game\n" +
						"4 - Load previously saved game\n" +
						"5 - Quit\n");
				selection = Integer.parseInt(getFromUser());
				switch(selection){
					case 1: case 2: case 3: board = Factory.instance().makeBoard(selection);
					case 4: board = this.load(); break;
					case 5: System.exit(0); break;
					default: System.out.println("Invalid Input"); 
				}
				if(selection >= 1 && selection < 4 && board != null){
					return true;
				}
			}
			return false;
		}
	
	protected void playNextTurn() {	 
		System.out.println(board.whoseTurn()+"'s Turn:");
		board.makeMoveFromInput(this.getFromUser());
		if (board.checkWin() != 0) {
			gameOver = true;
			System.out.println(board);
			if (board.checkWin() == 1) System.out.println(board.players._1().getName() + " wins!");
			else System.out.println(board.players._2().getName() + " wins!");
		}
	}
}