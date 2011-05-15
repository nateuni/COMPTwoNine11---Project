package quoridor;

/**
 * @author Stump
 * Contains all the information that is relevant to a player. 
 * Thus being - Their name, their token as to be displayed on the board and their current space. 
 */
public abstract class Player {
	private String name;
	private String token;
	private Space space;
	private int wallsLeft = 10;

	/**
	 * Constructor for a default player
	 * @param playerNumber can be of value 1 or 2 - for default filling of fields: name, token. 
	 */
	public Player(int playerNumber) {
		if (playerNumber == 1) {
			name = "Player 1";
			token = " X ";
			this.space = new Space("e1");
		}
		else if (playerNumber == 2) {
			token = " O ";
			name = "Player 2";
			this.space = new Space("e9");
		}
		else throw new RuntimeException("Invalid player number");
	}

	protected void setToken(String t) {
		if (t.length() == 0) return;
		else if (t.length() == 1) token = " " + t + " ";
		else if (t.length() == 2) token = t + " ";
		else if (t.length() == 3) token = t;
		else token = t.substring(0, 3);
	}

	/**
	 * @return The player's token as a String
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Checks if this player has reached their quota of walls
	 * @return boolean true is player can still place walls
	 */
	public Boolean hasWallsLeft() {
		return (wallsLeft > 0); 
	}

	public Boolean decrementWallTally() {
		if(this.hasWallsLeft()){
			this.wallsLeft--;
			return true;
		}
		return false;
	}

	//used for undo'ing
	public boolean incrementWallTally(){
		if(this.wallsLeft < 20){
			wallsLeft++;
			return true;
		}
		return false;
	}

	/**
	 * @return The player's name as a String 
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @return The players current Space as a pointer to a Space object. 
	 */
	public Space getSpace() {
		return space;
	}

	public void setSpace(Space newSpace) {
		space = newSpace;
	}
	
	/**
	 * Obtain the next moved to be played by this player.
	 * Implementation will differ depending on instance of player object
	 * @return move to be played
	 */
	public abstract Move getMove();

	public String toString() {
		return name;
	}

}
