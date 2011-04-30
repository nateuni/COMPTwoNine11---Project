public class Space {

	private int x;
	private int y;
	
	Space(int x, int y) {
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
}