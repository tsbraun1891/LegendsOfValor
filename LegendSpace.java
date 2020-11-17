/**
 * LegendSpace is an extension of the space class that adds a 
 * space's type to the board. Spaces can be common, a market,
 * or impassable.
 * @author Tanner Braun
 *
 */

public class LegendSpace extends Space{

	public static enum SpaceType {
		NEXUS,
		INACCESSIBLE,
		PLAIN,
		BUSH,
		CAVE,
		KOULOU
	}
	
	private SpaceType type;
	
	public LegendSpace(SpaceType type) {
		super();
		this.type = type;
	}
	
	public SpaceType getType() {
		return type;
	}
	
	public boolean hasHeroOnSpace() {		
		for(Piece piece : this.getCurrentPieces()) {
			if(piece.isHero()) {
				return true;
			}
		}
		
		// If we get here, there are no heroes
		return false;
	}
}
