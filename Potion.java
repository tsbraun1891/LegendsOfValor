/**
 * Potions are one time use items that can increase given attributes by a
 * given amount. The potion class handles what happens when a given hero
 * drinks this potion.
 * @author Tanner Braun
 *
 */


public class Potion extends Item{
	
	/* Attributes that can be affected by a potion */
	public static enum Attribute {
		HEALTH,
		MANA,
		STRENGTH,
		DEXTERITY,
		AGILITY
	}
	
	private Attribute[] affectedAttributes;
	
	/**
	 * 
	 * @param name - Name of the potion
	 * @param cost - Cost of the potion
	 * @param requiredLevel - Level needed in order to be able to buy this potion
	 * @param affectAmount - How much affected attributes are changed by this potion
	 * @param affectedAttributes - list of attributes affected by this potion
	 */
	public Potion(String name, int cost, int requiredLevel, int affectAmount, Attribute[] affectedAttributes) {
		super(name, cost, requiredLevel, affectAmount, ItemType.POTION);
		
		this.affectedAttributes = affectedAttributes;
	}
	
	/**
	 * Use this potion on a specified hero
	 * @param hero - the hero for the potion to be used on
	 */
	public void usePotion(Hero hero) {
		
		for(Attribute a : affectedAttributes) {
			switch(a) {
			case HEALTH:
				hero.increaseHP(this.getAffectAmount());;
				break;
				
			case MANA:
				hero.increaseMana(this.getAffectAmount());
				break;
				
			case STRENGTH:
				hero.setStrength(hero.getStrength() + this.getAffectAmount());
				break;
				
			case DEXTERITY:
				hero.setDexterity(hero.getDexterity() + this.getAffectAmount());
				break;
				
			case AGILITY:
				hero.setAgility(hero.getAgility() + this.getAffectAmount());
				break;
				
			default:
				System.err.println("Given attribute for potion, " + this.getName() + " is not a valid attribute.");
			}					
		}
	}
	
	public Attribute[] getAttributes() {
		return affectedAttributes;
	}
	
	public String attributesToString() {
		String rhet = "";

		rhet += this.affectedAttributes[0].name();
		
		for(int i = 1; i < affectedAttributes.length; i++) {
			rhet += ", " + this.affectedAttributes[i].name();
		}
		
		return rhet;
	}

}
