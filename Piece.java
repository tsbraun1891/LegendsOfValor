/**
 * Piece represents a player's piece in a game. A piece has an identifier that
 * keeps track of the 'type' of a piece and a description describing what the piece does.
 * A Piece also has an Actor that it represents within a game which can be used to
 * link the character and their state on the board.
 * @author Tanner Braun
 *
 */

public class Piece {
	// Identifier is what defines what a piece is
	private String identifier;
	private String description;	
	private Actor actor;
	private boolean isHero;
	
	/**
	 * Creates a piece with the given id and description
	 * @param id - the piece's identifier
	 * @param desc - a short description of the piece
	 */
	public Piece(Hero actor, String id, String desc) {
		identifier = id;
		description = desc;
		
		this.actor = actor;
		isHero = true;
	}
	
	public Piece(Actor actor, String id, String desc) {
		identifier = id;
		description = desc;
		
		this.actor = actor;
		isHero = false;
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
	
	public Actor getActor() {
		return this.actor;
	}
	
	public void setActor(Actor a) {
		this.actor = a;
	}
	
	public boolean isHero() {
		return this.isHero;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Piece) {
			Piece other = (Piece) o;
			// If their identifiers are the same, they are the same type of piece
			// Make sure to handle when they are upper or lower case of the same type
			if(other.getIdentifier().toLowerCase().equals(this.getIdentifier().toLowerCase()) && other.getActor().equals(this.getActor())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toString() {
		return "" + getIdentifier();
	}
}
