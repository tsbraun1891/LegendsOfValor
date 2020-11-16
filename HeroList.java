/**
 * This is a list of all the heroes in the game. These are actual instances
 * of the different hero types. HeroList also handles fetching a random
 * hero from these instances for you.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;
import java.util.Random;

public class HeroList {
	private ArrayList<Paladin> paladins;
	private ArrayList<Sorcerer> sorcerers;
	private ArrayList<Warrior> warriors;
	private Random r;
	
	public HeroList() {
		r = new Random();
		
		paladins = new ArrayList<>();
		paladins.add(new Paladin("Solonor Thelandria", 300, 750, 650, 700, 2500, 7));
		paladins.add(new Paladin("Sehanine Moonbow", 300, 750, 700, 700, 2500, 7));
		paladins.add(new Paladin("Skoraeus Stonebones", 250, 650, 600, 350, 2500, 4));
		paladins.add(new Paladin("Garl Glittergold", 100, 600, 500, 400, 2500, 5));
		paladins.add(new Paladin("Amaryllis Astra", 500, 500, 500, 500, 2500, 5));
		paladins.add(new Paladin("Caliber Heist", 400, 400, 400, 400, 2500, 8));
		
		
		sorcerers = new ArrayList<>();
		sorcerers.add(new Sorcerer("Rillifane Rallathil", 1300, 750, 450, 500, 2500, 9));
		sorcerers.add(new Sorcerer("Segojan Earthcaller", 900, 800, 500, 650, 2500, 5));
		sorcerers.add(new Sorcerer("Reign Havoc", 800, 800, 800, 800, 2500, 8));
		sorcerers.add(new Sorcerer("Reverie Ashels", 900, 800, 700, 400, 2500, 7));
		sorcerers.add(new Sorcerer("Radiant Ash", 800, 850, 400, 600, 2500, 6));
		sorcerers.add(new Sorcerer("Skye Soar", 1000, 700, 400, 500, 2500, 5));
		
		
		warriors = new ArrayList<>();
		warriors.add(new Warrior("Gaerdal Ironhand", 100, 700, 500, 600, 1354, 7));
		warriors.add(new Warrior("Sehanine Monnbow", 600, 700, 800, 500, 2500, 8));
		warriors.add(new Warrior("Muamman Duathall", 300, 900, 500, 750, 2546, 6));
		warriors.add(new Warrior("Flandal Steelskin", 200, 750, 650, 700, 2500, 7));
		warriors.add(new Warrior("Undefeated Yoj", 400, 800, 400, 700, 2500, 7));
		warriors.add(new Warrior("Eunoia Cyn", 400, 700, 800, 600, 2500, 6));
	}
	
	public Paladin getRandomPaladin() {
		return paladins.get(r.nextInt(paladins.size()));
	}
	
	public Sorcerer getRandomSorcerer() {
		return sorcerers.get(r.nextInt(sorcerers.size()));
	}
	
	public Warrior getRandomWarrior() {
		return warriors.get(r.nextInt(warriors.size()));
	}
}
