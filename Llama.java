import java.awt.Color;
import java.util.ArrayList;

	public class Llama extends Animals {
		
		public Llama(int life, Location l, Color Orange, World w, int level) {	
			super(7, l, Orange, w, level);
			myColor= Color.ORANGE;
		}
		public void reproduce() {
			// this is silly code really, but as an example
			int newX = (int)(Math.random()*20);
			int newY = (int)(Math.random()*20); 
//			if () {
//			
//			}
		
		
		myWorld.getCreatureList().add(new Llama(1,new Location(newX,newY), myColor, myWorld,1));
		}
	}

