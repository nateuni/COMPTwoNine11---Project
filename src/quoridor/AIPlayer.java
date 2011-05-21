package quoridor;

public abstract class AIPlayer extends Player {

	/**Constructor
	 * @param playerNumber delegates player1 or player2 default construction
	 */
	public AIPlayer(int playerNumber) {
		super(playerNumber);
	}
}
