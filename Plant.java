import java.awt.Color;

public abstract class Plant extends LifeForm {

	public Plant(Location loc, World w, int level) {
		super(loc, w,level);
		myColor = Color.green;
	}
}
