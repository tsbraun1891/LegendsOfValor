/**
 * The LegendsIO class deals directly with the user and routing their
 * commands to the proper game logic while also directing the user
 * on valid inputs.
 */


import java.util.Scanner;

public class LegendsIO extends IO {
	
		private LegendsGame game;
		private LegendBoard map;
		private LegendsActions actions;
		
		public LegendsIO() {
			
		}

		
		public void runGame() {
			Scanner scanner = new Scanner(System.in);
			
			
			this.getStartingInfo(scanner);
			
			continuePlaying = true;
			
			actions = game.getActionHandler();
			
			map = game.getBoard();
			
			while(continuePlaying) {
				boolean tookTurn = false;

				actions.displayMap();
				System.out.println("\nRound " + this.game.getBattle().getRound());
				System.out.println("Turn " + this.game.getBattle().getTurn());
				while(!tookTurn) {
					if(this.game.getBattle().isPlayerTurn()) {
						Piece currentPiece = game.getHeroPiece((Hero) game.getBattle().getTurnActor());
				
						System.out.print("\nPlease Input a command for " + currentPiece.getActor().getName() + "(" + currentPiece.getIdentifier() + ") (Press H for help): ");
						
						String inputVal = scanner.next();	
						
						switch(inputVal.charAt(0)) {
						case 'W':
						case 'w':
							tookTurn = actions.move(scanner, LegendsActions.Direction.UP, currentPiece);
							if(!tookTurn)
								this.invalidMove();
							break;
							
						case 'S':
						case 's':
							tookTurn = actions.move(scanner, LegendsActions.Direction.DOWN, currentPiece);
							if(!tookTurn)
								this.invalidMove();
							break;
							
						case 'A':
						case 'a':
							tookTurn = actions.move(scanner, LegendsActions.Direction.LEFT, currentPiece);
							if(!tookTurn)
								this.invalidMove();
							break;
							
						case 'D':
						case 'd':
							tookTurn = actions.move(scanner, LegendsActions.Direction.RIGHT, currentPiece);
							if(!tookTurn)
								this.invalidMove();
							break;
							
						case 'Q':
						case 'q':
							continuePlaying = false;
							tookTurn = true;
							break;
						
						case 'H':
						case 'h':
							actions.getHelp();
							break;
							
						case 'M':
						case 'm':
							actions.displayMap();
							break;
							
						case 'P':
						case 'p':
							if(!actions.openMarket(scanner, currentPiece)) {
								System.out.print("You are not on a Nexus space! ");
								this.retry();
							}					
							break;
							
						case 'I':
						case 'i':
							System.out.println(actions.displayPartyInformation());
							break;

						case 'U':
						case 'u':
							System.out.println(this.game.getBattle().toString());
							break;
							
						case 'E':
						case 'e':
							actions.manageEquipment(scanner, currentPiece);	
							tookTurn = true;				
							break;

						case 'B':
						case 'b':
							tookTurn = actions.back(currentPiece);
							if(!tookTurn) {
								System.out.println("You cannot back because a hero is already at this column's nexus space!");
							}
							break;

						case 'T':
						case 't':
							int row = this.safeGetInt("\nWhich row would you like to tp to? ", scanner);
							int column = this.safeGetInt("\nWhich column would you like to tp to? ", scanner);

							tookTurn = actions.teleport(currentPiece, row, column);
							if(!tookTurn) {
								System.out.println("\n You have entered an invalid position. \n You may only tp to different lanes behind allies and in front of enemies. \n");
								this.retry();
							}
							break;

						case 'F':
						case 'f':
							int monsterIndex = this.safeGetInt("Please insert the number (x in \'Mx\') of the monster you would like to attack: ", scanner);
							tookTurn = actions.attack(currentPiece, monsterIndex);
							
							if(!tookTurn)
								System.out.println("That piece is not in range! They can at most be one space away.");
							break;

						case 'C':
						case 'c':
							Spell spellToCast = this.selectSpell(scanner, (Hero) currentPiece.getActor());
							if(spellToCast != null) {
								int monsterIndex2 = this.safeGetInt("Please insert the number (x in \'Mx\') of the monster you would like to attack: ", scanner);
								tookTurn = actions.castSpell(currentPiece, monsterIndex2, spellToCast);
								if(!tookTurn)
									System.out.println("That piece is not in range! They can at most be one space away.");
							}								
							else 
								tookTurn = false;
							
							break;
						default:
							System.out.print("Invalid command input. ");
							this.retry();
						}
					} else {
						Piece currentPiece = game.getMonsterPiece((Monster) game.getBattle().getTurnActor());
						System.out.print(currentPiece.getActor().getName() + "\'s (" + currentPiece.getIdentifier() + ") Turn: Type anything and press enter to continue... ");
						scanner.next();
						this.game.getBattle().monsterTurn();
						tookTurn = true;
					}
					
				}
				
				this.game.getBattle().nextTurn();
				if(this.game.getBattle().checkBattleOver() != Battle.BattleState.ONGOING) {
					continuePlaying = false;
				}
			}

			actions.displayMap();

			if(this.game.getBattle().checkBattleOver() == Battle.BattleState.VICTORY) {
				System.out.println("The Heroes have defeated the Monsters!");
			} else if(this.game.getBattle().checkBattleOver() == Battle.BattleState.DEFEAT) {
				System.out.println("The Monsters have crushed the Heroes!");
			}
			
			System.out.println("Thanks for playing!\n\n");
			scanner.close();
		}
		
		
		private void getStartingInfo(Scanner scanner) {
			
			System.out.println("~~~~~~~~ Welcome to Legends: Monsters and Heroes! ~~~~~~~~");
			System.out.print("What is your name, adventurer? ");
			
			String playerName = scanner.nextLine();
			
			int numHeroes = 0, numPal, numSorc, numWar;
			
			do {
				System.out.println("\n\n\nNow we will create your party!  You can have at most 3 heroes in your party.");
				
				numPal = this.safeGetInt("\nHow many paladins would you like in your party? ", scanner);
				
				numSorc = this.safeGetInt("\nHow many sorcerers would you like in your party? ", scanner);
				
				numWar = this.safeGetInt("\nHow many warriors would you like in your party? ", scanner);
				
				numHeroes = numPal + numSorc + numWar;
				
				if( numHeroes > 3 || numHeroes < 1 ) {
					System.out.println("\n\nInvalid number of heroes. Must have a party size > 0 and < 4.");
				}
			} while(numHeroes > 3 || numHeroes < 1);
			
			
			game = new LegendsGame(playerName, numPal, numSorc, numWar);
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
		
		public void invalidMove() {
			System.out.print("Invalid move direction. \nTile is either inaccessible (I), out of bounds, or full. ");
			this.retry();
		}
		
		public void endGame() {
			continuePlaying = false;
		}
}
