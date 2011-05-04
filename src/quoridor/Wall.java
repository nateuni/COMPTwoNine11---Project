package quoridor;

public class Wall {
	private Space space;
	private Boolean vertical;
	
	public Wall(Space space, Boolean vertical) {
		this.space = space;
		this.vertical = vertical;
	}
	
	public Wall(int x, int y, Boolean vertical) {
		this.space = new Space(x, y);
		this.vertical = vertical;
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
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Wall)) return false;
		Wall otherWall = (Wall) obj;
		return (this.getSpace().equals(otherWall.getSpace()) && this.isVertical() == otherWall.isVertical());
	}
}
