import java.util.ArrayList;

/**
 * The Player class represents a player within a game.  Every player has a name,
 * and may have a piece that represents them within the game.
 * @author Tanner Braun
 *
 */
public class Player {
	
	private String playerName;
	private Piece primaryPiece;
	private ArrayList<Piece> pieces;
	
	public Player(String name) {
		playerName = name;
		pieces = new ArrayList<>();
	}
	
	public Player(String name, Piece piece) {
		primaryPiece = piece;
		playerName = name;
		pieces = new ArrayList<>();
		pieces.add(piece);
	}
	
	public Player(String name, ArrayList<Piece> pieces) {
		primaryPiece = pieces.get(0);
		playerName = name;
		this.pieces = pieces;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Piece getPlayerPiece() {
		return primaryPiece;
	}
	
	public ArrayList<Piece> getPlayerPieces() {
		return pieces;
	}

	public void setPlayerPiece(Piece playerPiece) {
		if(pieces.contains(playerPiece)) {
			this.primaryPiece = playerPiece;
		} else {
			System.err.println("Player does not have specified piece!");
		}
		
	}
	
	/**
	 * Add a piece to the player's list of pieces
	 * @param piece
	 */
	public void addPlayerPiece(Piece piece) {
		pieces.add(piece);
	}
	
	/**
	 * Remove a piece from the player
	 * @param piece
	 */
	public void removePlayerPiece(Piece piece) {
		pieces.remove(piece);
	}

	public boolean equals(Object o) {
		if(o instanceof Player) {
			Player other = (Player)o;
			if(other.getPlayerName() == this.getPlayerName()) {
				return true;
			}
		}
		
		return false;
	}
	
}
