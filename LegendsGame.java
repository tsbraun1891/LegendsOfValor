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
	private ArrayList<Piece> playerPieces, monsterPieces;
	private Battle battle;
	
	public LegendsGame(String playerName, int numPaladins, int numSorcerers, int numWarriors) {
		itemList = new ItemList();
		heroList = new HeroList();
		monsterList = new MonsterList();
		
		
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



		playerPieces=new ArrayList<>();
		for(int i=0;i<party.size();i++){
			String Hid="H"+String.valueOf(i);//Hero Piece id starts from H0
			playerPieces.add(new Piece(party.get(i),Hid,"H"));// H represents the player's party

		}
		this.player = new Player(playerName,playerPieces);


		monsterPieces=new ArrayList<>();
		for(int i=0;i<3;i++){// Create 3 monsters Pieces on the board
			String Mid="M"+String.valueOf(i);//Monster Piece id starts from M0
			monsterPieces.add(new Piece(monsterList.getRandomMonster(),Mid,"M"));// M represents the monster
		}

		this.board = new LegendBoard(player);
		
		battle = new Battle(this, party);
		
		actions = new LegendsActions(this, player, board);
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
	
	public ArrayList<Piece> getPlayerPieces() {
		return this.playerPieces;
	}
	
	public ArrayList<Piece> getMonsterPieces() {
		return this.monsterPieces;
	}
	
	public Battle getBattle() {
		return this.battle;
	}
	
	/**
	 * Find the piece related to the specified hero
	 * @param hero
	 * @return the piece, or null if there is no piece related to the hero
	 */
	public Piece getHeroPiece(Hero hero) {
		for(Piece piece : this.playerPieces) {
			if(piece.getActor().equals(hero)) {
				return piece;
			}
		}
		
		return null;
	}
	
	/**
	 * Find the piece related to the specified monster
	 * @param monster
	 * @return the piece, or null if there is no piece related to the monster
	 */
	public Piece getMonsterPiece(Monster monster) {
		for(Piece piece : this.monsterPieces) {
			if(piece.getActor().equals(monster)) {
				return piece;
			}
		}
		
		return null;
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
