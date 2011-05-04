package quoridor;

public class Space {

	private int x;
	private int y;
	
	public Space(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoords(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY()  {
		return y;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Space)) return false;
		Space otherSpace = (Space) obj;
		return (this.x == otherSpace.x && this.y == otherSpace.y);
	}
}