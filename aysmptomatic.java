import java.awt.Color;
import acm.util.RandomGenerator;
//level 1
public class aysmptomatic extends Humans {

	public aysmptomatic(int life, Location l, Color Green, World w, int level) {	
		super(52, l, Green, w, level,0);
		myColor= Color.BLUE;	

	}

	public void move(int i) { 
		boolean move=true;		
		int x= myWorld.getCreatureList().get(i).getMyLocation().getX();
		x += rgen.nextInt(-5, 5);	
		int y= myWorld.getCreatureList().get(i).getMyLocation().getY();
		y += rgen.nextInt(-5, 5);	
		for (int plus=0; plus<myWorld.getCreatureList().size();plus++) {	
			if( (myWorld.getCreatureList().get(plus).getMyLocation().getX())==x 
					&& (myWorld.getCreatureList().get(plus).getMyLocation().getY())==y) {
				move= false;
				if (move==false) {
					break;
				}
			}
			if ((x > 98)|| (y > 74))   {
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

