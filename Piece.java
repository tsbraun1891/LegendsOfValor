/**
 * Piece represents a player's piece in a game. A piece has an identifier that
 * keeps track of the 'type' of a piece and a description describing what the piece does.
 * @author Tanner Braun
 *
 */

public class Piece {
	// Identifier is what defines what a piece is
	private String identifier;
	private String description;	
	
	/**
	 * Creates a piece with the given id and description
	 * @param id - the piece's identifier
	 * @param desc - a short description of the piece
	 */
	public Piece(String id, String desc) {
		identifier = id;
		description = desc;
	}
	
	public Piece(String id) {
		identifier = id;
		description = "";
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public boolean equals(Object o) {
		if(o instanceof Piece) {
			Piece other = (Piece) o;
			// If their identifiers are the same, they are the same type of piece
			// Make sure to handle when they are upper or lower case of the same type
			if(other.getIdentifier().toLowerCase().equals(this.getIdentifier().toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toString() {
		return "" + getIdentifier();
	}
}
