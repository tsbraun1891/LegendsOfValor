
/**
 * Inventory manages a hero's items that they have stored in their bag
 * and also equipping and stowing their items. In order to access any
 * of their items, a player must first access their inventory object.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	private ArrayList<Weapon> equippedWeapons;
	private Armor equippedArmor;
	private Hero owner;
	
	public Inventory(Hero owner) {
		this.owner = owner;
		items = new ArrayList<Item>();
		equippedArmor = null;
		equippedWeapons = new ArrayList<Weapon>();
	}
	
	public Inventory(Hero owner, ArrayList<Item> startingItems) {
		this.owner = owner;
		items = startingItems;
		equippedWeapons = new ArrayList<Weapon>();
	}
	
	/**
	 * @return the list of items in the inventory
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * Add a given item to the inventory
	 * @param item
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * @return the items currently equipped by the hero
	 */
	public ArrayList<Weapon> getEquippedWeapons() {
		return equippedWeapons;
	}
	
	/**
	 * Remove the index of the given item from your inventory
	 * @param itemIndex
	 */
	public void removeItem(int itemIndex) {
		
		Item itemToRemove = items.get(itemIndex);
		
		// Unequip the item if it is equipped
		if(equippedArmor != null && itemToRemove.equals(equippedArmor)) {
			unequipArmor();
		} else {
			for(Weapon w : equippedWeapons) {
				if(itemToRemove.equals(w)) {
					equippedWeapons.remove(w);
				}
			}
		}
		
		items.remove(itemIndex);
	}
	
	public void unequipArmor() {
		equippedArmor = null;
		owner.setDefense(0);
	}
	
	public void unequipWeapon(int weaponIndex) {
		equippedWeapons.remove(weaponIndex);
	}
	
	public boolean usePotion(int potionIndex) {
		Item item = this.items.get(potionIndex);
		
		if(item.getType() != Item.ItemType.POTION) {
			return false;
		} else {
			Potion pot = (Potion) item;
			
			pot.usePotion(owner);
			pot.setOwner(null);
			this.removeItem(potionIndex);
			
			System.out.println("\n" + owner.toString());
			
			return true;
		}
	}
	
	/**
	 * Gets the armor that the hero is currently wearing
	 * @return null if there is no armor currently equipped
	 */
	public Armor getArmor() {
		return equippedArmor;
	}
	
	/**
	 * Equip the item in the given inventory slot
	 * @param indexOfItem - the index of the item you want to equip in the inventory
	 * @return whether or not the item was successfully equipped
	 */
	public boolean equipItem(int indexOfItem) {
		if(indexOfItem < items.size()) {
			Item item = items.get(indexOfItem);
			
			if(item.getType() == Item.ItemType.WEAPON) {
				return equipWeapon((Weapon) item);
			} else if(item.getType() == Item.ItemType.ARMOR) {
				equipArmor((Armor) item);
				return true;
			} else {
				// Items not of type Weapon or Armor are not equippable
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	/**
	 * Try to equip the given weapon
	 * @param weapon
	 * @return false if hands are too full to equip
	 */
	private boolean equipWeapon(Weapon weapon) {
		int handsFull = 0;
		
		for(Weapon w : equippedWeapons) {
			handsFull += w.getRequiredHands();
		}
		
		if(handsFull >= 2) {
			return false;
		} else {
			equippedWeapons.add(weapon);
			return true;
		}
	}
	
	/**
	 * Equips the given armor object
	 * @param armor
	 */
	private void equipArmor(Armor armor) {
		/* Changing the equipped armor pointer effectively unequips any previous armor */
		equippedArmor = armor;
		owner.setDefense(armor.getDamageReduction());
	}
	
	public Hero getOwner() {
		return owner;
	}
	
	/* A toString method specifically to be used in battle */
	public String tabbedEquippedToString() {
		String rhet = "";
		
		rhet += "\n\t--------Equipped Items--------";
		if(equippedArmor == null) {
			rhet += "\n\t0 - Armor:  None";
		} else {
			rhet += "\n\t0 - Armor:  " + equippedArmor.getName() + "   |   Damage Reduction: " + equippedArmor.getDamageReduction();
		}
		
		for(int i = 0; i < equippedWeapons.size(); i++) {
			Weapon w = equippedWeapons.get(i);
			rhet += "\n\t" + (i+1) + " - Weapon:  " + w.getName() + "   |   Base Damage: " + w.getDamage() + "   |   Equipped Max Damage: " + w.maxDamage(owner);
		}
		
		if(equippedWeapons.isEmpty()) {
			rhet += "\n\t0 - Weapon:  Fists  |  Damage: " + this.owner.getStrength();
		}
		
		return rhet;
	}
	
	public String equippedToString() {
		String rhet = "";
		
		rhet += "\n\n--------Equipped Items--------";
		if(equippedArmor == null) {
			rhet += "\n0 - Armor:  None";
		} else {
			rhet += "\n0 - Armor:  " + equippedArmor.getName() + "   |   Damage Reduction: " + equippedArmor.getDamageReduction();
		}
		
		for(int i = 0; i < equippedWeapons.size(); i++) {
			Weapon w = equippedWeapons.get(i);
			rhet += "\n" + (i+1) + " - Weapon:  " + w.getName() + "   |   Base Damage: " + w.getDamage() + "   |   Equipped Max Damage: " + w.maxDamage(owner);
		}
		
		return rhet;
	}
	
	public String toString() {
		String rhet = "";
		
		rhet += "\n----------------------- Inventory -----------------------\n";
		
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			
			rhet += i + " - Name: " + item.getName();
			rhet += "  |  Value: " + (int) (item.getCost()/2);
			
			switch(item.getType()) {
			case WEAPON:
				Weapon weapon = (Weapon) item;
				rhet += "  |  Type:  Weapon";
				rhet += "  |  Base Damage: " + weapon.getDamage();
				rhet += "  |  Equipped Max Damage: " + weapon.maxDamage(owner);
				rhet += "  |  Required Hands: " + weapon.getRequiredHands();
				break;
				
			case ARMOR:
				Armor armor = (Armor) item;
				rhet += "  |  Type:  Armor";
				rhet += "  |  Damage Reduction: " + armor.getDamageReduction();
				break;
				
			case SPELL:
				Spell spell = (Spell) item;
				rhet += "  |  Type:  Spell";
				rhet += "  |  Mana Cost: " + spell.getManaCost();
				rhet += "  |  Base Damage: " + spell.getDamage();
				rhet += "  |  Your Max Damage: " + spell.maxDamage(owner);
				break;
				
			case POTION:
				Potion pot = (Potion) item;
				rhet += "  |  Type:  Potion";
				rhet += "  |  Affect: " + pot.getAffectAmount();
				rhet += "  |  Attributes: ";
				rhet += pot.attributesToString();				
				break;
			}
			
			rhet += "\n";
		}
		
		rhet += "\n";
		
		return rhet;
	}
}
