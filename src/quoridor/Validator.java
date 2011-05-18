package quoridor;

public class Validator {

	Game sampleGame;
	
	public Validator() {
		sampleGame = new Game();
	}

	/**
	 * Check the validity of a given sequence of moves.
	 * The sequence is valid if and only if each (space separated)
	 * move in the list is valid,
	 * starting from the initial position of the game.
	 * When the game has been won, no further moves are valid.
	 * @param moves a list of successive moves
	 * @return validity of the list of moves
	 */
	
	public static boolean check(String moves){
		Game game = Factory.instance().makeGame(moves);
		return game.playGame();
	}
}