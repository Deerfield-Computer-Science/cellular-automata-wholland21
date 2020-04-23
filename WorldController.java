import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;

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
		writeCreaturesToFile2();

	}

	public void init(){
		resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void setUpWorld(){ 
		theWorld = new World(100,75);
		int number =0;
		while(number < 4000) {
			boolean place = true;
			int x=rgen.nextInt(0, 99);
			int y=rgen.nextInt(0, 74);
			for (int plus=0; plus<theWorld.getCreatureList().size();plus++) {	
				if( (theWorld.getCreatureList().get(plus).getMyLocation().getX())==x 
						&& (theWorld.getCreatureList().get(plus).getMyLocation().getY())==y) {
					place= false;
					break;
				}
			}
			if (place==true) {
				theWorld.getCreatureList().add(new healthy(52,new Location(x,y), Color.GREEN, theWorld, 0));
				number++;
			}
		}
		number =0;
		while(number < 20) {
				boolean place = true;
				int x=rgen.nextInt(0, 99);
				int y=rgen.nextInt(0, 74);
				for (int plus=0; plus<theWorld.getCreatureList().size();plus++) {	
					if( (theWorld.getCreatureList().get(plus).getMyLocation().getX())==x 
							&& (theWorld.getCreatureList().get(plus).getMyLocation().getY())==y) {
						place= false;
						break;
					}
				}
				if (place==true) {
					theWorld.getCreatureList().add(new aysmptomatic(52,new Location(x,y), Color.BLUE, theWorld, 1));
					number++;					
				}
			}
		theWorldCanvas = this.getGCanvas();
	}

	public void runWorld(){
		drawWorld();
		countCreatures();
		System.out.println("original list ^");
		for(int i=0; i<5;i++){
			theWorld.letTimePass();
			countCreatures();
			pause(1000);
			drawWorld();
		//	writeCreaturesToFile();			
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
	public void writeCreaturesToFile() {
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
		try {
			FileWriter myWriter = new FileWriter("pandemicData.csv");
			myWriter.write(healthy +","+aypm+","+symp+","+recovered+","+ theWorld.dead);
			myWriter.close();
			System.out.println("File written");
		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
	
	public void writeCreaturesToFile2() {
		try {
			FileWriter myWriter = new FileWriter("pandemicDataTotal2.csv");
			for(int i=0; i<theWorld.deads.size(); i++){	
				myWriter.write(theWorld.healths.get(i)+","+theWorld.asms.get(i)+","+theWorld.ssmsm.get(i)+","+theWorld.recoves.get(i)+","+theWorld.deads.get(i));	
				myWriter.write(System.getProperty( "line.separator" ));
			}
			myWriter.close();
			System.out.println("File written");
		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
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
		System.out.print(healthy +","+aypm+","+symp+","+recovered+","+ theWorld.dead);
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();
}
