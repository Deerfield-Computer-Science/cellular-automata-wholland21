import java.awt.Color;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {

	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 1000;

	public void run(){	
		setUpWorld();
		System.out.println(theWorld.getWidth());
		System.out.println(theWorld.getHeight());
		runWorld();

	}

	public void init(){
		resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void setUpWorld(){ 
		theWorld = new World(100,75);
		for(int number = 0 ; number < 750 ; number++) {
			theWorld.getCreatureList().add(new healthy(52,new Location(rgen.nextInt(0, 99),rgen.nextInt(0, 74)), Color.GREEN, theWorld, 0));
		}
		for(int number = 0 ; number < 20 ; number++) {
			theWorld.getCreatureList().add(new aysmptomatic(52,new Location(rgen.nextInt(0, 99),rgen.nextInt(0, 74)), Color.BLUE, theWorld, 1));
		}
		theWorldCanvas = this.getGCanvas();
	}

	public void runWorld(){
		drawWorld();
		countCreatures();
		System.out.println("original list ^");
		for(int i=0; i<24;i++){
			theWorld.letTimePass(i);
			countCreatures();
			pause(1000);
			drawWorld();
			
		}
	}	

	public void drawWorld(){
		drawBlankWorld();
		drawCreatures();
	}

	public void drawBlankWorld(){
		for(int row = 0 ; row<theWorld.getWidth(); row++)
			for(int col=0; col<theWorld.getHeight(); col++){
				GRect r = new GRect(row*10, col*10, 10, 10);
				r.setFillColor(Color.WHITE);
				r.setFilled(true);
				theWorldCanvas.add(r);
			}
	}

	public void drawCreatures(){
		for(LifeForm x: theWorld.getCreatureList()){
			GRect r = new GRect (x.getMyLocation().getX()*10, x.getMyLocation().getY()*10,10,10);
			r.setFillColor(x.getMyColor());
			r.setFilled(true);
			theWorldCanvas.add(r);
		}
	}
	public void countCreatures() {
		int aypm=0;
		int healthy=0;
		int symp=0;
		int recovered=0;
		for(LifeForm x: theWorld.getCreatureList()) {
			if(x.getMyColor()==Color.BLUE) {
				aypm= aypm+1;
			}
			if(x.getMyColor()==Color.GREEN) {
				healthy= healthy+1;
			}
			if (x.getMyColor()==Color.RED) {
				symp= symp +1;
			}
			if (x.getMyColor()==Color.BLACK) {
				recovered= recovered +1;
			}

		}
		System.out.print("Creature list size:"+ theWorld.getCreatureList().size()+""
				+ " ");
		System.out.print("number of asymp:"+ aypm);
		System.out.print("number of healthy:"+ healthy);
		System.out.print("number of symptomatic:"+ symp );
		System.out.print("number of recovered:"+ recovered );
		System.out.println("number of dead:"+theWorld.dead);
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();
}
