package quoridor;

/**
 * An AI player that picks a random move.
 * @author Team Stump
 */
public class RandomAIPlayer extends AIPlayer {

	public RandomAIPlayer(int playerNumber) {
		super(playerNumber);
	}

	public Move getMove(Board board) {
		Move move = null;
		boolean valid = false;
		while (!valid) {
			try {
				if (Math.random() < 0.6) {
					// Do a movement move
					double dir = Math.random();
					if      (dir < 0.25) move = new MovementMove(this.getSpace(), this.getSpace().getUp());
					else if (dir < 0.5)  move = new MovementMove(this.getSpace(), this.getSpace().getRight());
					else if (dir < 0.75) move = new MovementMove(this.getSpace(), this.getSpace().getDown());
					else                 move = new MovementMove(this.getSpace(), this.getSpace().getLeft());
				}
				else {
					// Do a wall move
					int row = (int) Math.floor(Math.random() * 9);
					int col = (int) Math.floor(Math.random() * 9);
					boolean vertical = (Math.random() < 0.5);
					move = new WallMove(new Wall(new Space(col, row), vertical));
				}
				valid = board.moveValid(move);
			}
			catch (RuntimeException e) {
				valid = false;
			}
		}
		return move;
	}
}
