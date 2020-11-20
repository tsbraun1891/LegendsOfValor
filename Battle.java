/**
 * The Battle Class represents an ongoing battle within the game
 * world. It handles the AI behind the monsters and their attacks
 * and deals with resolving a battle.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	
	/* States of battle labeled from the hero perspective */
	public static enum BattleState {
		ONGOING,
		VICTORY,
		DEFEAT
	}
	
	private ArrayList<Hero> heroes;
	private ArrayList<Monster> monsters;
	private ArrayList<Actor> activeCombatants;
	private int turn, round;
	private LegendsGame game;
	
	public Battle(LegendsGame game, ArrayList<Hero> party) {
		this.heroes = party;
		this.activeCombatants = new ArrayList<>();
		this.activeCombatants.addAll(heroes);
		this.monsters = new ArrayList<>();
		this.turn = 0;
		this.round = 0;
		this.game = game;
		this.spawnNewMonsters();
	}
	
	public void startBattle(Scanner scanner) {
		this.turn = 0;
		BattleIO io = new BattleIO(this);
		
		io.startBattle(scanner);
	}
	
	public ArrayList<Hero> getHeroes() {
		return this.heroes;
	}
	
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	/**
	 * @return The actor (monster/hero) whose turn it is
	 */
	public Actor getTurnActor() {
		return this.activeCombatants.get(this.getTurn());
	}
	
	/**
	 * Handles all of the things that happen at the end of a round of battle,
	 * such as hereos regaining HP and Mana
	 */
	public void endOfRound() {
		for(Hero hero : heroes) {
			hero.recuperate();
		}
		
		this.round++;
		
		// We reset on 7 since we want it to happen every 8 turns and we start at 0
		if(this.round % 7 == 0) {
			this.spawnNewMonsters();
		}
	}
	
	public void nextTurn() {
		this.turn++;
		if(this.turn >= ( heroes.size() + monsters.size() )) {
			this.endOfRound();
			this.turn = 0;
		}
		
		// Skip an actors turn if they are defeated
		if(this.getTurnActor().isDefeated()) {
			nextTurn();
		}
	}
	
	public boolean isPlayerTurn() {
		return this.turn < heroes.size();
	}
	
	public void playerAttack(Monster targetMonster) {
		this.heroes.get(this.getTurn()).attack(targetMonster);
	}
	
	public void playerSpell(Monster targetMonster, Spell spell) {
		Hero hero = (Hero) this.getTurnActor();
		hero.spendMana(spell.getManaCost());
		spell.attackTarget(targetMonster);
	}
	
	public void playerUsePotion(Potion potion) {
		potion.usePotion(this.heroes.get(this.getTurn()));
	}
	
	/**
	 * As their action in a battle, a hero can open up their inventory
	 * in order to equip items or drink potions.
	 * @param scanner
	 */
	public void openInventory(Scanner scanner) {
		Hero currentHero = (Hero) this.getTurnActor();
		InventoryIO io = new InventoryIO(currentHero.getInventory());
		
		io.openInventory(scanner);
	}
	
	/**
	 * Handles the monster logic for when a monster does an attack
	 */
	public void monsterTurn() {
		// TODO: completely change monster logic for monster turn
		Monster monster = (Monster) this.getTurnActor();

		Piece monsterPiece=this.game.getMonsterPiece(monster);

		for(Hero target : this.heroes) {
			//check if there's a hero in monster's attack range
			Piece heroPiece=this.game.getHeroPiece(target);
			if(this.game.getBoard().inAttackRange(monsterPiece,heroPiece)){
				monster.attack(target);
				nextTurn();
				return;
			}
		}

		// move monster piece forward instead
		this.game.getBoard().movePieceTo(monsterPiece, this.game.getBoard().getPieceRow(monsterPiece) + 1, this.game.getBoard().getPieceCol(monsterPiece));
		nextTurn();	
	}
	
	public void spawnNewMonsters() {
		ArrayList<Monster> newMonsters = this.game.getMonsterList().getRandomListOfMonstersBetweenLevels(this.heroes.size(), this.game.getPartyLevel(), this.game.getPartyLevel());
		
		for(int i = 0; i < newMonsters.size(); i++) {
			Monster monster = newMonsters.get(i);
			this.monsters.add(monster);
			this.activeCombatants.add(monster);
			Piece piece = new Piece(monster, "M"+(this.monsters.size()-1), "A monster on the battlefield");
			this.game.getBoard().addMonsterPiece(piece);
			this.game.addMonsterPiece(piece);
			// Monsters always spawn in the top row
			this.game.getBoard().placePiece(piece, 0, i*3);
		}
	}
	
	public void resolveBattle() {
		BattleState battleState = this.checkBattleOver();
		
		if(battleState == BattleState.VICTORY) {
			int coinBonus = 0;
			
			for(Monster monster : this.monsters) {
				coinBonus += 100*monster.getLevel();
			}
			
			for(Hero hero : this.heroes) {
				
				if(hero.getHP() == 0) {
					hero.increaseHP(hero.maxHP()/2);
				} else {
					hero.addCoins(coinBonus);
					hero.increaseXP(2*this.monsters.size());
				}
			}
		} else {
			for(Hero hero : this.heroes) {
				hero.increaseHP(hero.maxHP()/2);
				hero.removeCoins((int) hero.getCoins()/2);
			}
		}
	}
	
	
	public BattleState checkBattleOver() {

		// First check if any heroes are in the monster nexus
		for(Piece piece : this.game.player.getPlayerPieces()) {
			if(this.game.getBoard().getPieceRow(piece) == 0) {
				return BattleState.VICTORY;
			}
		}

		// Now check if any of the monsters are in the hero nexus
		for(Piece piece : this.game.getMonsterPieces()) {
			if(this.game.getBoard().getPieceRow(piece) == 0) {
				return BattleState.DEFEAT;
			}
		}

		// If no one is in either of the nexi, the battle is ongoing		
		return BattleState.ONGOING;
	}
	
	
	public String toString() {
		String rhet = "\n\n";
		
		rhet += "Monsters:\n";
		
		int count = 0;
		for(Monster monster : monsters) {
			
			rhet += count + " - ";
			rhet += monster.toString();
			
			count++;
		}
		
		
		rhet += "\n---------------------------------------------------------------------------\n";
		
		rhet += "Heroes:\n";
		
		for(Hero hero : heroes) {
			rhet += hero.getName() + ", ";
			
			switch(hero.getHeroClass()) {
			case PALADIN:
				rhet += "The Paladin:";
				break;
				
			case SORCERER:
				rhet += "The Sorcerer:";
				break;
				
			case WARRIOR:
				rhet += "The Warrior:";
				break;
			}
			
			rhet += "  Lvl - " + hero.getLevel();
			rhet += "  |  HP - " + (int) hero.getHP();
			rhet += "  |  Mana - " + (int) hero.getMana();
			rhet += "  |  Defence - " + (int) hero.getDefense();
			rhet += "  |  Dodge - " + hero.getDodgeChance();
			rhet += "\n";
			
			rhet += hero.getInventory().tabbedEquippedToString();
			
			rhet += "\n\n";
		}
		
		return rhet;
	}

}
