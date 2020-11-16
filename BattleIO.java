/**
 * The BattleIO class is an extension of IO.  This class interacts with the player
 * during a battle, and gets their commands over each round. On a hero's turn, the
 * player has to select a target and a possible action for that target.
 */

import java.util.Scanner;


public class BattleIO extends IO{
	private Battle battle;
	
	public BattleIO(Battle battle) {
		this.battle = battle;
	}
	
	/**
	 * Displays the battle between the heroes and the monsters
	 * @param scanner - scanner to use to interact with the player
	 * @return True if the heroes win, false if the monsters win
	 */
	public void startBattle(Scanner scanner) {
		boolean exit = false;
		
		while(!exit && battle.checkBattleOver() == Battle.BattleState.ONGOING) {
			
			System.out.println(this.battle);	
			
			
			if(battle.isPlayerTurn()) {
				Hero hero = (Hero) this.battle.getTurnActor();
				boolean tookTurn = false;
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
				
				System.out.println(hero.getName() + "'s Turn!\n");
				
				while(!tookTurn) {					
					System.out.print("What should " + hero.getName() + " do? Attack (A), Cast a Spell (S), or access the equipment in your inventory (I): ");
					
					String inputVal = scanner.next();
					
					switch(inputVal.charAt(0)) {
					case 'A':
					case 'a':
						tookTurn = true;
						this.battle.playerAttack(this.selectTarget(scanner));
						break;
						
					case 'S':
					case 's':
						Monster target = this.selectTarget(scanner);
						Spell spell = this.selectSpell(scanner, hero);
						if(spell != null) {
							tookTurn = true;
							this.battle.playerSpell(target, spell);
						}						
						break;
						
					case 'I':
					case 'i':
						this.battle.openInventory(scanner);
						tookTurn = true;
						break;
						
					case 'Q':
					case 'q':
						tookTurn = true;
						exit = true;
						break;
					
					default:
						tookTurn = false;
						System.out.print("Invalid command input. ");
						this.retry();
					}
				}
				
			} else {
				Monster monster = (Monster) this.battle.getTurnActor();
				
				System.out.println(monster.getName() + "'s Turn!");
				
				// Wait for user to press enter key
				System.out.println("\nPress enter to continue...");
				try{System.in.read();}
				catch(Exception e){}
				
				battle.monsterTurn();			
			}
			
			battle.nextTurn();
		}		
		
		if(battle.checkBattleOver() == Battle.BattleState.VICTORY) {
			System.out.println("\n\nThe party has claimed VICTORY!!");
		} else {
			System.out.println("\n\nThe party has suffered DEFEAT!!");
		}

		battle.resolveBattle();
	}
	
	public Monster selectTarget(Scanner scanner) {
		boolean targetSelected = false;
		int choice = -1;
		
		while(!targetSelected) {
			choice = this.safeGetInt("\n\nPlease select a target (Use index by monster name): ", scanner);
			
			if(choice >= this.battle.getMonsters().size() || choice < 0) {
				System.out.print("Invalid index given! ");
				this.retry();
			} else if(this.battle.getMonsters().get(choice).isDefeated()) {
				System.out.println("That monster is already defeated! ");
				this.retry();
			} else {
				targetSelected = true;
			}
			
		}
		
		return this.battle.getMonsters().get(choice);
	}
	
	public Spell selectSpell(Scanner scanner, Hero hero) {
		Inventory inv = hero.getInventory();
		System.out.println(inv.toString());
		
		int spellIndex = this.safeGetInt("\n\nWhich spell would you like to use? ", scanner);
	
		if(spellIndex < 0 || spellIndex >= inv.getItems().size()) {
			System.out.print("\n\nYou have entered an invalid index! ");
			this.retry();
			return null;
		} else if(inv.getItems().get(spellIndex).getType() != Item.ItemType.SPELL) {
			System.out.println("\n\nThat is not a spell! ");
			this.retry();
			return null;
		} else if(hero.getMana() < ( (Spell) inv.getItems().get(spellIndex) ).getManaCost()) {
			System.out.println("\n\nYou do not have enough mana for that spell! ");
			this.retry();
			return null;
		} else {
			return (Spell) inv.getItems().get(spellIndex);
		}
	}
	
}
