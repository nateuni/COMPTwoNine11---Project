package quoridor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class Validator {

	private static Queue<String> q = null;
	
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
		breakUpMoves(moves);
		//Pass queue to game and play game.
		return false;
	}
	
	private static void breakUpMoves(String string){
		q = new LinkedList<String>();
		Pattern p = Pattern.compile("[\\s]");
		String[] result = p.split(string);
		for(int i = 0; i < result.length; i++){
			q.add(result[i]);
		}
	}
}