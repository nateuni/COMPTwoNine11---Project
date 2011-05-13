
package quoridor;

/**
 * An abstract class for quoridor moves.
 * @author Team Stump
 */
public abstract class Move {
	public Player owner;
	
	public void setOwner(Player player) {
		this.owner = player;
	}
	
	public Player getOwner() {
		return owner;
	}
}
