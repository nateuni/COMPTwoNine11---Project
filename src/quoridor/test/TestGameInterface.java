package quoridor.test;
import quoridor.*;

public class TestGameInterface {
		
		public static void main(String[] args) {
			if(Validator.check("e2 e3h e3 e8 d3 e7 d6h d7 b6h c3h a7v e7 e3 f7 f3 g7 g3 g6 g4 g5 f4 g4 f5 g3 f4 g2 f5 g1 f6")) {
				System.out.print("Move sequence is all good");
			} else {
				System.out.print("Invalid Move sequence");
			}
		}
	}

