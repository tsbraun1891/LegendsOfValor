/**
 * Fire spell represents specifically a spell of the fire type. The main thing
 * it does is reimplements attackActor to also include the stat debuffs that it
 * adds.
 * @author Tanner Braun
 *
 */

public class FireSpell extends Spell{

	public FireSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
		super(name, cost, requiredLevel, damage, manaCost, Spell.SpellType.FIRE);
	}
	
	/**
	 * Does damage to a given monster.  This implementation is spell-type specific, and so, overrides the AttackTime attackActor function
	 * @param monster - The actor to be attacked
	 * @return whether the attack was successful or not
	 */
	public boolean attackTarget(Monster target) {
		boolean rhet = super.attackTarget(target); 
		if(rhet) {
			target.setDefense(target.getDefense() * .9);
		}
		
		return rhet;
	}
	
}
