/**
 * This class is used to handle all of the possible actions of the player. It contains
 * all of the necessary background logic and computations that need to be done
 * for each action.
 * @author Tanner Braun
 *
 */


import java.util.ArrayList;
import java.util.Scanner;

public class LegendsActions {
	public static enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	private LegendsGame game;
	private Player player;
	private LegendBoard board;
	
	public LegendsActions(LegendsGame game, Player player, LegendBoard board) {
		this.game = game;
		this.player = player;
		this.board = board;
	}
	
	/**
	 * Attempt to move the party in a given direction
	 * @param scanner - input scanner used in case there is a battle
	 * @param direction - direction to move the party in
	 * @return whether or not this was a valid move
	 */
	public boolean move(Scanner scanner, Direction direction, Piece piece) {
		boolean rhet;
		int moveRow, moveCol;
		
		switch(direction) {
		case UP:
			moveRow = board.getPieceRow(piece)-1;
			moveCol = board.getPieceCol(piece);
			break;
			
		case DOWN:
			moveRow = board.getPieceRow(piece)+1;
			moveCol = board.getPieceCol(piece);
			break;
			
		case LEFT:
			moveRow = board.getPieceRow(piece);
			moveCol = board.getPieceCol(piece)-1;
			break;
			
		case RIGHT:
			moveRow = board.getPieceRow(piece);
			moveCol = board.getPieceCol(piece)+1;
			break;
			
		default:
			System.out.println("Error! Invalid direction given.");
			moveRow = 0;
			moveCol = 0;
			return false;
		}
		
		rhet = board.movePieceTo(piece, moveRow, moveCol);		
		
		return rhet;
	}
	
	
	public void getHelp() {
		System.out.println("\nActions you can take:");
		System.out.println("W: Move the current hero up one space");
		System.out.println("S: Move the current hero down one space");
		System.out.println("A: Move the current hero left one space");
		System.out.println("D: Move the current hero right one space");
		System.out.println("B: Move back to the beginning of this lane");
		System.out.println("T: Teleport to another lane");
		System.out.println("F: Attack an enemy");
		System.out.println("C: Cast a spell at an enemy");
		System.out.println("P: Open the market in the nexus");
		System.out.println("E: Manage your equipment");
		System.out.println("I: Display Party Information");
		System.out.println("U: Display Battle Information");
		System.out.println("M: Display the world map");
		System.out.println("Q: Quit the game");
	}
	
	/**
	 * Open the market interface for the user on a market tile
	 * @param scanner - The scanner object that you are using to get input from the market
	 * @return True if the player was on a space with a market, false otherwise
	 */
	public boolean openMarket(Scanner scanner, Piece piece) {
		if(board.getTypeOfSpace(board.getPieceRow(piece), board.getPieceCol(piece)) == LegendSpace.SpaceType.NEXUS) {
			Market market = new Market(game);
			
			market.openMarket(scanner, (Hero) piece.getActor());
			return true;
		} else {
			return false;
		}
		
	}
	
	public String displayPartyInformation() {
		ArrayList<Hero> party = game.getParty();
		
		String rhet = "";
		
		rhet += "\n\n--- Your Party ---\n";
		
		for(int i = 0; i < party.size(); i++) {
			rhet += i + " - ";
			rhet += party.get(i) + "\n";
		}
		
		return rhet;
	}
	
	public boolean manageEquipment(Scanner scanner, Piece piece) {
		InventoryIO io = new InventoryIO( ((Hero)piece.getActor()).getInventory() );
		
		io.openInventory(scanner);
		
		return true;
	}
	
	public boolean attack(Piece attacker, int monsterIndex) {

		if(monsterIndex >= this.game.getMonsterPieces().size() || monsterIndex < 0) {
			return false;
		} 		
		
		Piece target = this.game.getMonsterPieces().get(monsterIndex);
				
		if(board.inAttackRange(attacker, target)) {
			game.getBattle().playerAttack((Monster) target.getActor());
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public boolean castSpell(Piece attacker, Piece target, Spell spell) {
		if(board.inAttackRange(attacker, target)) {
			this.game.getBattle().playerSpell((Monster) target.getActor(), spell);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean back(Piece piece) {
		return board.movePieceTo(piece, board.getHeight()-1, board.getPieceCol(piece));
	}
	
	public boolean teleport(Piece piece, int row, int column) {
		/* column should never be adjacent because it is then either INACESSIBLE or in the same lane */
		if(Math.abs(board.getPieceCol(piece) - column) <= 1 || column == 2 || column == 5) {
			return false;
		} else {


			// Make sure there is no monster in front of the requested row
			for(Piece monsterPiece : this.game.getMonsterPieces()) {
				// If monster is in the same lane
				if(Math.abs(board.getPieceCol(monsterPiece) - column) <= 1) {
					// Now check if its further up than the requested row
					if(board.getPieceRow(monsterPiece) >= row) {
						return false;
					}
				}
			}

			// Now check if it is further than the furthest hero

			// By starting with this as the original value, teleports to the hero nexus row will pass this check
			int furthestHeroCol = board.getHeight()-1;
			for(Piece heroPiece : this.game.player.getPlayerPieces()) {
				if(Math.abs(board.getPieceCol(heroPiece) - column) <= 1) {
					if(board.getPieceRow(heroPiece) < furthestHeroCol) {
						furthestHeroCol = board.getPieceRow(heroPiece);
					}
				}
			}

			if(furthestHeroCol > row) 
				return false;


			// If we got here, we know that we are both in front of all the monsters in lane and behind the other heroes
			return board.movePieceTo(piece, row, column);
		}
	}
	
	public void displayMap() {
		System.out.println(board.toString());
	}
	
}
