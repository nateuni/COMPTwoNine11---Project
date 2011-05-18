
package quoridor;

	public class Factory {

	protected static Factory theFactory;
	    
	private static Factory make() {
		theFactory = new Factory();
		return theFactory;
	}
	
	public static Factory instance ()
	{
		if (theFactory == null) make();
		return theFactory;
	}
	
	public Game makeGame() {
		return new ConsoleGame();
	}

	public Game makeGame(String presetMoves) {
		if(presetMoves != null) {
			return new ValidatorGame(presetMoves);
		}
		return null;
	}

	public Board makeBoard() {
		Player player1 = new AIPlayer(1);
		Player player2 = new AIPlayer(2);
		return new Board(new Two<Player>(player1, player2));
	}
	/**
	 * Construct and return a new board with the specified types of players
	 * @param type - integer representation of the type of game: (human/AI player combinations)
	 * 1 - Human vs Human Game
	 * 2 - Play Human vs AI Game
	 * 3 - Play AI vs AI Game
	 * @return the new board
	 */
	public Board makeBoard(int type) {
		Player player1;
		Player player2;
		switch (type){
		case 1: player1 = new HumanPlayer(1);
		player2 = new HumanPlayer(2);
		break;
		case 2: player1 = new HumanPlayer(1);
		player2 = new AIPlayer(2);
		break;
		case 3: player1 = new AIPlayer(1);
		player2 = new AIPlayer(2);
		break;
		default: return null;
		}
		return new Board(new Two<Player>(player1, player2));
	}
}
