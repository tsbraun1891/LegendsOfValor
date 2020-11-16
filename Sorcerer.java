/**
 * The Sorcerer class represents a hero that has the sorcerer class. Sorcerers 
 * specialize in the dexterity and agility stats. The main thing this class 
 * does is implements the levelUp function as different classes of heroes 
 * level up differently.
 * @author Tanner Braun
 *
 */

public class Sorcerer extends Hero{

	public Sorcerer(String name, double startingMana, int startStrength, int startAgility,
			int startDexterity, int startingMoney, int startingXP) {
		super(name, startingMana, startStrength, startAgility, startDexterity, startingMoney, startingXP, Hero.HeroClass.SORCERER);
	}

	/**
	 * Levels up the character to the next level
	 */
	public void levelUp() {
		super.levelUp();
		
		// Sorcerers gain 5% in strength and 10% in dexterity and agility
		this.setDexterity(this.getDexterity() * 1.1);
		
		this.setAgility(this.getAgility() * 1.1);
		
		this.setStrength(this.getStrength() * 1.05);
	}

}
