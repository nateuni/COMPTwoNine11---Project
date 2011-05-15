package quoridor.test;
import quoridor.*;

public class TestGameInterface {
		
		public static void main(String[] args) {
			if(Validator.check("e2 e3h e3 e8 d3 e7 d6h d7 b6h c3h a7v")) {
				System.out.print("Move sequence is all good");
			} else {
				System.out.print("Invalid Move sequence");
			}
		}
	}

