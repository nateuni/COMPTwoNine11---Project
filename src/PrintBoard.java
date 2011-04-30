import java.util.List;

public class PrintBoard {

	/*  Board Example
		"   --------------------------------------------\n" +
		"9 |    :    :  1 :  2 :    :    :    :    :    |\n" +
		"  |========= ========= .... .... .... .... ....|\n" +
		"8 |    |    |    :    :    :    :    :    :    |\n" +
		"  |....|....|.... .... .... .... .... .... ....|\n" +
		"7 |    |    |    :    :    :    :    :    :    |\n" +
		"  |.... .... ========= .... .... .... .... ....|\n" +
		"6 |    |    :    :    :    :    :    :    :    |\n" +
		"  |....|.... .... .... .... .... .... .... ....|\n" +
		"5 |    |    :    :    :    :    :    :    :    |\n" +
		"  |.... .... .... .... .... .... .... .... ....|\n" +
		"4 |    |    :    :    :    :    :    :    :    |\n" +
		"  |....|.... .... .... .... .... .... .... ....|\n" +
		"3 |    |    :    :    :    :    :    :    :    |\n" +
		"  |.... .... .... .... .... .... .... .... ....|\n" +
		"2 |    :    :    :    :    :    :    :    :    |\n" +
		"  |.... .... .... .... .... .... .... .... ....|\n" +
		"1 |    :    :    :    :    :    :    :    :    |\n" +
		"   --------------------------------------------\n" +
		"     A    B    C    D    E    F    G   H    I  \n";
		
	*/	

	private final Two<Player> PLAYERS;
	private final List<Wall> WALL_LIST;
	
	private final String TOP = "   --------------------------------------------\n";
	private final String BOTTOM = "   --------------------------------------------\n     A    B    C    D    E    F    G   H    I  \n";
	
	private final String INBETWEEN_DIVIDER_LEFT = "|....";
	private final String INBETWEEN_DIVIDER_LEFT_WALL = "|=========";
	private final String INBETWEEN_DIVIDER_OTHER = " ....";
	private final String INBETWEEN_DIVIDER_OTHER_WALL = " =========";
	
	// note space with vertical wall is the same as a left space.
	
	private final String EMPTY_LEFT_SPACE = "|    ";
	private final String EMPTY_OTHER_SPACE = ":    ";
	
	private final String PLAYER1_LEFT_SPACE;
	private final String PLAYER1_OTHER_SPACE;
	
	private final String PLAYER2_LEFT_SPACE;
	private final String PLAYER2_OTHER_SPACE;
	
	//static int [] [] boardArray = new int [9][9];
	
	public PrintBoard(Board board){
		this.PLAYERS = board.getPlayers();
		this.WALL_LIST = board.getList();
		this.PLAYER1_LEFT_SPACE = "|  ".concat(this.PLAYERS._1.getToken()).concat(" ");
		this.PLAYER1_OTHER_SPACE = ":  ".concat(this.PLAYERS._1.getToken()).concat(" ");
		this.PLAYER2_LEFT_SPACE = "|  ".concat(this.PLAYERS._2.getToken()).concat(" ");
		this.PLAYER2_OTHER_SPACE = ":  ".concat(this.PLAYERS._2.getToken()).concat(" ");
	}
	
	// * Reminder make sure when in the if statement that prints a H.wall to increment the column by an extra 1.
	private void printSquares() {
		Space playersSpace = new Space(0,0);
		Space wallSpace = new Space(0,0);
		
		System.out.print(this.TOP);
		for(int row = 18; row >= 1; row--){
			for (int column = 1; column <= 9; column++){
				
				if (row % 2 == 0) {
					if (column == 1) {
						
					} else if (column ==)
					//playersSpace.setCoords(column, row / 2);
					//if(playersSpace.equals(PLAYERS._1.getSpace())) {
					//	System.out.print(s);
					//}
				// reminder append "  " to all divider rows	
				} else {
					System.out.print(s);
				}
			}
		}
		System.out.print(this.BOTTOM);
	}
	
	
	private void printRows(){
		
	}
	
	private void printColumns(){
		
	}
	
	void printBoard() {
		this.printSquares();
	}
}
