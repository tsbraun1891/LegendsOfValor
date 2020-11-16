
public class LegendBoard extends Board{
	private int playerRow, playerCol;
	private Piece playerPiece;
	
	public LegendBoard(int width, int height, Player player) {
		super();
		spaces = new Space[height][width];

		// We now know that all spaces will be of type LegendSpace
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double randNum = Math.random();
				
				if(randNum <= .5) {
					Space newSpace = new LegendSpace(LegendSpace.SpaceType.COMMON);
					spaces[i][j] = newSpace;
				} else if(randNum <= .8) {
					Space newSpace = new LegendSpace(LegendSpace.SpaceType.MARKET);
					spaces[i][j] = newSpace;
				} else {
					Space newSpace = new LegendSpace(LegendSpace.SpaceType.INACCESSIBLE);
					spaces[i][j] = newSpace;
				}
				
			}
		}
		
		this.playerPiece = player.getPlayerPiece();
		
		// Place the player's starting piece
		this.placePieceAtStart(playerPiece);		
	}
	
	/**
	 * @param i - row of the space
	 * @param j - column of the space
	 * @return type of the space at the specified coordinates. Returns null if coordinates are out of bounds
	 */
	public LegendSpace.SpaceType getTypeOfSpace(int i, int j) {
		// If you have a LegendBoard instance, the spaces must be of type LegendSpace and can therefore be casted to it
		if( i >= 0 && i < spaces.length && j >= 0 && j < spaces[i].length) {
			LegendSpace space = (LegendSpace) spaces[i][j];
			return space.getType();
		} else {
			return null;
		}		
	}
	
	/**
	 * Places the given piece at the bottom leftmost corner of the map that is not INACCESSIBLE
	 * @param piece
	 */
	public void placePieceAtStart(Piece piece) {
		
		for(int i = spaces.length-1; i >= 0; i--) {
			for(int j = 0; j < spaces[i].length; j++) {
				if(this.getTypeOfSpace(i, j) != LegendSpace.SpaceType.INACCESSIBLE) {
					this.addPiece(piece, i, j);
					playerRow = i;
					playerCol = j;
					return;
				}
			}
		}
	}
	
	/**
	 * Tries to move the party to the specified square
	 * @param row
	 * @param col
	 * @return false if the space being moved to is inaccessible
	 */
	public boolean movePartyTo(int row, int col) {
		LegendSpace.SpaceType type = this.getTypeOfSpace(row, col);
		if(type != null && type != LegendSpace.SpaceType.INACCESSIBLE) {
			this.removePiece(playerPiece, playerRow, playerCol);
			
			this.addPiece(playerPiece, row, col);
			
			this.playerRow = row;
			this.playerCol = col;
			
			return true;
			
		} else {
			return false;
		}
	}
	
	public int playerRow() {
		return playerRow;
	}
	
	public int playerCol() {
		return playerCol;
	}

	public String toString() {		
		String rowline = "\n+";
		for(int i = 0; i < this.getWidth(); i++) {
			rowline += "-------+";
		}
		rowline += "\n";

		String rhet = "\n\n";
		for(int i = 0; i < this.getHeight(); i++) {
			rhet += rowline + "|";
			
			// Each row is 3 lines high
			// Top left corner denotes type of the square
			for(int j = 0; j < this.getWidth(); j++) {
				switch(this.getTypeOfSpace(i, j)) {
				case COMMON:
					rhet += "C      |";
					break;
					
				case MARKET:
					rhet += "M      |";
					break;
					
				case INACCESSIBLE:
					rhet += "I      |";
					break;
				}		
			}
			
			rhet += "\n|";
			for(int j = 0; j < this.getWidth(); j++) {
				if(this.getPieces(i, j).isEmpty()) {
					rhet += "       |";
				} else {
					rhet += "   " + this.getPieces(i, j).get(0) + "   |";
				}				
			}
			
			rhet += "\n|";			
			for(int j = 0; j < this.getWidth(); j++) {
				rhet += "       |";			
			}
		}
		
		rhet += rowline;
		
		rhet += "\n\n";
		
		rhet += "Party Position: " + this.playerRow + ", " + this.playerCol;
		
		return rhet;		
	}
}
