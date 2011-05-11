package quoridor;

public class AIGame extends AbstractGame{

	@Override
	public void playGame() {
		// TODO Auto-generated method stub	
	}

	@Override
	protected boolean setupGame() {
		board = new Board(new Two<Player>(new AIPlayer(1), new AIPlayer(2)));
		return true;
	}

	@Override
	protected void playNextTurn() {
		// TODO Auto-generated method stub
		//return false;
	}
}
