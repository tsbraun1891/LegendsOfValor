/**
 * Board represents a physical board by using a 2D array (of which you could opt to use only one dimension) composed
 * of Space objects.  Board allows outside Objects to access specific spaces on the board given their coordinates.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;

public class Board {
	protected Space[][] spaces;
	
	protected Board() {
		
	}

	// Creates a board of the given width and height
	public Board(int width, int height) {
		spaces = new Space[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				spaces[i][j] = new Space();
			}
		}
	}

	// Creates a board of the given width and height, where each space can only hold
	// spaceSize pieces
	public Board(int width, int height, int spaceSize) {
		spaces = new Space[height][width];
			
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				spaces[i][j] = new Space(spaceSize);
			}
		}
	}

	/**
	 * Places a piece on space in row x, column y
	 * 
	 * @param piece - the piece to be placed
	 * @param x     - the row to place the piece in
	 * @param y     - the column to place the piece in
	 * @return returns 1 on successful placement, and 0 on illegal move
	 */
	public int placePiece(Piece piece, int x, int y) {
		if(x >= getHeight() || y >= getWidth()) {
			return 0;
		} else {
			return spaces[x][y].addPiece(piece);
		}		
	}

	/**
	 * Removes all instances of the given piece from space in row x, column y
	 * @param removePiece - the piece to be removed
	 * @param x           - the row to place the piece in
	 * @param y           - the column to place the piece in
	 * @return
	 */
	public void removePiece(Piece removePiece, int row, int col) {
		if(!(row >= getHeight() || col >= getWidth())) {
			spaces[row][col].removePiece(removePiece);
		}	
	}
	
	/**
	 * Returns a list of the pieces found at space in row x, column y
	 * @param x - the row of the space to find pieces from
	 * @param y - the column of the space to find pieces from
	 * @return the pieces in the specified space
	 */
	public ArrayList<Piece> getPieces(int x, int y) {
		if(!(x >= getHeight() || y >= getWidth())) {
			return spaces[x][y].getCurrentPieces();
		} else {
			return null;
		}
	}
	
	public int getHeight() {
		return spaces.length;
	}
	
	public int getWidth() {
		return spaces[0].length;
	}
	
	public String toString() {		
		String rowline = "\n+";
		for(int i = 0; i < this.getWidth(); i++) {
			rowline += "---+";
		}
		rowline += "\n";

		String rhet = "\n\n";
		for(int i = 0; i < this.getHeight(); i++) {
			rhet += rowline + "|";
			for(int j = 0; j < this.getWidth(); j++) {
				if(this.getPieces(i, j).isEmpty()) {
					rhet += "   |";
				} else {
					rhet += " " + this.getPieces(i, j).get(0) + " |";
				}				
			}
		}
		
		rhet += rowline;
		
		rhet += "\n\n";
		
		return rhet;		
	}
}
