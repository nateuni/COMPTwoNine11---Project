package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factory {

	private static Factory theFactory;

	public static Factory instance ()
	{
		if (theFactory == null) theFactory = new Factory();
		return theFactory;
	}

	public Game makeGame(String presetMoves) {
		if(presetMoves != null) {
			return new ValidatorGame(presetMoves);
		}
		return null;
	}

	/**
	 * Construct and return a new board with the specified types of players
	 * @param type - integer representation of the type of game: (human/AI player combinations)
	 * 1 - Human vs Human Game
	 * 2 - Play Human vs AI Game
	 * 3 - Play AI vs AI Game
	 * @return the new board
	 */
	public Board makeBoard(int type) {
		Player player1;
		Player player2;
		switch (type){

		// 0 for validator games
		case 0: player1 = new DeadPlayer(1);
		player2 = new DeadPlayer(2);
		break;
		case 1: player1 = new HumanPlayer(1);
		player2 = new HumanPlayer(2);
		break;
		case 2: player1 = new HumanPlayer(1);
		player2 = makeAIPlayer(2);
		break;
		case 3: player1 = makeAIPlayer(1);
		player2 = makeAIPlayer(2);
		break;
		default: return null;
		}
		return new Board(new Two<Player>(player1, player2));
	}

	Player makeAIPlayer(int playerNumber) {
		while(true) {
			System.out.println("Select difficulty level for player " + playerNumber + ":");
			System.out.println("1 - Easy");
			System.out.println("2 - Medium");
			System.out.println("3 - Hard");
			String input = getFromUser();
			int difficulty;
			try {
				difficulty = Integer.parseInt(input.substring(0,1));
			}
			catch( Exception e) {
				difficulty =0;
			}
			if(difficulty>0) {
				switch (difficulty){
				case 1: return new RandomAIPlayer(playerNumber);
				case 2: return new NoLookAIPlayer(playerNumber);
				case 3: return new MultipleLookAIPlayer(playerNumber);
				default:
				}
				System.out.println("Invalid input.");
			}
		}
	}

	Player makePlayer(int playerNumber, String playerType, String playerName, String playerToken){
		Player newPlayer = null;
		if(playerType.equalsIgnoreCase("quoridor.HumanPlayer")){
			return new HumanPlayer(playerNumber, playerName, playerToken);
		} else if(playerType.equalsIgnoreCase("quoridor.RandomAIPlayer")){
			newPlayer = new RandomAIPlayer(playerNumber);
		} else if(playerType.equalsIgnoreCase("quoridor.NoLookAIPlayer")) {
			newPlayer = new NoLookAIPlayer(playerNumber);
		} else if(playerType.equalsIgnoreCase("quoridor.MultipleLookAIPlayer")){
			newPlayer = new MultipleLookAIPlayer(playerNumber);
		}
		newPlayer.setName(playerName);
		newPlayer.setToken(playerToken);
		return newPlayer;
	}


	private String getFromUser() {
		try {
			while (true) {
				BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print(">");
				String fromUser = userReader.readLine();
				if (!fromUser.isEmpty()) { // never returns an empty string
					return fromUser;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
