package quoridor;

import java.lang.Math;

/**
 * Represents a move from one space to another.
 * @author Team Stump
 */
public class MovementMove extends Move {
	private Space from;
	private Space to;
	private boolean isJump;
	
	public MovementMove(Space from, Space to) {
		if (!this.validate(from, to)) {
        	throw new RuntimeException("Not a valid quoridor move");
		}
		else {
			this.from = from;
			this.to = to;
		}
	}
	
	/**
	 * Checks that the move is a potentially valid quoridor move.
	 * This does NOT guarantee that the move is valid in the current game state.
	 * Also checks whether the move is a jump move.
	 * @param from The Space moved from.
	 * @param to The Space moved to.
	 * @return True if move is valid.
	 */
	private boolean validate(Space from, Space to) {
		if (from.equals(to)) return false;
		if (Math.abs(from.row()-to.row()) <= 1 && Math.abs(from.col()-to.col()) <= 1) {
			if (from.row()==to.row() || from.col()==to.col()) isJump = false;
			else isJump = true;
			return true;
		}
		if (from.row()==to.row() && Math.abs(from.col()-to.col()) == 2
		 || from.col()==to.col() && Math.abs(from.row()-to.row()) == 2) {
			isJump = true;
			return true;
		}
		return false;
	}
	
	public Space from() {
		return from;
	}
	
	public Space to() {
		return to;
	}
	
	public boolean isJump() {
		return isJump;
	}
}
