package quoridor;

public class Space {

	public final int col;
	public final int row;
	
	public Space(String coords) {
		assert(coords.length() == 2);
		String alpha = coords.substring(0,1);
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
	
	public String getAlpha() {
		return intToAlpha(col);
	}
	
	public int getNumeric()  {
		return row;
	}
	
	private String intToAlpha(int number) {
		number = 'a' - 1;  //convert int to its ascii decimal representation
		return Integer.toString(number);
	}
	
	public int alphaToInt(String alpha) {
		char alphaChar = alpha.charAt(0);  
		int alphaAsInt = alphaChar - 'a' + 1; 
		return alphaAsInt;
	}		

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override 
	public boolean equals(Object obj) {
		if (!(obj instanceof Space)) return false;
		Space otherSpace = (Space) obj;
		return (this.col == otherSpace.col && this.row == otherSpace.row);
	}
}