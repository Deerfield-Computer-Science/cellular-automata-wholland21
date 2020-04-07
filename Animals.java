import java.awt.Color;

public abstract class Animals  extends LifeForm {

	public Animals(int myLifeSpan, Location loc, Color Color, World w, int level) {
		super(myLifeSpan, loc, Color, w, level);
		
		//Animal's levels are above 0
	}
}
