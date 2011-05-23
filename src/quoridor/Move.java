
package quoridor;

import java.io.Serializable;

/**
 * An abstract class for quoridor moves.
 * @author Team Stump
 */
public abstract class Move implements Serializable  {

	private static final long serialVersionUID = 1L;

	/**
	 * How good the move is. (To be used fleetingly by the AI algorithms.)
	 */
	public double awesomeness;
	
	public Player owner;

	/**
	 * Sets the owner for this move object
	 * @param player
	 */
	public void setOwner(Player player) {
		this.owner = player;
	}
	
	/**
	 * Accessor for the owner of this move object
	 * @return player owner
	 */
	public Player getOwner() {
		return owner;
	}
}
