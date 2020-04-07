import java.util.ArrayList;

public class Grass extends Plant {
	
	public Grass(Location l, World w, int level) {
		super(l,w,0);
		myLifeSpan = 7;
	}
	
	public void reproduce() {
		// this is silly code really, but as an example
		int newX = (int)(Math.random()*20);
		int newY = (int)(Math.random()*20); 
		
		myWorld.getCreatureList().add(new Grass(new Location(newX,newY), myWorld, 0));
	}

}
