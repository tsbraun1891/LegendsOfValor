/**
 * LegendsGame serves as a point where all of the data needed to
 * run a game comes together. Through the LegendsGame class, a user
 * can access all of the other relevant pieces needed to run a game.
 * @author Tanner Braun
 *
 */

import java.util.ArrayList;

public class LegendsGame {
	private ItemList itemList;
	private HeroList heroList;
	private MonsterList monsterList;
	private LegendBoard board;
	private LegendsActions actions;
	private Player player;
	private ArrayList<Hero> party;
	
	public LegendsGame(String playerName, int boardSize, int numPaladins, int numSorcerers, int numWarriors) {
		// H represents the player's party
		this.player = new Player(playerName, new Piece("H"));
		
		itemList = new ItemList();
		heroList = new HeroList();
		monsterList = new MonsterList();
		
		board = new LegendBoard(boardSize, boardSize, player);
		
		actions = new LegendsActions(this, player, board);
		
		party = new ArrayList<Hero>();
		
		for(int i = 0; i < numPaladins; i++) {
			party.add(heroList.getRandomPaladin());
		}
		
		for(int i = 0; i < numSorcerers; i++) {
			party.add(heroList.getRandomSorcerer());
		}
		
		for(int i = 0; i < numWarriors; i++) {
			party.add(heroList.getRandomWarrior());
		}
		
	}
	
	
	public LegendsActions getActionHandler() {
		return actions;
	}
	
	public LegendBoard getBoard() {
		return board;
	}
	
	public ArrayList<Hero> getParty() {
		return party;
	}

	public ItemList getItemList() {
		return itemList;
	}

	public MonsterList getMonsterList() {
		return monsterList;
	}
	
	// Returns the average level of the party
	public int getPartyLevel() {
		int sum = 0;
		
		for(Hero hero : this.getParty()) {
			sum += hero.getLevel();
		}
		
		return sum/this.getParty().size();
	}
	
}
