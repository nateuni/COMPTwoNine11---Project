package quoridor;

/**
 * @author Stump
 * A wall can be either vertical or horizontal as it's location is defined by a space. 
 */
public class Wall {
	private Space space;
	private Boolean vertical;
	
	/**
	 * Sets the type and coordinates of the wall. 
	 * @param x The x coordinate as an int. 
	 * @param y The y coordinate as an int. 
	 * @param vertical If the wall is vertical or horizontal as defined by a boolean.
	 */
	public Wall(Space space, Boolean vertical) {
		this.space = space;
		this.vertical = vertical;
	}

        public Wall(String command) {
                String wallType = command.substring(0,1).toUpperCase();
                if(wallType.equals("H")) {
                    this.vertical = false;
                }
                if(wallType.equals("V")) {
                    this.vertical = true;
                }
                String spaceCoords = command.substring(1);
                this.space = new Space(spaceCoords);
        }

	/**
	 * @return The space from which the wall is located.
	 */
	public Space getSpace() {
		return space;
	}
	
	/**
	 * @return If the wall is vertical or not.
	 */
	public Boolean isVertical() {
		return vertical;
	}
	
	/**
	 * @return If the wall is horizontal or not.
	 */
	public Boolean isHorizontal() {
		return !vertical;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Wall)) return false;
		Wall otherWall = (Wall) obj;
		return (this.getSpace().equals(otherWall.getSpace()) && this.isVertical() == otherWall.isVertical());
	}
}
