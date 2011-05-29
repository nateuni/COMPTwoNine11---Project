package quoridor;

import java.util.Comparator;

public class MinimaxComparator implements Comparator<Move> {

	Space enemySpace = new Space(5, 5);

	public MinimaxComparator() {
	}

	public MinimaxComparator(Space enemySpace) {
		setEnemySpace(enemySpace);
	}

	@Override
	public int compare(Move move1, Move move2) {
		if (move1 instanceof MovementMove && move2 instanceof MovementMove) return 0;
		else if (move1 instanceof MovementMove && move2 instanceof WallMove) return -1;
		else if (move1 instanceof WallMove && move2 instanceof MovementMove) return 1;
		else if (move1 instanceof WallMove && move2 instanceof WallMove) {
			Wall wall1 = ((WallMove)move1).wall();
			Wall wall2 = ((WallMove)move2).wall();
			double distDiff = distance(enemySpace, wall1) - distance(enemySpace, wall2);
			if (distDiff < 0) return -1;
			if (distDiff > 0) return 1;
			return 0;
		}
		return 0;
	}

	public void setEnemySpace(Space enemySpace) {
		this.enemySpace = enemySpace;
	}
	
	private double distance(Space space, Wall wall) {
		double colDiff = (double) space.col() - (double) wall.getSpace().col() + 0.5;
		double rowDiff = (double) space.row() - (double) wall.getSpace().row() + 0.5;
		return Math.sqrt(colDiff * colDiff + rowDiff * rowDiff);
	}
}
