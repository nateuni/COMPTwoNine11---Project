
public class Wall {
	private Space space;
	private Boolean vertical;
	private Player owner;
	
	public Wall(Space space, Boolean vertical, Player owner) {
		this.space = space;
		this.vertical = vertical;
		this.owner = owner;
	}
	
	public Wall(int x, int y, Boolean vertical, Player owner) {
		this.space = new Space(x, y);
		this.vertical = vertical;
		this.owner = owner;
	}
	
	public Space getSpace() {
		return space;
	}
	
	public Boolean isVertical() {
		return vertical;
	}
	
	public Boolean isHorizontal() {
		return !vertical;
	}
	
	public Player getOwner() {
		return owner;
	}
}
