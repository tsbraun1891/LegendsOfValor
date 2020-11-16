/**
 * The hero class is an abstract class that represents an individual hero.  
 * This class extends actors to encompass all hero-specific stats such as
 * gold and mana, as well as hero-specific abilities such as healing and
 * levelling up.
 * @author Tanner Braun
 */


public abstract class Hero extends Actor{
	
	/* The different hero classes */
	public static enum HeroClass {
		PALADIN,
		SORCERER,
		WARRIOR
	}
	
	
	private double mana, strength, dexterity, agility, coins, XP;
	private Inventory inventory;	
	private HeroClass heroClass;
	
	
	public Hero(String name, double startingMana, double startStrength, double startAgility, double startDexterity, double startingMoney, double startingXP, HeroClass heroClass) {
		// We multiply the dodge chance by 100 here to keep with the convention that an actor has dodgeChance * .01 to dodge an attack
		super(name, 1000, 1, 0, startAgility * 0.002 * 25);
		mana = startingMana;
		strength = startStrength;
		dexterity = startDexterity;
		agility = startAgility;
		coins = startingMoney;
		XP = startingXP;
		this.heroClass = heroClass;
		
		inventory = new Inventory(this);
	}
	
	/**
	 * Adds given HP to the hero, making sure it does not exceed the maximum amount
	 * @param amount
	 */
	public void increaseHP(double amount) {
		double endResult = this.getHP() + amount;
		
		if(endResult > this.maxHP())
			this.setHP(this.maxHP());
		else 
			this.setHP(this.getHP() + amount);
	}
	
	// I decided to set Max HP and mana as Hero Level * 1000
	public double maxHP() {
		return this.getLevel() * 1000;
	}
	
	public double maxMana() {
		return this.getLevel() * 1000;
	}
	
	/**
	 * Adds given xp to this hero and levels them up if they break the level up threshold.
	 * @param amount
	 */
	public void increaseXP(double amount) {
		XP += amount;
		
		if(XP >= this.getLevel()*10) {
			XP -= this.getLevel()*10;
			this.levelUp();			
		}
	}
	
	public void spendMana(double amount) {
		if(amount > this.getMana()) {
			this.setMana(0);
		} else {
			this.setMana(this.getMana() - amount);
		}
	}
	
	/**
	 * Attack a given actor with all of the weapons that the hero is holding.
	 * @param target
	 */
	public void attack(Monster target) {
		System.out.println("\n\n" + this.getName() + " attacks " + target.getName() + "... ");
		for(Weapon weapon : this.inventory.getEquippedWeapons()) {
			weapon.attackTarget(target);
		}
		
		if(inventory.getEquippedWeapons().isEmpty()) {
			this.unarmedAttack(target);
		}
	}
	
	private void unarmedAttack(Actor target) {
		if(!target.tryToDodge()) {
				
			target.takeDamage(this.getStrength());
						
		} else {
			System.out.println("\n" + this.getName() + "'s attack against " + target.getName() + " missed!");
		}
	}
	
	/**
	 * This function is to be called at the end of a round of battle.
	 * Heroes regain 10% of their max HP and mana at the end of each round.
	 */
	public void recuperate() {
		this.increaseHP(this.maxHP() * .1);
		this.increaseMana(this.maxMana() * .1);
	}
	
	public void increaseMana(double amount) {
		this.setMana(this.getMana() + amount);
	}

	public void levelUp() {
		this.setMana(this.getMana() * 1.1);
		
		this.setLevel(this.getLevel() + 1);
		
		this.setHP(this.maxHP());
	}

	// Getters and Setters
	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public double getDexterity() {
		return dexterity;
	}

	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}

	public double getAgility() {
		return agility;
	}

	public void setAgility(double agility) {
		this.agility = agility;
	}

	public double getMana() {
		return mana;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public double getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void removeCoins(int num) {
		this.coins -= num;
	}
	
	public void addCoins(int num) {
		this.coins += num;
	}

	public double getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public HeroClass getHeroClass() {
		return heroClass;
	}
	
	public void endOfRoundRegain() {
		
	}
	
	public boolean equals(Object o) {
		if(o instanceof Hero) {
			Hero other = (Hero) o;
			
			if(other.getClass() == this.getClass() && other.getName().equals(this.getName()))
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String rhet = "";
		
		rhet += this.getName() + ":";
		rhet += "  Class - " + this.getClass().getCanonicalName();
		rhet += "  |  Lvl - " + this.getLevel();
		rhet += "  |  HP - " + (int) this.getHP();
		rhet += "  |  Mana - " + (int) this.getMana();
		rhet += "  |  Strength - " + (int) this.getStrength();
		rhet += "  |  Agility - " + (int) this.getAgility();
		rhet += "  |  Dexterity - " + (int) this.getDexterity();
		rhet += "  |  XP - " + (int) this.getXP();
		rhet += "  |  Gold - " + (int) this.getCoins();
		
		return rhet;
	}
}
