package quoridor;

/**
 * @author Stump
 * A space is to be used by both the Player and Wall classes as a means to store their location.
 */
public class Space {

	private int x;
	private int y;
	
	public Space(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the xy coordinates of the space.
	 * @param newX the x coordinate as an int.
	 * @param newY the y coordinate as an int.
	 */
	public void setCoords(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	/**
	 * @return The x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return The y coordinate
	 */
	public int getY()  {
		return y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override 
	public boolean equals(Object obj) {
		if (!(obj instanceof Space)) return false;
		Space otherSpace = (Space) obj;
		return (this.x == otherSpace.x && this.y == otherSpace.y);
	}
}