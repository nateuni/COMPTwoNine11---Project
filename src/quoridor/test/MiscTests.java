package quoridor.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import quoridor.Factory;
import quoridor.Game;
import quoridor.Player;

public class MiscTests {

	@Test
	public void gamesAreEqual(){
		Game game1 = Factory.instance().makeGame("d9 f1 a1h");
		Game game2 = Factory.instance().makeGame("d9 f1 a1h");
		game1.playGame();
		game2.playGame();
		assertTrue(game1.equals(game2));
	}
	
	@Test
	public void gamesAreNotEqual(){
		Game game1 = Factory.instance().makeGame("d9 f1 a1h");
		Game game2 = Factory.instance().makeGame("d9");
		game1.playGame();
		game2.playGame();
		assertFalse(game1.equals(game2));
	}
	
	@Test
	public void savedAndLoadedGameAreEqual(){
		String testString1 = "d9 f1 a1h g1 a2h h1 a3h i1 a4h i2 a5h i3";
		String testString2 = "d9 f1 a1h g1 a2h h1";
		Game game = Factory.instance().makeGame(testString1);
		assertTrue(game.playGame());
		game.save("test");
		Game newGame = Factory.instance().makeGame(testString2);
		newGame.load("test");
		newGame.playGame();
		game.equals(newGame);
	}
	
	@Test
	public void playersHaveTheSameDetails(){
		//Player p1 = Factory.instance().makePlayer(1, "quoridor.HumanPlayer", "testPlayer", "tst");
		//Player p2 = Factory.instance().makePlayer(2, "quoridor.HumanPlayer", "testPlayer", "tst");
		//Game game = new Game();
	}

}
