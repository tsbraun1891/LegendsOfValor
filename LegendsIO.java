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
			
			System.out.println(map.toString());
			
			while(continuePlaying) {
				System.out.print("\n\nPlease Input a command (Press H for help): ");
				
				String inputVal = scanner.next();	
				
				switch(inputVal.charAt(0)) {
				case 'W':
				case 'w':
					if(!actions.move(scanner, LegendsActions.Direction.UP))
						this.invalidMove();
					break;
					
				case 'S':
				case 's':
					if(!actions.move(scanner, LegendsActions.Direction.DOWN))
						this.invalidMove();
					break;
					
				case 'A':
				case 'a':
					if(!actions.move(scanner, LegendsActions.Direction.LEFT))
						this.invalidMove();
					break;
					
				case 'D':
				case 'd':
					if(!actions.move(scanner, LegendsActions.Direction.RIGHT))
						this.invalidMove();
					break;
					
				case 'Q':
				case 'q':
					continuePlaying = false;
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
					if(!actions.openMarket(scanner)) {
						System.out.print("You are not on a market space! ");
						this.retry();
					}					
					break;
					
				case 'I':
				case 'i':
					this.displayInformation();
					break;
					
				case 'E':
				case 'e':
					this.displayInformation();
					int heroIndex = this.safeGetInt("\nWhich hero's equipment would you like to manage? ", scanner);
					if(!actions.manageEquipment(scanner, heroIndex)) {
						System.out.print("Invalid hero entered. ");
						this.retry();
					}
					
					break;
					
				default:
					System.out.print("Invalid command input. ");
					this.retry();
				}
			}
			
			System.out.println("Thanks for playing!");
			scanner.close();
		}
		
		private void displayInformation() {
			System.out.println(actions.displayPartyInformation());
		}
		
		
		private void getStartingInfo(Scanner scanner) {
			
			System.out.println("~~~~~~~~ Welcome to Legends: Monsters and Heroes! ~~~~~~~~");
			System.out.print("What is your name, adventurer? ");
			
			String playerName = scanner.nextLine();
			
			int boardSize = this.safeGetInt("\nWhat would you like the size of your board to be? ", scanner);
			if(boardSize <= 0) {
				boardSize = 1;
			}
			
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
			
			
			game = new LegendsGame(playerName, boardSize, numPal, numSorc, numWar);
		}
		
		public void invalidMove() {
			System.out.print("Invalid move direction. Tile is either inaccessible (I) or is out of bounds. ");
			this.retry();
		}
		
		public void endGame() {
			continuePlaying = false;
		}
}
