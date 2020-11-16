/**
 * The Paladin class represents a hero that has the paladin class. Paladins 
 * specialize in the dexterity and strength stats. The main thing this class 
 * does is implements the levelUp function as different classes of heroes 
 * level up differently.
 * @author Tanner Braun
 *
 */

public class Paladin extends Hero{

	public Paladin(String name, double startingMana, int startStrength, int startAgility,
			int startDexterity, int startingMoney, int startingXP) {
		super(name, startingMana, startStrength, startAgility, startDexterity, startingMoney, startingXP, Hero.HeroClass.PALADIN);
	}

	/**
	 * Levels up the character to the next level
	 */
	public void levelUp() {
		super.levelUp();
		
		// Paladins gain 5% in agility and 10% in dexterity and strength
		this.setDexterity(this.getDexterity() * 1.1);
		
		this.setAgility(this.getAgility() * 1.05);
		
		this.setStrength(this.getStrength() * 1.1);
	}

}
