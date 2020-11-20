/**
 * Attack items is another abstract class that deals specifically
 * with items that have their own damage value/attack action. Attack
 * items handle calculating the damage they do with their owner
 * and also actually carrying out the attack against an enemy.
 * @author Tanner Braun
 *
 */

public abstract class AttackItem extends Item{
	private int damage;
	
	public AttackItem(String name, int cost, int requiredLevel, int damage, ItemType type) {
		super(name, cost, requiredLevel, damage, type);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int num) {
		this.damage = num;
		super.setAffectAmount(num);
	}
	
	/**
	 * Calculates the amount of damage with a given weapon when used
	 * by specified hero
	 * @param hero - The hero that intends to use this weapon
	 * @return
	 */
	public abstract double calculateAttack(Hero hero);
	
	/**
	 * Does damage to a given actor
	 * @param target - The actor to be attacked
	 * @return whether the attack was successful or not
	 */
	public boolean attackTarget(Actor target) {
		
		if(!target.tryToDodge()) {
			if(this.getOwner() == null) {
				System.err.println("Error: Tried to attack with item " + this.getName() + ", but that item has no owner!");
				return false;
			} else {
				
				target.takeDamage(this.getOwner(), this.calculateAttack(this.getOwner()));
				return true;
			}			
		} else {
			System.out.println("\n" + this.getOwner().getName() + "'s attack against " + target.getName() + " missed!");
			return false;
		}
		
	}
}
