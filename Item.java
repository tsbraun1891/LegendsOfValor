/**
 * An item is any piece of equipment that can be equipped or bought in
 * a market square. Items all have a name, cost, required level, and
 * an affect amount. This amount can be damage, attribute increase, or
 * damage reduction. Item is an abstract class to be implemented by
 * other specific types of items
 * @author Tanner Braun
 *
 */

public abstract class Item {
	
	/* Item Types */
	public static enum ItemType {
		WEAPON,
		SPELL,
		ARMOR,
		POTION
	}
	
	private String name;
	private int cost, requiredLevel, affectAmount;
	private Hero owner;
	private ItemType type;
	
	public Item(String name, int cost, int requiredLevel, int affectAmount, ItemType type) {
		this.name = name;
		this.cost = cost;
		this.requiredLevel = requiredLevel;
		this.affectAmount = affectAmount;
		this.type = type;
		this.owner = null;
	}
	
	/**
	 * Checks if the given hero is high enough level to buy an item
	 * @param hero
	 * @return
	 */
	public boolean canBuyItem(Hero hero) {
		return hero.getLevel() >= this.requiredLevel;
	}
	
	public boolean equals(Item other) {
		return this.type == other.getType() && this.name.equals(other.getName());
	}
	
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getRequiredLevel() {
		return this.requiredLevel;
	}
	
	public int getAffectAmount() {
		return affectAmount;
	}
	
	public void setAffectAmount(int affectAmount) {
		this.affectAmount = affectAmount;
	}
	
	public Hero getOwner() {
		return owner;
	}
	
	public void setOwner(Hero owner) {
		this.owner = owner;
	}	
	
	public ItemType getType() {
		return type;
	}
}
