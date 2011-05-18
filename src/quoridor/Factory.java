
package quoridor;

	public class Factory
	{

	protected static Factory theFactory;
	    
	    public static Factory instance ()
	    {
	        return theFactory;
	    }
		
	    public Game makeGame(String presetMoves) {
	    	if(presetMoves != null) {
	    		return new ValidatorGame(presetMoves);
	    	}
	    	else {
	    		return new ConsoleGame();
	    	}
	    	
	    }
	    
	    /**
	     * Construct and return a new board with the specified types of players
	     * @param type - integer representation of the type of game: (human/AI player combinations)
	     * 1 - Human vs Human Game
 	     * 2 - Play Human vs AI Game
  	     * 3 - Play AI vs AI Game
	     * @return the new board
	     */
	    public Board makeBoard(int type) {
			Player player1 =null;
	    	Player player2 =null;
			switch (type){
			case 1: player1 = new HumanPlayer(1);
				   player2 = new HumanPlayer(2);
				   break;
			case 2: player1 = new HumanPlayer(1);
				   player2 = new AIPlayer(2);
				   break;
			case 3: case 0: player1 = new AIPlayer(1);
				   			player2 = new AIPlayer(2);
				   break;
		//	default: return null;
	    }
			if(player1== null || player2 == null) {
				System.out.println("Could not construct players");
				return null;
			}
			return new Board(new Two<Player>(player1, player2));
	    }
}
