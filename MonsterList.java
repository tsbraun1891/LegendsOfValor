/**
 * This is a list of all the monsters in the game. These are actual instances
 * of the different monster types. MonsterList also handles fetching a random
 * monster from these instances for you.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;
import java.util.Random;

public class MonsterList {

	private ArrayList<Monster> monsters;
	private Random r;
	
	public MonsterList() {
		r = new Random();
		
		monsters = new ArrayList<>();
		
		// Dragons
		monsters.add(new Monster("Disghidorrah", 3, 300, 400, 35, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Chrysophylax", 2, 200, 500, 20, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("BunsenBurner", 4, 400, 500, 45, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Natsunomeryu", 1, 100, 200, 10, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("TheScaleless", 7, 700, 600, 75, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Kas-Ethelinh", 5, 600, 500, 60, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Alexstraszan", 10, 1000, 9000, 55, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Phaarthurnax", 6, 600, 700, 60, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("D-Maleficent", 9, 900, 950, 85, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("TheWeatherbe", 8, 800, 900, 80, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Igneel", 6, 600, 400, 60, Monster.MonsterType.DRAGON));
		monsters.add(new Monster("Blue Eyes White", 9, 900, 600, 75, Monster.MonsterType.DRAGON));
		
		// Skeletons
		monsters.add(new Monster("Cyrrollalee", 7, 700, 800, 75, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Big Bad Wolf", 3, 350, 450, 30, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Wicked Witch", 1, 150, 250, 15, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Aasterinian", 4, 400, 500, 45, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Chronepsish", 6, 650, 750, 60, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Kiaransalee", 8, 850, 950, 85, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("St-Shargaas", 5, 550, 650, 55, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Merrshaullk", 10, 1000, 900, 55, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("St-Yeenoghu", 9, 950, 850, 90, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("DocOck", 6, 600, 600, 55, Monster.MonsterType.SKELETON));
		monsters.add(new Monster("Exodia", 10, 1000, 1000, 50, Monster.MonsterType.SKELETON));
		
		// Spirits
		monsters.add(new Monster("Andrealphus", 2, 600, 500, 50, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Andromalius", 3, 550, 450, 25, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Chiang", 4, 700, 600, 40, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Fallen Angel", 5, 800, 700, 50, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Ereshkigall", 6, 950, 450, 35, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Melchiresas", 7, 350, 150, 75, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Jormunngand", 8, 600, 900, 20, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Rakkshasass", 9, 550, 600, 35, Monster.MonsterType.SPIRIT));
		monsters.add(new Monster("Taltechutli", 10, 300, 200, 50, Monster.MonsterType.SPIRIT));
		
	}
	
	public Monster getRandomMonster() {
		Monster monster = monsters.get(r.nextInt(monsters.size()));
		return new Monster(monster.getName(), monster.getLevel(), monster.getDamage(), monster.getDefense(), monster.getDodgeChance(), monster.getType());
	}
	
	public Monster getRandomMonsterUnderLevel(int maxLevel) {
		Monster monster = monsters.get(r.nextInt(monsters.size()));
		
		while(monster.getLevel() > maxLevel) {
			monster = monsters.get(r.nextInt(monsters.size()));
		}
		
		return new Monster(monster.getName(), monster.getLevel(), monster.getDamage(), monster.getDefense(), monster.getDodgeChance(), monster.getType());
	}
	
	public Monster getRandomMonsterBetweenLevels(int levelMin, int maxLevel) {
		Monster monster = monsters.get(r.nextInt(monsters.size()));
		
		while(monster.getLevel() < levelMin || monster.getLevel() > maxLevel) {
			monster = monsters.get(r.nextInt(monsters.size()));
		}
		
		return new Monster(monster.getName(), monster.getLevel(), monster.getDamage(), monster.getDefense(), monster.getDodgeChance(), monster.getType());
	}
	
	public ArrayList<Monster> getRandomListOfMonsters(int listSize) {
		ArrayList<Monster> rhet = new ArrayList<>();
		
		for(int i = 0; i < listSize; i++) {
			rhet.add(this.getRandomMonster());
		}
		
		return rhet;
	}
	
	public ArrayList<Monster> getRandomListOfMonstersUnderLevel(int listSize, int maxLevel) {
		ArrayList<Monster> rhet = new ArrayList<>();
		
		for(int i = 0; i < listSize; i++) {
			rhet.add(this.getRandomMonsterUnderLevel(maxLevel));
		}
		
		return rhet;
	}
	
	public ArrayList<Monster> getRandomListOfMonstersBetweenLevels(int listSize, int levelMin, int maxLevel) {
		ArrayList<Monster> rhet = new ArrayList<>();
		
		for(int i = 0; i < listSize; i++) {
			rhet.add(this.getRandomMonsterBetweenLevels(levelMin, maxLevel));
		}
		
		return rhet;
	}
}
