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
	
	public Player(String name, int x, int y ) {
		this.name = name;
		space = new Space(x, y);
		token = name.substring(0,3).toUpperCase();
	}
	
	/**
	 * @return The player's token as a String
	 */
	public String getToken() {
		return token;
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
		