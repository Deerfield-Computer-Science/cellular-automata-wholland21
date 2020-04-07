import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 200;
	public static final int APPLICATION_HEIGHT = 200;
	
	public void run(){	
		setUpWorld();
		runWorld();
		
		System.out.print(theWorld.getCreatureList().get(0));
	
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(20,20);
		
		theWorld.getCreatureList().add(new Cow(1, new Location(3,3), Color.BLACK, theWorld, 2));
		theWorld.getCreatureList().add(new Cow(1, new Location(4,3), Color.BLACK, theWorld, 2));
		
		theWorld.getCreatureList().add( new Grass( new Location(5,4), theWorld, 0 ));
		theWorld.getCreatureList().add( new Grass( new Location(4,4), theWorld, 0 ));
		
		theWorld.getCreatureList().add(new Llama(1, new Location(5,5), Color.ORANGE, theWorld, 1));
		theWorld.getCreatureList().add(new Llama(1, new Location(6,5), Color.ORANGE, theWorld, 1));
		
		theWorldCanvas = this.getGCanvas();
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<4;i++){
			theWorld.letTimePass();
			pause(500);
			drawWorld();
			//System.out.print("REDRAWING");
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
}
