package quoridor;

public class Space {

    public final int col;
    public final int row;

    public Space(String coords) {
        assert (coords.length() == 2);
        String alpha = coords.substring(0, 1);
        this.col = alphaToInt(alpha);
        String numeric = coords.substring(1);
        this.row = Integer.parseInt(numeric);
    }

    public Space(String alpha, int numeric) {
        this.col = alphaToInt(alpha);
        this.row = numeric;
    }

    public Space(int col, int row) {
        this.col = col;
        this.row = row;
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

    //obtain the space above
    public Space getUp() {
        if (row == 9) {
            return null;
        }
        return new Space(col, row + 1);
    }

    //obtain the space below
    public Space getDown() {
        if (row == 1) {
            return null;
        }
        return new Space(col, row - 1);
    }

    //obtain the space to the left
    public Space getLeft() {
        if (col == 1) {
            return null;
        }
        return new Space(col - 1, row);
    }

    //obtain the space to the right
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
    
	public String toString() {
		return "[" + col + ", " + row + "]";
	}
}
