import java.util.ArrayList;
import java.util.HashMap;

public class LegendBoard extends Board{
	private ArrayList<Piece> playerPieces, monsterPieces;
	private int size;
	private HashMap<Piece, Integer> pieceRows, pieceCols;
	
	public LegendBoard(Player player) {
		super();
		size = 8;
		spaces = new Space[size][size];
		

		// We now know that all spaces will be of type LegendSpace
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				
				
				if(col == 2 || col == 5) {
					Space newSpace = new LegendSpace(LegendSpace.SpaceType.INACCESSIBLE);
					spaces[row][col] = newSpace;
				} else if(row == 0 || row == spaces.length-1) {
					Space newSpace = new LegendSpace(LegendSpace.SpaceType.NEXUS);
					spaces[row][col] = newSpace;
				} else {
					double randNum = Math.random();
					
					if(randNum <= .4) {
						Space newSpace = new LegendSpace(LegendSpace.SpaceType.PLAIN);
						spaces[row][col] = newSpace;
					} else if(randNum <= .6) {
						Space newSpace = new LegendSpace(LegendSpace.SpaceType.BUSH);
						spaces[row][col] = newSpace;
					} else if(randNum <= .8) {
						Space newSpace = new LegendSpace(LegendSpace.SpaceType.CAVE);
						spaces[row][col] = newSpace;
					} else {
						Space newSpace = new LegendSpace(LegendSpace.SpaceType.KOULOU);
						spaces[row][col] = newSpace;
					}
				}
			}
		}
		
		this.monsterPieces = new ArrayList<Piece>();
		this.playerPieces = player.getPlayerPieces();
		this.pieceRows = new HashMap<Piece, Integer>();
		this.pieceCols = new HashMap<Piece, Integer>();
		
		// Place the player's starting piece
		this.placePiecesAtStart();		
	}
	
	/**
	 * @param row - row of the space
	 * @param col - column of the space
	 * @return type of the space at the specified coordinates. Returns null if coordinates are out of bounds
	 */
	public LegendSpace.SpaceType getTypeOfSpace(int row, int col) {
		// If you have a LegendBoard instance, the spaces must be of type LegendSpace and can therefore be casted to it
		if( row >= 0 && row < spaces.length && col >= 0 && col < spaces[row].length) {
			LegendSpace space = (LegendSpace) spaces[row][col];
			return space.getType();
		} else {
			return null;
		}		
	}
	
	/**
	 * Places the given piece at the bottom leftmost corner of the map that is not INACCESSIBLE
	 * @param piece
	 */
	public void placePiecesAtStart() {
		
		// Place each piece in its own row starting from the left lane
		for(int i = 0; i < playerPieces.size(); i++) {
			this.placePiece(playerPieces.get(i), spaces.length-1, i*3);
		}
	}
	
	/**
	 * Tries to move the given piece to the specified square
	 * @param piece
	 * @param row
	 * @param col
	 * @return false if the space being moved to is inaccessible
	 */
	public boolean movePieceTo(Piece piece, int row, int col) {
		
		LegendSpace.SpaceType type = this.getTypeOfSpace(row, col);
		if(type != null && type != LegendSpace.SpaceType.INACCESSIBLE) {
			
			LegendSpace space = (LegendSpace) spaces[row][col];
			
			// If no one is on the space, you are clear to move
			if(space.isEmpty()) {
				this.removePiece(piece, this.getPieceRow(piece), this.getPieceCol(piece));
				
				this.placePiece(piece, row, col);
				
				return true;
			} 
			
			// There is at least one hero on this space, so a hero cannot move here
			if(piece.isHero() && space.hasHeroOnSpace()) {
				return false;
			}
			
			// There is at least one actor on this space and it is not a hero, so monsters cannot move here
			if(!piece.isHero() && !space.hasHeroOnSpace()) {
				return false;
			}
			
			
			this.removePiece(piece, this.getPieceRow(piece), this.getPieceCol(piece));
			
			this.placePiece(piece, row, col);		
			
			return true;
			
		} else {
			return false;
		}
	}
	
	/**
	 * Does the same functionality as parent and also updates the map linking pieces to positions
	 */
	public int placePiece(Piece piece, int x, int y) {
		int success = super.placePiece(piece, x, y);	
		
		
		if(success != 0) {
			this.pieceRows.put(piece, x);	
			this.pieceCols.put(piece, y);
			
		} 
		
		return success;
	}
	
	/**
	 * Checks if attacker is at most 1 space away or diagonal from their intended target
	 * @param attacker
	 * @param target
	 * @return
	 */
	public boolean inAttackRange(Piece attacker, Piece target) {
		return Math.abs(this.getPieceRow(attacker) - this.getPieceRow(target)) <= 1 && Math.abs(this.getPieceCol(attacker) - this.getPieceCol(target)) <= 1;
	}
	
	public void addMonsterPiece(Piece monsterPiece) {
		this.monsterPieces.add(monsterPiece);
	}
	
	public int getPieceRow(Piece piece) {
		return this.pieceRows.get(piece);
	}
	
	public int getPieceCol(Piece piece) {
		return this.pieceCols.get(piece);
	}

	public String toString() {		
		String rowline = "\n       \t+";
		for(int i = 0; i < this.getWidth(); i++) {
			if(i == 2 || i == 5) {
				rowline += "---+";
			} else {
				rowline += "--------------+";
			}
			
		}
		rowline += "\n";

		String rhet = "\n\n";
		for(int i = 0; i < this.getHeight(); i++) {
			rhet += rowline + "       \t|";
			
			LegendSpace.SpaceType type = null;
			
			// Each row is 3 lines high
			// Top left corner denotes type of the square
			
			for(int j = 0; j < this.getWidth(); j++) {
				type = this.getTypeOfSpace(i, j);
				switch(type) {
				case PLAIN:
					rhet += " P            |";
					break;
					
				case NEXUS:
					rhet += " N            |";
					break;
					
				case INACCESSIBLE:
					rhet += "||||";
					break;
					
				case BUSH:
					rhet += " B            |";
					break;
					
				case CAVE:
					rhet += " C            |";
					break;
					
				case KOULOU:
					rhet += " K            |";
					break;
				}		
			}
			
			rhet += "\n Row " + i + ":\t|";
			for(int j = 0; j < this.getWidth(); j++) {
				type = this.getTypeOfSpace(i, j);
				
				if(type == LegendSpace.SpaceType.INACCESSIBLE) {
					rhet += "||||";
				} else {
					if(this.getPieces(i, j).isEmpty()) {
						rhet += "              |";
					} else {
						if(this.getPieces(i, j).size() != 0 && !this.getPieces(i, j).get(0).getActor().isDefeated()) {
							rhet += "   " + this.getPieces(i, j).get(0);
						} else {
							rhet += "     ";
						}
						
						if(this.getPieces(i, j).size() >= 2 && !this.getPieces(i, j).get(1).getActor().isDefeated()) {
							rhet += "   " + this.getPieces(i, j).get(1) + "    |";
						} else {
							rhet += "         |";
						}
					}	
				}
							
			}
			
			rhet += "\n       \t|";			
			for(int j = 0; j < this.getWidth(); j++) {
				type = this.getTypeOfSpace(i, j);
				if(type == LegendSpace.SpaceType.INACCESSIBLE) {
					rhet += "||||";
				} else {
					rhet += "              |";		
				}
			}
		}
		
		rhet += rowline;
		
		rhet += "             Col0           Col1               Col3           Col4               Col6           Col7      \n";
		
		return rhet;		
	}
}
