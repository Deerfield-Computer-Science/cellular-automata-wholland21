import java.awt.Color;
import java.util.ArrayList;

import acm.util.RandomGenerator;

public class World {

	private int width;
	private int height;
	private ArrayList<LifeForm> creatureList;
	public int dead;

	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.creatureList = new ArrayList<LifeForm>();
	}

	public void letTimePass(int i){
		
	
		checkAround3();
		moveCreatures();
		creaturesGetOlder();
		killOrRecover(i);
		purgeTheDead();		
	}


	public void moveCreatures() {
		for(int i=0; i< creatureList.size(); i++) {
			creatureList.get(i).move(i);
		}
	}

	public void killOrRecover(int i) {	
		if (i%4==0 && i!=0) {
			for(int index=0; index< creatureList.size(); index++) {
				LifeForm creature=creatureList.get(index);
				int number= rgen.nextInt(0,99);
				int x=creature.getMyLocation().getX();
				int y=creature.getMyLocation().getY();
				int lifeSpan= creature.getMyLifeSpan();
				
				if (creature.getMyColor()==Color.RED) {
					if (number<30) {
						creature.alive = false;
						dead++;
						
					} else {
						creature.alive = false;
						creatureList.add(new recovered(lifeSpan,new Location(x,y), Color.BLACK, this, 2));
					}
				}
				if (creature.getMyColor()==Color.BLUE) {
					if (number<90) {
						creature.alive = false;					
						creatureList.add(new symptomatic(lifeSpan,new Location(x,y), Color.RED, this, 1));
					} else {
						creature.alive = false;						
						creatureList.add(new recovered(lifeSpan,new Location(x,y), Color.BLACK, this, 2));
					}
				}	
			}
		}
	}
	
	
	public void checkAround3() {
		int size = creatureList.size();
		for(int index=0; index<size; index++) {
			if(creatureList.get(index).getLevel() != 0) {
				int x=creatureList.get(index).getMyLocation().getX();             
				int y=creatureList.get(index).getMyLocation().getY();
				int levelOfOGCreature=creatureList.get(index).getLevel();
				
				for (int nextCreatureInd=0; nextCreatureInd<size;nextCreatureInd++) {
					LifeForm nextCreature = creatureList.get(nextCreatureInd);
					if(nextCreature.getMyLocation().getX()==x 
							&& nextCreature.getMyLocation().getY()==y) {
					} else {
						for (int xVal = -1 ; xVal <= 1 ;xVal++) {     
							for(int yVal = -1 ; yVal <= 1; yVal++) {
								if(nextCreature.getMyLocation().getX()==x+xVal && 
										nextCreature.getMyLocation().getY()==y+yVal) 
								{
									spreadDisease(levelOfOGCreature, nextCreature, index);
									//break;
								}
							}
						}
					}
				}
			}
		}
	}

	public void spreadDisease(int levelOfOGCreature,  LifeForm healthyPerson, int index) {
		if(healthyPerson.getLevel()==0 && creatureList.get(index).getLevel()==1) {
			int x=healthyPerson.getMyLocation().getX();
			int y=healthyPerson.getMyLocation().getY();
			int lifeSpan= healthyPerson.getMyLifeSpan();
			int add= rgen.nextInt(0,99);
			if(add<20) {
				creatureList.set(index, new symptomatic(lifeSpan,new Location(x,y), Color.RED, this, levelOfOGCreature));
				healthyPerson.alive = false;
				System.out.println("Healthy person replaced with symptomatic at location ("+x+","+y+")");
			} else if (add<70) {
				creatureList.set(index, new aysmptomatic(lifeSpan,new Location(x,y), Color.BLUE, this, levelOfOGCreature));
				healthyPerson.alive = false;
				System.out.println("Healthy person replaced with asymptomatic at location ("+x+","+y+")");
			} 
		}
	}

	public void purgeTheDead(){
		int i=0;
		while(i<creatureList.size()){
			if(creatureList.get(i).isDead()) {
				creatureList.remove(i);
			}else {
				i++;
			}
		}	
	}

	public void creaturesGetOlder(){
		for(LifeForm l:creatureList){
			l.age(1);
		}
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<LifeForm> getCreatureList() {
		return creatureList;
	}
	public void setCreatureList(ArrayList<LifeForm> creatureList) {
		this.creatureList = creatureList;
	}

	@Override
	public String toString() {
		return "World [width=" + width + ", height=" + height
				+ ", creatureList=" + creatureList + "]";
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();
}
