/**
 * InventoryIO handles interacting with the user while they are in their inventory.
 * It takes in user inputs and tries to perform the input actions on the specified
 * inventory.
 */

import java.util.Scanner;

public class InventoryIO extends IO{

	private Inventory inv;
	private boolean stayOpen;
	
	public InventoryIO(Inventory inv) {
		this.inv = inv;
	}
	
	public void openInventory(Scanner scanner) {
		stayOpen = true;
		
		while(stayOpen) {
			
			System.out.println(inv.equippedToString());
			System.out.println(inv.toString());
			
			System.out.print("Would you like to equip an item (E), unequip an item (U), use a potion (P), or leave inventory (L)? ");
			String input = scanner.next();
			
			switch(input.charAt(0)) {
			case 'L':
			case 'l':
				this.closeInventory();
				break;
				
			case 'P':
			case 'p':
				this.usePotion(scanner);
				break;
				
			case 'E':
			case 'e':
				this.equipItem(scanner);
				break;
				
			case 'U':
			case 'u':
				this.unequipItem(scanner);
				break;
				
			case 'Q':
			case 'q':
				continuePlaying = false;
				this.closeInventory();
				break;
			}
		}
	}
	
	private void closeInventory() {
		stayOpen = false;
	}
	
	private void equipItem(Scanner scanner) {
		int itemIndex = this.safeGetInt("\n\nWhich item would you like to equip (enter item index)? ", scanner);
	
		if(itemIndex < 0 || itemIndex >= inv.getItems().size()) {
			System.out.print("\n\nYou have entered an invalid item. ");
			this.retry();
		} else {
			if(!inv.equipItem(itemIndex)) {
				System.out.println("\n\nYou cannot equip that item. Either it is not an armor or weapon, or your hands are full.");
			}
		}
	}
	
	private void unequipItem(Scanner scanner) {
		int itemIndex = this.safeGetInt("\n\nWhich item would you like to unequip (enter item index)? ", scanner);
		
		if(itemIndex == 0) {
			inv.unequipArmor();
		} else if(itemIndex < 0 || itemIndex > inv.getEquippedWeapons().size()) {
			System.out.print("\n\nYou have entered an invalid item. ");
			this.retry();
		} else {
			inv.unequipWeapon(itemIndex-1);
		}
	}
	
	private void usePotion(Scanner scanner) {
		int itemIndex = this.safeGetInt("\n\nWhich potion would you like to use (enter item index)? ", scanner);
		
		if(itemIndex < 0 || itemIndex >= inv.getItems().size()) {
			System.out.print("\n\nYou have entered an invalid item. ");
			this.retry();
		} else {
			if(!inv.usePotion(itemIndex)) {
				System.out.println("\n\nThe item you entered is not a potion.");
			}
		}
	}
}
