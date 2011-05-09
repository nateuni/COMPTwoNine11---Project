package quoridor;

import java.util.LinkedList;

public class Validator {

	AIGame sampleGame;
	// TODO complete this class using your project code
	// you must implement the no-arg constructor and the check method
	
	// you may add extra fields and methods to this class
	// but the ProvidedTests code will only call the specified methods
	
	public Validator() {
		sampleGame = new AIGame();
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
	public boolean check(String moves) {
		LinkedList<String> movesList = sampleGame.tokenizeString(moves);
		for(int i=0; i<movesList.size(); i++) {
			if(sampleGame.checkInput(movesList.get(i)) == false) {
				return false;
			}
		}
		return false;
	}

}