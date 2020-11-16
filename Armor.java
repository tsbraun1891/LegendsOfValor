/**
 * The armor class extends Item as it is a type of item
 * that contributes to the defense of its wearer/owner.
 * @author Tanner Braun
 *
 */

public class Armor extends Item{
	private int damageReduction;

	public Armor(String name, int cost, int requiredLevel, int damageReduction) {
		super(name, cost, requiredLevel, damageReduction, ItemType.ARMOR);

		this.damageReduction = damageReduction;
	}

	public int getDamageReduction() {
		return damageReduction;
	}
	
	public void setDamageReduction(int num) {
		this.damageReduction = num;
		super.setAffectAmount(num);
	}
}
