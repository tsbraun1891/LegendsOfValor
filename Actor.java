/**
 * Abstract class the represents the generic 'Actor' in a video game
 * which can either be a player or an enemy.  Handles things they all 
 * share like taking damage, defense, dodge chance, etc...
 * @author Tanner Braun
 *
 */

public abstract class Actor implements Fighter{
	private String name;
	private double HP, defense, dodgeChance;
	private int level, id;
	private boolean isDefeated;
	private static int numActors = 0;
	
	/**
	 * Creates a new actor with the given parameters
	 * @param name
	 * @param startingHP
	 * @param startingLevel
	 * @param defense
	 * @param dodgeChance
	 */
	public Actor(String name, double startingHP, int startingLevel, double defense, double dodgeChance) {
		this.name = name;
		this.HP = startingHP;
		this.level = startingLevel;
		this.defense = defense;
		this.dodgeChance = dodgeChance * .01;
		this.isDefeated = false;
		this.id = numActors;
		numActors++;
	}
	
	abstract public boolean attack(Actor target);
	
	/**
	 * Do damage to this actor. This will reduce incoming damage by defense, and
	 * handle if the actor faints or not
	 * @param damageAmount - amount of damage to do
	 * @return actual damage done to the actor
	 */
	public double takeDamage(double damageAmount) {
		// Reduce the amount of damage by this actor's defense
		// Defense was kind of OP so I divided it by 2
		double actualDamage = damageAmount - this.getDefense();
		
		if(actualDamage < 0)
			actualDamage = 0;
			
		this.setHP(this.getHP() - actualDamage);
		
		System.out.println(this.getName() + " took " + actualDamage + " damage!");
		
		// Set isDefeated to true if HP is <= 0
		if(this.getHP() <= 0 ) {
			isDefeated = true;
			System.out.println("\n" + this.getName() + " was defeated!");
		}
		
		
		
		return actualDamage;
	}
	
	
	/**
	 * The actor attempts to dodge an incoming attack
	 * @return returns true if the attack was successfully dodged
	 */
	public boolean tryToDodge() {
		return Math.random() < this.dodgeChance;
	}
	
	public void incrementLevel() {
		level++;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		if(hP < 0) {
			HP = 0;
		} else {
			HP = hP;
		}		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public double getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(double dodgeChance) {
		this.dodgeChance = dodgeChance;
	}

	public boolean isDefeated() {
		return isDefeated;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Actor) {
			Actor other = (Actor) o;
			if(other.getId() == this.getId()) {
				return true;
			}
		}
		
		return false;
	}
	
}
