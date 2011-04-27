
public class PrintTest {

	final String EXAMPLE = 
		" --------------------------------------------\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|  O :    :    :    :    :    :    :    : X  |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		"| - - - - - - - - - - - - - - - - - - - - - -|\n" +
		"|    :    :    :    :    :    :    :    :    |\n" +
		" --------------------------------------------\n";

	static final String PLAYER1 = "⟶";
	static final String PLAYER2 = "⟵";
	
	static final String TOP_AND_BOTTOM = " --------------------------------------------\n";
	static final String INBETWEEN_DIVIDER = "| - - - - - - - - - - - - - - - - - - - - - -|\n";
	
	static final String EMPTY_LEFT_EDGE = "|    ";
	static final String EMPTY_MIDDLE_SPACE = ":    ";
	static final String EMPTY_RIGHT_EDGE = ":    |\n";
	
	static final String PLAYER1_LEFT_EDGE = "|  ⟶ ";
	static final String PLAYER1_MIDDLE_SPACE = ":  ⟶ ";
	static final String PLAYER1_RIGHT_EDGE = ":  ⟶ |\n";
	
	static final String PLAYER2_LEFT_EDGE = "| ⟵  ";
	static final String PLAYER2_MIDDLE_SPACE = ": ⟵  ";
	static final String PLAYER2_RIGHT_EDGE = ": ⟵  |\n";
	
	static int [] [] boardArray = new int [9][9];
	
	public static void main(String[] args) {
		
		boardArray[4][0] = 1;
		boardArray[4][8] = 2;
		
		
		System.out.print(TOP_AND_BOTTOM);
		for(int i= 0; i < 9; i++) {
			if(i > 0) System.out.print(INBETWEEN_DIVIDER);
			for (int j = 0; j < 9; j++){
				if(boardArray[i][j] == 0) {
					if(j == 0) 
						System.out.print(EMPTY_LEFT_EDGE);
					else if (j == 8)
						System.out.print(EMPTY_RIGHT_EDGE);
					else
						System.out.print(EMPTY_MIDDLE_SPACE);
				} else if (boardArray[i][j] == 1) {
					if(j == 0) 
						System.out.print(PLAYER1_LEFT_EDGE);
					else if (j == 8)
						System.out.print(PLAYER1_RIGHT_EDGE);
					else
						System.out.print(PLAYER1_MIDDLE_SPACE);
				} else {
					if(j == 0) 
						System.out.print(PLAYER2_LEFT_EDGE);
					else if (j == 8)
						System.out.print(PLAYER2_RIGHT_EDGE);
					else
						System.out.print(PLAYER2_MIDDLE_SPACE);
				}
			}
		}
		System.out.print(TOP_AND_BOTTOM);
	}
}
