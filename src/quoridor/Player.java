package quoridor;

/**
 * @author Stump
 * Contains all the information that is relevant to a player. 
 * Thus being - Their name, their token as to be displayed on the board and their current space. 
 */
public abstract class Player {
	protected String name;
	protected String token;
	protected Space space;
	int wallsLeft = 10;

	public Player(int playerNumber) {
		if (playerNumber == 1) {
			name = " X ";
			this.space = new Space("e1");
		}
		else {
			name = " O ";
			this.space = new Space("e9");
		}
		setToken(name);
	}

	protected void setToken(String t) {
		this.token = t;
	}

	/**
	 * @return The player's token as a String
	 */
	public String getToken() {
		return token;
	}

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

	/**
	 * @return The players current Space as a pointer to a Space object. 
	 */
	public Space getSpace() {
		return space;
	}

	public void setSpace(Space newSpace) {
		space = newSpace;
	}

	public abstract Move getMove();

	public String toString() {
		return name;
	}

}
