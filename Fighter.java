
public interface Fighter {

	public double takeDamage(Actor attacker, double damageAmount);
	
	public boolean tryToDodge();
	
	public boolean attack(Actor target);
}
