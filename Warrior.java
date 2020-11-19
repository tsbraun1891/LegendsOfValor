/**
 * The Warrior class represents a hero that has the warrior class. Warriors 
 * specialize in the strength and agility stats. The main thing this class 
 * does is implements the levelUp function as different classes of heroes 
 * level up differently.
 * @author Tanner Braun
 *
 */

public class Warrior extends Hero{

	public Warrior(String name, double startingMana, double startStrength, double startAgility,
			double startDexterity, double startingMoney, double startingXP) {
		super(name, startingMana, startStrength, startAgility, startDexterity, startingMoney, startingXP, Hero.HeroClass.WARRIOR);
	}

	/**
	 * Levels up the character to the next level
	 */
	public void levelUp() {
		super.levelUp();
		
		// Warriors gain 5% in dexterity and 10% in agility and strength 
		this.setDexterity(this.getDexterity() * 1.05);
		
		this.setAgility(this.getAgility() * 1.1);
		
		this.setStrength(this.getStrength() * 1.1);
	}

}
