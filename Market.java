/**
 * The Market class simulates a market in the game world.  Here the market's
 * items and interactions are stored and handled. When a market is created,
 * it is randomly filled with a bunch of items.
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Market {
	private ArrayList<Item> items;
	private LegendsGame game;
	private final int itemCap = 10;
	
	/**
	 * Create a market with a random amount of items and then open the IO
	 * @param game - The game that this market is being used in
	 */
	public Market(LegendsGame game) {
		this.game = game;
		
		Random r = new Random();
		
		r.nextInt(itemCap);
		
		items = new ArrayList<>();
		
		this.addNumItems(r.nextInt(itemCap-1) + 1);
		
	}
	
	/**
	 * Create a market with a specified amount of items and then open the IO
	 * @param game - The game that this market is being used in
	 * @param numItems - number of items to place in the market
	 */
	public Market(LegendsGame game, int numItems) {
		this.game = game;
		
		items = new ArrayList<>();
		
		this.addNumItems(numItems);
		
	}
	
	/**
	 * Create a market with the specified items and then open the IO
	 * @param game - The game that this market is being used in
	 * @param items - the items to place in the market
	 */
	public Market(LegendsGame game, ArrayList<Item> items) {
		this.game = game;
		
		this.items = items;
		
	}
	
	private void addNumItems(int numItems) {
		for(int i = 0; i < numItems; i++) {
			items.add(game.getItemList().getRandomItem());
		}
	}
	
	public void openMarket(Scanner scanner, Hero customer) {
		MarketIO io = new MarketIO(this, customer);
		
		io.openMarket(scanner);
	}
	
	public LegendsGame getGame() {
		return game;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	private String genericStatsToString(Item item) {
		String rhet = "";
		
		rhet += "  |  Name: " + item.getName();
		rhet += "  |  Cost: " + item.getCost();
		rhet += "  |  Req Lvl: " + item.getRequiredLevel();
		
		return rhet;
	}
	
	public String toString() {
		String rhet = "\n\n";
		
		rhet += "~~~~~~~~~~ Market ~~~~~~~~~~\n";
		
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			
			rhet += i + " - ";
			
			
			switch(item.getType()) {
			case WEAPON:
				Weapon weapon = (Weapon) item;
				rhet += "  Type: Weapon";
				rhet += this.genericStatsToString(item);
				rhet += "  |  Damage: " + weapon.getDamage();
				rhet += "  |  Req Hands: " + weapon.getRequiredHands();
				break;
				
			case ARMOR:
				rhet += "  Type: Armor";
				rhet += this.genericStatsToString(item);
				rhet += "  |  Defence: " + item.getAffectAmount();
				break;
				
			case SPELL:
				Spell spell = (Spell) item;
				switch(spell.getSpellType()) {
				case FIRE:
					rhet += "  Type: Fire";
					break;
					
				case ICE:
					rhet += "  Type: Ice";
					break;
					
				case LIGHTNING:
					rhet += "  Type: Lightning";
					break;
				}
				
				rhet += " Spell";
				rhet += this.genericStatsToString(item);
				rhet += "  |  Damage: " + item.getAffectAmount();
				break;
				
			case POTION:
				Potion potion = (Potion) item;
				rhet += "  Type: Potion";
				rhet += this.genericStatsToString(item);
				rhet += "  |  Affect Amount: " + item.getAffectAmount();
				rhet += "  |  Affected Attributes: " + potion.attributesToString();
				break;
			}
			
			rhet += "\n";
		}
		
		return rhet;
	}
}
