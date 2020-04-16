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
		runWorld();
		countCreature();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(100,100);
		
		theWorld.getCreatureList().add(new healthy(52,new Location(10,10), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(10,11), Color.BLUE, theWorld, 1));
		theWorld.getCreatureList().add(new healthy(52,new Location(11,12), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(11,13), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new healthy(52,new Location(12,14), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(12,15), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(11,15), Color.BLUE, theWorld, 1));

		theWorld.getCreatureList().add(new healthy(52,new Location(14,16), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(10,16), Color.BLUE, theWorld, 1));
		theWorld.getCreatureList().add(new healthy(52,new Location(15,16), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(16,17), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new healthy(52,new Location(12,17), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(17,18), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(18,18), Color.BLUE, theWorld, 1));
		
		theWorld.getCreatureList().add(new healthy(52,new Location(19,19), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(10,11), Color.BLUE, theWorld, 1));
		theWorld.getCreatureList().add(new healthy(52,new Location(20,19), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(22,20), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(24,20), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(26,20), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(11,23), Color.BLUE, theWorld, 1));

		theWorld.getCreatureList().add(new healthy(52,new Location(28,23), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(10,11), Color.BLUE, theWorld, 1));
		theWorld.getCreatureList().add(new healthy(52,new Location(30,25), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(32,25), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(35,23), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(37,30), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(11,30), Color.BLUE, theWorld, 1));
		
		theWorld.getCreatureList().add(new healthy(52,new Location(50,50), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(90,91), Color.BLUE, theWorld, 1));
		theWorld.getCreatureList().add(new healthy(52,new Location(51,52), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(51,53), Color.GREEN, theWorld, 0));		theWorld.getCreatureList().add(new healthy(52,new Location(52,54), Color.GREEN, theWorld, 0));
		theWorld.getCreatureList().add(new healthy(52,new Location(52,55), Color.GREEN, theWorld, 0));
//		theWorld.getCreatureList().add(new aysmptomatic(52,new Location(51,55), Color.BLUE, theWorld, 1));
		
		theWorldCanvas = this.getGCanvas();
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<52;i++){
			theWorld.letTimePass();
			pause(500);
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
	public void countCreature() {
		int aypm=0;
		int healthy=0;
		for(LifeForm x: theWorld.getCreatureList()) {
			if(x.getMyColor()==Color.BLUE) {
				aypm= aypm+1;
			}
			if(x.getMyColor()==Color.GREEN) {
				healthy= healthy+1;
			}
		}
		System.out.print("Creature list size:"+ theWorld.getCreatureList().size());
		System.out.print("number of asymp:"+ aypm);
		System.out.print("number of healthy:"+ healthy+ " ");
	}
}
