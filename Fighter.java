/**
 * The Fighter interface must be implemented by anyone who plans
 * on participating in battles.
 */

public interface Fighter {

	public double takeDamage(Actor attacker, double damageAmount);
	
	public boolean tryToDodge();
	
	public boolean attack(Actor target);
}
