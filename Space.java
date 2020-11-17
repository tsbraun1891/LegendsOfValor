/**
 * Space represents a space on a game board.  It keeps track of any pieces on the space
 * and allows users to add and remove pieces from the space.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;

public class Space {
	private ArrayList<Piece> currentPieces;
	private int spaceSize;
	
	// Creates a space that can hold spaceSize pieces at once
	public Space(int spaceSize) {
		currentPieces = new ArrayList<Piece>();
		this.spaceSize = spaceSize;
	}
	
	// Creates a space that can hold unlimited pieces
	public Space() {
		currentPieces = new ArrayList<Piece>();
		this.spaceSize = Integer.MAX_VALUE;
	}
	
	/**
	 * Puts a given piece on this space
	 * @param piece - the piece to be placed on this space
	 * @return returns 1 on successful placement, and 0 on illegal move
	 */
	public int addPiece(Piece piece) {
		if(currentPieces.size() < spaceSize) {
			currentPieces.add(piece);
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Removes all instances of the given piece from this space
	 * @param removePiece - the piece to be removed
	 * @return whether or not the piece was successfully removed
	 */
	public boolean removePiece(Piece removePiece) {
		
		return currentPieces.remove(removePiece);
	}
	
	public ArrayList<Piece> getCurrentPieces() {
		return currentPieces;
	}

	public int getSpaceSize() {
		return spaceSize;
	}
	
	public boolean isEmpty() {
		return this.currentPieces.isEmpty();
	}

	public String toString() {
		return currentPieces.toString();
	}
}
