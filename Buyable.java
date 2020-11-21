/**
 * The Buyable interface implies that something can be bought by a 
 * hero character. We must be able to check if they're allowed to
 * buy it and what happens when they do buy it.
 */

public interface Buyable {

	public boolean canBuyItem(Hero hero);
	
	public boolean buyItem(Hero hero);
}
