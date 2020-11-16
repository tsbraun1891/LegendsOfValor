/**
 * Spell is an abstract class that represents a magical attack in our game.
 * All spells require an amount of mana to be spent in order to cast, and 
 * also have an extra affect based on their spell type.
 * @author Tanner Braun
 *
 */

import java.util.Random;


public abstract class Spell extends AttackItem{
	public static enum SpellType {
		FIRE,
		ICE,
		LIGHTNING
	}
	
	private int manaCost;
	private SpellType type;

	public Spell(String name, int cost, int requiredLevel, int damage, int manaCost, SpellType spellType) {
		super(name, cost, requiredLevel, damage, ItemType.SPELL);

		this.manaCost = manaCost;
		
		this.type = spellType;
	}
	
	public double calculateAttack(Hero hero) {
		// I decided to use a more classic RPG damage calculation of dex + random number up to max damage of spell
		Random r = new Random();
		return hero.getDexterity() + r.nextInt(this.getDamage());
	}

	public double maxDamage(Hero hero) {
		return hero.getDexterity() + this.getDamage();
	}
	
	// Getters and Setters	
	public int getManaCost() {
		return manaCost;
	}
	
	public void setManaCost(int mc) {
		manaCost = mc;
	}
	
	public SpellType getSpellType() {
		return type;
	}
}
