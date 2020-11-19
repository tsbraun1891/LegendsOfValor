
public interface Fighter {

	public double takeDamage(double damageAmount);
	
	public boolean tryToDodge();
	
	public boolean attack(Actor target);
}
