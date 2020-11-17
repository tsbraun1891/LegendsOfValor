/**
 * LegendsGame serves as a point where all of the data needed to
 * run a game comes together. Through the LegendsGame class, a user
 * can access all of the other relevant pieces needed to run a game.
 * @author Tanner Braun
 *
 */

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

public class LegendsGame {
	private ItemList itemList;
	private HeroList heroList;
	private MonsterList monsterList;
	private LegendBoard board;
	private LegendsActions actions;
	private Player player;
	private ArrayList<Hero> party;
	
	public LegendsGame(String playerName, int boardSize, int numPaladins, int numSorcerers, int numWarriors) {


		
		itemList = new ItemList();
		heroList = new HeroList();
		monsterList = new MonsterList();
		

		
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



		ArrayList<Piece> playerPieces=new ArrayList<>();

		for(int i=0;i<party.size();i++){
			String Hid="H"+String.valueOf(i);//Hero Piece id starts from H0
			String Mid="M"+String.valueOf(i);//Monster Piece id starts from M0
			playerPieces.add(new Piece(party.get(i),Hid,"H"));// H represents the player's party
			playerPieces.add(new Piece(monsterList.getRandomMonster(),Mid,"M"));// M represents the monster
		}
		this.player = new Player(playerName,playerPieces);


		ArrayList<Piece> monsterPieces=new ArrayList<>();
		for(int i=0;i<3;i++){// Create 3 monsters Pieces on the board
			String Mid="M"+String.valueOf(i);//Monster Piece id starts from M0
			playerPieces.add(new Piece(party.get(i),Mid,"H"));// H represents the player's party
			playerPieces.add(new Piece(monsterList.getRandomMonster(),Mid,"M"));// M represents the monster
		}
		board = new LegendBoard(player,monsterPieces);


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
