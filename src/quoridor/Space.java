package quoridor;

public class Space {

	private final int col;
    private final int row;
    private final int ASCII_DIFFERENCE = 96;

    /**
     * @param coords
     * @exception throws a RuntimeException if the space is out of bounds
     */
    public Space(String coords) {
        assert (coords.length() == 2);
        String alpha = coords.substring(0, 1);
        String numeric = coords.substring(1);
        if(!withinBounds(alphaToInt(alpha),Integer.parseInt(numeric))){
        	throw new RuntimeException("Space is not within bounds of the board");
        } else {
        	this.col = alphaToInt(alpha);
            this.row = Integer.parseInt(numeric);
        }
    }

    
    /**
     * @param alpha
     * @param numeric
     * @exception throws a RuntimeException if the space is out of bounds
     */
    public Space(String alpha, int numeric) {
        if(!withinBounds(alphaToInt(alpha), numeric)){
        	throw new RuntimeException("Space is not within bounds of the board");
        } else {
        	this.col = alphaToInt(alpha);
            this.row = numeric;
        }
    }

    /**
     * @param col
     * @param row
     * @exception throws a RuntimeException if the space is out of bounds
     */
    public Space(int col, int row) {
        if(!withinBounds(col, row)){
        	throw new RuntimeException("Space is not within bounds of the board");
        } else {
        	this.col = col;
            this.row = row;
        }
    }

    public int col() {
        return col;
    }

    public int row() {
        return row;
    }

    public int alphaToInt(String alpha) {
        char alphaChar = alpha.charAt(0);
        int alphaAsInt = alphaChar - 'a' + 1;
        return alphaAsInt;
    }

    
    /**
     * obtain the space above
     * @return space above this object
     */
    public Space getUp() {
        if (row == 1) {
            return null;
        }
        return new Space(col, row - 1);
    }

    /**
     * obtain the space below
     * @return space below this object
     */
    public Space getDown() {
        if (row == 9) {
            return null;
        }
        return new Space(col, row + 1);
    }

    /**
     * obtain the space to the left
     * @return space to left of this object
     */
    public Space getLeft() {
        if (col == 1) {
            return null;
        }
        return new Space(col - 1, row);
    }

    /**
     * obtain the space to the right
     * @return space to right of this object
     */
    public Space getRight() {
        if (col == 9) {
            return null;
        }
        return new Space(col + 1, row);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Space)) {
            return false;
        }
        Space otherSpace = (Space) obj;
        return (this.col == otherSpace.col && this.row == otherSpace.row);
    }
    
    @Override
	public String toString() {
		return "[" + col + ", " + row + "]";
	}
	
	public String toOriginalString(){
		int ascii = col + ASCII_DIFFERENCE;
		String alpha = Character.toString((char) ascii);	
		return alpha+row;
	}
	
	 /**
     * Checks the space is within the bounds of the board
     * @return The result.
     */
    private boolean withinBounds(int col, int row) {
        if (row > 0 && row <= 9 && col > 0 && col <= 9) {
            return true;
        }
        return false;
    }
}
