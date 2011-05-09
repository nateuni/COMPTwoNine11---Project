package quoridor;

public class AIGame extends AbstractGame{

	@Override
	public void playGame() {
		// TODO Auto-generated method stub	
	}

	@Override
	protected boolean setupGame() {
		board = new Board(" X ", " O ");
		return true;
	}

	@Override
	protected boolean playNextTurn() {
		// TODO Auto-generated method stub
		return false;
	}
}
