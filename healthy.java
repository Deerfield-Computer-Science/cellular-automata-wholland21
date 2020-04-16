import java.awt.Color;
import acm.util.RandomGenerator;
//level 0
public class healthy extends Humans {

	public healthy(int life, Location l, Color Green, World w, int level) {	
		super(52, l, Green, w, level);
		myColor= Color.GREEN;	

	}

	public void move(int i) { 
		boolean move=true;	
		int x= myWorld.getCreatureList().get(i).getMyLocation().getX();
		x += rgen.nextInt(-1, 1);
		int y= myWorld.getCreatureList().get(i).getMyLocation().getY();
		y += rgen.nextInt(-1, 1);
		for (int plus=0; plus<myWorld.getCreatureList().size();plus++) {	
			if( (myWorld.getCreatureList().get(plus).getMyLocation().getX())==x 
					&& (myWorld.getCreatureList().get(plus).getMyLocation().getY())==y) {
				move= false;
				if (move==false) {
					break;
				}
			}
			if ((x > 99)|| (y > 99))  {
				move= false;
				if (move==false) {
					break;
				}
			}
		}
		if (move==true) {
			myWorld.getCreatureList().get(i).getMyLocation().setX(x);
			myWorld.getCreatureList().get(i).getMyLocation().setY(y);
		}
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();

}
