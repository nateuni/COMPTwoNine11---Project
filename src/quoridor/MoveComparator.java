package quoridor;

import java.util.Comparator;

public class MoveComparator implements Comparator<Move> {

	public MoveComparator() {
	}
	
	public int compare(Move move1, Move move2) {
		if (move1.awesomeness > move2.awesomeness) return -1;
		else if (move1.awesomeness < move2.awesomeness) return 1;
		else {
//			if (Math.random() > 0.5) return 1;
//			else return -1;
			return 0;
		}
	}
}
