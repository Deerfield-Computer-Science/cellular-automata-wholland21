import java.awt.Color;
import java.util.ArrayList;

public class Cow extends Animals {

	public Cow(int life, Location l, Color BLACK, World w, int level) {
		
		super(7, l, BLACK, w, level);
		myColor= Color.BLACK;
		
	}
	public void reproduce() {
		// this is silly code really, but as an example
		int newX = (int)(Math.random()*20);
		int newY = (int)(Math.random()*20); 
//		if () {
//			
//		}
		
		
		myWorld.getCreatureList().add(new Cow(1,new Location(newX,newY), myColor, myWorld, 2));
	}
}
