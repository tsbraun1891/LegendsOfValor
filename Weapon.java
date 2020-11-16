/**
 * Weapons are physical objects that heroes can use to do damage to monsters.
 * Weapons represent one specific type of item that you can attack with.
 * @author Tanner Braun
 *
 */

import java.util.Random;

public class Weapon extends AttackItem{
	private int requiredHands;

	public Weapon(String name, int cost, int requiredLevel, int damage, int requiredHands) {
		super(name, cost, requiredLevel, damage, ItemType.WEAPON);

		
		this.requiredHands = requiredHands;
	}
	
	
	public double calculateAttack(Hero hero) {
		// I decided to go with the classic RPG style of just damage = strength + random number from 0 to maxDamage
		Random r = new Random();
		return (hero.getStrength() + r.nextInt(this.getDamage()));
	}
	
	public double maxDamage(Hero hero) {
		return hero.getStrength() + this.getDamage();
	}
	
	public int getRequiredHands() {
		return this.requiredHands;
	}
}
