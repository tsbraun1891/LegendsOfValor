/**
 * MarketIO pulls the user out of the overarching LegendsIO interface
 * so that they can make more market-specific commands. MarketIO also
 * has to handle a lot of invalid input loops for when trying to
 * purchase and item and deciding what hero is purchasing the item.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;
import java.util.Scanner;


public class MarketIO extends IO {
	private Market market;
	private boolean stayOpen;
	private Hero customer;

	
	public MarketIO(Market market, Hero hero) {
		this.market = market;
		this.customer = hero;
		stayOpen = true;
	}
	
	public void openMarket(Scanner scanner) {
		stayOpen = true;
		
		while(stayOpen) {
			System.out.println(market.toString());
			
			System.out.print("Would you like to buy (B), sell (S), or leave (L)? ");
			String input = scanner.next();
			
			switch(input.charAt(0)) {
			case 'B':
			case 'b':
				this.buyItem(scanner);
				break;
				
			case 'S':
			case 's':
				this.sellItem(scanner);
				break;
				
			case 'L':
			case 'l':
				this.closeMarket();
				break;
				
			case 'I':
			case 'i':
				this.displayPartyInformation();
				break;
				
			case 'Q':
			case 'q':
				continuePlaying = false;
				this.closeMarket();
				break;
			}
		}
		
	}
	
	public void closeMarket() {
		stayOpen = false;
	}
	
	public void displayPartyInformation() {
		ArrayList<Hero> party = market.getGame().getParty();
		
		String rhet = "";
		
		rhet += "\n\n--- Your Party ---\n";
		
		for(int i = 0; i < party.size(); i++) {
			rhet += i + " - ";
			rhet += party.get(i) + "\n";
		}
		
		System.out.println(rhet);
	}
	
	private void buyItem(Scanner scanner) {

		int itemIndex = this.safeGetInt("\n\nWhich item would you like to purchase (enter item index)? ", scanner);
		
		if(itemIndex < 0 || itemIndex >= market.getItems().size()) {
			System.out.print("\n\nYou have entered an invalid item. ");
			this.retry();
		} else {
			Item item = market.getItems().get(itemIndex);
			
			if(item.buyItem(customer)) {
				market.getItems().remove(item);
			}
			
		}
				
	}
	
	private void sellItem(Scanner scanner) {

		Inventory heroInv = this.customer.getInventory();
		
		System.out.println(heroInv.toString());
		int itemIndex = this.safeGetInt("\n\nWhich item would you like to sell (enter item index)? ", scanner);
		
		if(itemIndex < 0 || itemIndex >= heroInv.getItems().size()) {
			System.out.print("\n\nYou have entered an invalid item. ");
			this.retry();
		} else {
			Item item = heroInv.getItems().get(itemIndex);
			
			heroInv.removeItem(itemIndex);
			item.setOwner(null);
			
			this.customer.addCoins(item.getCost()/2);
			market.getItems().add(item);
			
		}		
	}
}
