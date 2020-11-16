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
	private final double battleProbability;
	
	public LegendsActions(LegendsGame game, Player player, LegendBoard board) {
		this.game = game;
		this.player = player;
		this.board = board;
		this.battleProbability = .75;
	}
	
	/**
	 * Attempt to move the party in a given direction
	 * @param scanner - input scanner used in case there is a battle
	 * @param direction - direction to move the party in
	 * @return whether or not this was a valid move
	 */
	public boolean move(Scanner scanner, Direction direction) {
		boolean rhet;
		int moveRow, moveCol;
		
		switch(direction) {
		case UP:
			moveRow = board.playerRow()-1;
			moveCol = board.playerCol();
			break;
			
		case DOWN:
			moveRow = board.playerRow()+1;
			moveCol = board.playerCol();
			break;
			
		case LEFT:
			moveRow = board.playerRow();
			moveCol = board.playerCol()-1;
			break;
			
		case RIGHT:
			moveRow = board.playerRow();
			moveCol = board.playerCol()+1;
			break;
			
		default:
			System.out.println("Error! Invalid direction given.");
			moveRow = 0;
			moveCol = 0;
			return false;
		}
		
		rhet = board.movePartyTo(moveRow, moveCol);
		LegendSpace.SpaceType spaceType = board.getTypeOfSpace(moveRow, moveCol);
		
		displayMap();
		
		// If the move was successful, a battle might start
		if(rhet && spaceType == LegendSpace.SpaceType.COMMON) {
			if(Math.random() < this.battleProbability) {
				Battle battle = new Battle(this.game.getParty(), this.game.getMonsterList().getRandomListOfMonstersBetweenLevels(this.game.getParty().size(), this.game.getPartyLevel()-1, this.game.getPartyLevel()+2));
				
				battle.startBattle(scanner);
			}
		}
		
		return rhet;
	}
	
	
	public void getHelp() {
		System.out.println("\nActions you can take:");
		System.out.println("W: Move the party up one space");
		System.out.println("S: Move the party down one space");
		System.out.println("A: Move the party left one space");
		System.out.println("D: Move the party right one space");
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
	public boolean openMarket(Scanner scanner) {
		if(board.getTypeOfSpace(board.playerRow(), board.playerCol()) == LegendSpace.SpaceType.MARKET) {
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
	
	public void displayMap() {
		System.out.println(board.toString());
	}
	
}
