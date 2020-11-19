/**
 * This is a list of all Items in the game. These are actual instances
 * of the different item types. ItemList also handles fetching a random
 * item from these instances for you.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;
import java.util.Random;

public class ItemList {
	private ArrayList<Armor> armorList;
	private ArrayList<Weapon> weaponList;
	private ArrayList<Potion> potionList;
	private ArrayList<Spell> spellList;
	private Random r;
	
	
	// Instantiates all of the known items
	public ItemList() {
		r = new Random();
		
		armorList = new ArrayList<Armor>();
		armorList.add(new Armor("Platinum Shield", 150, 1, 200));
		armorList.add(new Armor("Breastplate", 350, 3, 600));
		armorList.add(new Armor("Full Body Armor", 1000, 8, 1100));
		armorList.add(new Armor("Wizard Shield", 1200, 10, 1500));
		armorList.add(new Armor("Speed Boots", 550, 4, 600));
		armorList.add(new Armor("Guardian Angel", 1000, 10, 1000));
		
		
		weaponList = new ArrayList<Weapon>();
		weaponList.add(new Weapon("Sword", 500, 1, 800, 1));
		weaponList.add(new Weapon("Bow", 300, 2, 500, 2));
		weaponList.add(new Weapon("Scythe", 1000, 6, 1100, 2));
		weaponList.add(new Weapon("Axe", 550, 5, 850, 1));
		weaponList.add(new Weapon("TSwords", 1400, 8, 1600, 2));
		weaponList.add(new Weapon("Dagger", 200, 1, 250, 1));
		
		
		potionList = new ArrayList<Potion>();
		potionList.add(new Potion("Healing Potion", 250, 1, 100, new Potion.Attribute[] {Potion.Attribute.HEALTH } ));
		potionList.add(new Potion("Strength Potion", 200, 1, 75, new Potion.Attribute[] {Potion.Attribute.STRENGTH} ));
		potionList.add(new Potion("Magic Potion", 350, 2, 100, new Potion.Attribute[] { Potion.Attribute.MANA } ));
		potionList.add(new Potion("Luck Elixir", 500, 4, 65, new Potion.Attribute[] { Potion.Attribute.AGILITY } ));
		potionList.add(new Potion("Mermaid Tears", 850, 5, 100, new Potion.Attribute[] { Potion.Attribute.HEALTH, Potion.Attribute.MANA, Potion.Attribute.STRENGTH, Potion.Attribute.AGILITY } ));
		potionList.add(new Potion("Ambrosia", 1000, 8, 150, new Potion.Attribute[] { Potion.Attribute.HEALTH, Potion.Attribute.MANA, Potion.Attribute.STRENGTH, Potion.Attribute.DEXTERITY } ));
		
		spellList = new ArrayList<Spell>();
		spellList.add(new FireSpell("Flame Tornado", 700, 4, 850, 300));
		spellList.add(new FireSpell("Breath of Fire", 350, 1, 450, 100));
		spellList.add(new FireSpell("Heat Wave", 450, 2, 600, 150));
		spellList.add(new FireSpell("Lava Comet", 800, 7, 1000, 550));
		spellList.add(new FireSpell("Hell Storm", 600, 3, 950, 600));
		
		spellList.add(new IceSpell("Snow Cannon", 500, 2, 650, 250));
		spellList.add(new IceSpell("Ice Blade", 250, 1, 450, 100));
		spellList.add(new IceSpell("Frost Blizzard", 750, 5, 850, 350));
		spellList.add(new IceSpell("Arctic Storm", 700, 6, 800, 300));
		
		spellList.add(new LightningSpell("Lightning Dagger", 400, 1, 500, 150));
		spellList.add(new LightningSpell("Thunder Blast", 750, 4, 950, 400));
		spellList.add(new LightningSpell("Electric Arrows", 550, 5, 650, 200));
		spellList.add(new LightningSpell("Spark Needles", 500, 2, 600, 200));
	}
	
	public Item getRandomItem() {
		int listNum = r.nextInt(4);
		
		switch(listNum) {
		case 0:
			return getRandomWeapon();
			
		case 1:
			return getRandomArmor();
			
		case 2:
			return getRandomSpell();
			
		case 3:
			return getRandomPotion();
			
		default:
			// Should never get here, make an error happen
			return null;
		}
			
	}
	
	public Weapon getRandomWeapon() {
		Weapon w = weaponList.get(r.nextInt(weaponList.size()));
		return new Weapon(w.getName(), w.getCost(), w.getRequiredLevel(), w.getDamage(), w.getRequiredHands());
	}
	
	public Armor getRandomArmor() {
		Armor a = armorList.get(r.nextInt(armorList.size()));
		return new Armor(a.getName(), a.getCost(), a.getRequiredLevel(), a.getDamageReduction());
	}
	
	public Spell getRandomSpell() {
		Spell s = spellList.get(r.nextInt(spellList.size()));
		switch(s.getSpellType()) {
		case FIRE:
			return new FireSpell(s.getName(), s.getCost(), s.getRequiredLevel(), s.getDamage(), s.getManaCost());
			
		case ICE:
			return new IceSpell(s.getName(), s.getCost(), s.getRequiredLevel(), s.getDamage(), s.getManaCost());
			
		case LIGHTNING:
			return new LightningSpell(s.getName(), s.getCost(), s.getRequiredLevel(), s.getDamage(), s.getManaCost());
			
			default:
				return null;
		}
	}
	
	public Potion getRandomPotion() {
		Potion p = potionList.get(r.nextInt(potionList.size()));
		return new Potion(p.getName(), p.getCost(), p.getRequiredLevel(), p.getAffectAmount(), p.getAttributes());
	}
	 
}
