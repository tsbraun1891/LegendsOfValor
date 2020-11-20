/**
 * The monster class is a specific implementation of the Actor class
 * that can represent many different types of monsters in an rpg.
 * Monsters can naturally attack other characters as well as have
 * their own "classes" in MonsterType.
 * @author Tanner Braun
 *
 */


public class Monster extends Actor{
	
	/* The different types of monsters */
	public static enum MonsterType {
		DRAGON,
		SKELETON,
		SPIRIT
	}
	
	private double damage;
	private MonsterType monsterType;

	// Decided to make starting HP for monsters equal to 100 * their level just like with heroes
	public Monster(String name, int startingLevel, double damage,  double defense, 
			double dodgeChance, MonsterType monsterType) {
		super(name, startingLevel * 100, startingLevel, defense, dodgeChance);
		
		this.damage = damage;
		this.monsterType = monsterType;
	}

	/**
	 * Does damage to a given actor
	 * @param target - The actor to be attacked
	 * @return whether the attack was successful or not
	 */
	public boolean attack(Actor target) {
		System.out.println("\n\n" + this.getName() + " attacks " + target.getName() + "... ");
		
		if(!target.tryToDodge()) {
				
			target.takeDamage(this, this.damage);			
			
			return true;
		} else {
			System.out.println("\n" + this.getName() + "'s attack against " + target.getName() + " missed!");
			return false;
		}
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public MonsterType getType() {
		return this.monsterType;
	}
	
	public String toString() {
		String rhet = "";
		rhet += this.getName() + ", ";
		
		// Dispaly the monster's type
		switch(this.getType()) {
		case DRAGON:
			rhet += "The Dragon:";
			break;
			
		case SKELETON:
			rhet += "The Skeleton:";
			break;
			
		case SPIRIT:
			rhet += "The Spirit:";
			break;
		}
		
		// Display the monster's stats
		rhet += "  Level - " + this.getLevel();
		rhet += "  |  HP - " + (int) this.getHP();
		rhet += "  |  Damage - " + (int) this.getDamage();
		rhet += "  |  Defence - " + (int) this.getDefense();
		rhet += "  |  Dodge - " + this.getDodgeChance();
		rhet += "\n";
		
		return rhet;
	}	
}
