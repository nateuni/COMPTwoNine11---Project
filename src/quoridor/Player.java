package quoridor;

/**
 * @author Stump
 * Contains all the information that is relevant to a player. 
 * Thus being - Their name, their token as to be displayed on the board and their current space. 
 */
public class Player {
	private final String name;
	private final String token;
	private Space space;
	int wallsLeft = 10;
	
	Player(String name, Space space, String token ) {
		this.name = name;
		this.space = space;
		this.token = token;
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
}
		