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
		LegendSpace.SpaceType spaceType = board.getTypeOfSpace(moveRow, moveCol);
		
		displayMap();
		
		
		return rhet;
	}
	
	
	public void getHelp() {
		System.out.println("\nActions you can take:");
		System.out.println("W: Move the current hero up one space");
		System.out.println("S: Move the current hero down one space");
		System.out.println("A: Move the current hero left one space");
		System.out.println("D: Move the current hero right one space");
		System.out.println("P: Open the market on your tile");
		System.out.println("E: Manage your equipment");
		System.out.println("I: Display relevant information");
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
			
			market.openMarket(scanner);
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
	
	public boolean manageEquipment(Scanner scanner, int heroIndex) {
		if(heroIndex < 0 || heroIndex > game.getParty().size()) {
			return false;
		} else {
			InventoryIO io = new InventoryIO(game.getParty().get(heroIndex).getInventory());
			
			io.openInventory(scanner);
			
			return true;
		}		
	}
	
	public boolean attack(Piece attacker, Piece target) {
		
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
	
	public void back(Piece piece) {				
		board.removePiece(piece, board.getPieceRow(piece), board.getPieceCol(piece));
		
		board.placePiece(piece, board.getHeight()-1, board.getPieceCol(piece));
	}
	
	public boolean teleport(Piece piece, int column) {
		/* column should never be adjacent because it is then either INACESSIBLE or in the same lane */
		if(Math.abs(board.getPieceCol(piece) - column) <= 1 || column == 2 || column == 5) {
			return false;
		} else {
			// TODO
			return false;
		}
	}
	
	public void displayMap() {
		System.out.println(board.toString());
	}
	
}
