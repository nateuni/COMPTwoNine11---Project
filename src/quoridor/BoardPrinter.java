package quoridor;

public class BoardPrinter {

	static final String V_WALL = "|";
	static final String H_WALL = "=";
	static final String V_DIVIDER = ":";
	static final String H_DIVIDER_0 = "...";
	static final String H_DIVIDER_1 = "---";
	static final String CORNER_0 = " ";
	static final String CORNER_1 = "+";
	
	static int style = 0;
	
	public static void setStyle(int newStyle) {
		style = newStyle;
	}
	
	private static String getHDivider() {
		if (style == 1) return H_DIVIDER_1;
		return H_DIVIDER_0;
	}

	private static String getCorner() {
		if (style == 1) return CORNER_1;
		return CORNER_0;
	}

	public static String buildBoardString(Board board) {
		StringBuilder boardString = new StringBuilder("  -----------------------------------\n");
		Space thisSpace;
		int row, col, rowNum = 1;

		String hDivider = getHDivider();
		String corner = getCorner();
		
		// Create array for walls
		int [][] wallArray = new int [9][9];
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				wallArray[row][col] = 0;
			}
		}

		// Fill array with wall locations
		for (Wall wall : board.getWallList()) {
			row = wall.getSpace().row;
			col = wall.getSpace().col;
			if (wall.isVertical()) wallArray[row][col] = 1;
			else wallArray[row][col] = 2;
		}

		for (row = 0; row < 9; row++) {
			// Print row of spaces
			boardString.append(rowNum+"|");
			rowNum++;
			for (col = 0; col < 9; col++) {
				thisSpace = new Space(col+1, row+1);
				
				if      (board.getPlayers()._1().getSpace().equals(thisSpace)) boardString.append(board.getPlayers()._1().getToken());
				else if (board.getPlayers()._2().getSpace().equals(thisSpace)) boardString.append(board.getPlayers()._2().getToken());
				else boardString.append("   ");
				
				if (col != 8) {
					if (wallArray[row][col] == 1 || row != 0 && wallArray[row-1][col] == 1) boardString.append(V_WALL);
					else boardString.append(V_DIVIDER);
				}
			}
			boardString.append("|\n");
			
			// Print divider between rows
			if (row != 8) {
				boardString.append(" |");
				for (col = 0; col < 9; col++) {
					if (wallArray[row][col] == 2 || col != 0 && wallArray[row][col-1] == 2) boardString.append(H_WALL + H_WALL + H_WALL);
					else boardString.append(hDivider);
					
					if (col != 8) {
						if (wallArray[row][col] == 1) boardString.append(V_WALL);
						else if (wallArray[row][col] == 2) boardString.append(H_WALL);
						else boardString.append(corner);
					}
				}
				boardString.append("|\n");
			}
		}
		
		boardString.append("  -----------------------------------\n   a   b   c   d   e   f   g   h   i\n");
		
		return boardString.toString();
	}
	
}
