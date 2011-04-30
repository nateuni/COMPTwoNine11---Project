public class Player {

	private final String name;
	private final String token;
	private Space space;
	
	Player(String name, int x, int y ) {
		this.name = name;
		space = new Space(x, y);
		token = name.substring(0,1).toUpperCase();
	}
	
	public String getToken() {
		return token;
	}
	
	public String getName() {
		return name;
	}
	
	public Space getSpace() {
		return space;
	}
}
		