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
	
	public boolean equals(Space other){
		if(this.x == other.x && this.y == other.y) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getAlpha() {
		switch(this.getX()) {
			case 1: return "a"; 
			case 2: return "b";
			case 3: return "c"; 
			case 4: return "d"; 
			case 5: return "e"; 
			case 6: return "f"; 
			case 7: return "g"; 
			case 8: return "h"; 
			case 9: return "i"; 
			default: return "Error x coordinate is out of bounds";
		}
	}
	
	public String getAlphaNumeric() {
		return (this.getAlpha()+this.getY());
	}
	
	//test
	public static void main(String[] args) {
		Space space = new Space (0,1);
		System.out.println(space.getAlphaNumeric());
		space.setCoords(1, 5);
		System.out.println(space.getAlphaNumeric());
	}
}