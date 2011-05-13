package quoridor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class Validator {
	
	private Queue<String> q;

	Game sampleGame;
	// TODO complete this class using your project code
	// you must implement the no-arg constructor and the check method
	// you may add extra fields and methods to this class
	// but the ProvidedTests code will only call the specified methods
	
	public Validator() {
		sampleGame = new Game();
		q = new LinkedList<String>();
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
	
	public static boolean check(String moveString){
		Pattern p = Pattern.compile("[\\s]");
		String[] result = p.split(moveString);
		for(int i = 0; i < result.length; i++){
			q.add(result[i]);
			System.out.println(result[i]);
		}
		
		return false;
	}
}