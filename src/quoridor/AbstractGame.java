package quoridor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public abstract class AbstractGame {
	protected Board board;
	public abstract void playGame();
	protected abstract boolean setupGame();
	protected abstract void playNextTurn();
}
