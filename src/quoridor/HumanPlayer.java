package quoridor;

public class HumanPlayer extends Player {

	public HumanPlayer(int playerNumber) {
		super(playerNumber);
	}

	public Move getMove() {
		return new Move();
	}
}
