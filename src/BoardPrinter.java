
public class BoardPrinter {

	public static void main(String[] args) {
		System.out.println(printBoard());
	}

	public static String printBoard() {
		StringBuilder boardString = new StringBuilder(" ----------------------------------- \n");
		
		// Traverse wall list to build 9x9 array

		int row, col;
		
		int [][] wallArray = new int [9][9];
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				wallArray[row][col] = 0;
			}
		}
		wallArray[0][0] = 1;
		wallArray[0][1] = 2;
		wallArray[0][3] = 2;
		wallArray[2][0] = 1;
		
		for (row = 0; row < 9; row++) {
			boardString.append("|");
			for (col = 0; col < 9; col++) {
				//if (player_is_here) boardString.append(player_token);
				//else boardString.append("  ");
				boardString.append("   ");
				
				if (col != 8) {
					if (wallArray[row][col] == 1 || row != 0 && wallArray[row-1][col] == 1) boardString.append("|");
					else boardString.append(":");
				}
			}
			boardString.append("|\n");
			
			if (row != 8) {
				boardString.append("|");
				for (col = 0; col < 9; col++) {
					if (wallArray[row][col] == 2 || col != 0 && wallArray[row][col-1] == 2) boardString.append("===");
					else boardString.append("...");
					
					if (col != 8) {
						if (wallArray[row][col] == 1) boardString.append("|");
						else if (wallArray[row][col] == 2) boardString.append("=");
						else boardString.append(" ");
					}
				}
				boardString.append("|\n");
			}
		}
		boardString.append(" ----------------------------------- \n");
		
		return boardString.toString();
	}
	
}
