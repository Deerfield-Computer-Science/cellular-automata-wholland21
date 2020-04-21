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
		
		purgeTheDead();
		checkAround3();
	//	moveCreatures();
		creaturesGetOlder();
		//killOrRecover(i);
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
				
				int number= rgen.nextInt(0,99);
				int x=creatureList.get(index).getMyLocation().getX();
				int y=creatureList.get(index).getMyLocation().getY();
				int lifeSpan= creatureList.get(index).getMyLifeSpan();
				
				if (creatureList.get(index).getMyColor()==Color.RED) {
					if (number<30) {
						creatureList.get(index).setAge(lifeSpan);
						dead++;
						purgeTheDead();
					} else {
						creatureList.get(index).setAge(lifeSpan);
						purgeTheDead();
						creatureList.add(new recovered(lifeSpan,new Location(x,y), Color.BLACK, this, 2));
					}
				}
				if (creatureList.get(index).getMyColor()==Color.BLUE) {
					if (number<90) {
						creatureList.get(index).setAge(lifeSpan);
						purgeTheDead();
						creatureList.add(new symptomatic(lifeSpan,new Location(x,y), Color.RED, this, 1));
					} else {
						creatureList.get(index).setAge(lifeSpan);
						purgeTheDead();
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
				for (int nextCreature=0; nextCreature<size;nextCreature++) {
					if(creatureList.get(nextCreature).getMyLocation().getX()==x 
							&& creatureList.get(nextCreature).getMyLocation().getY()==y) {
					} else {
						for (int xVal = -1 ; xVal <= 1 ;xVal++) {     
							for(int yVal = -1 ; yVal <= 1; yVal++) {
								if(creatureList.get(nextCreature).getMyLocation().getX()==x+xVal && 
										creatureList.get(nextCreature).getMyLocation().getY()==y+yVal) 
								{
									spreadDisease(levelOfOGCreature, nextCreature, index);
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	public void spreadDisease(int levelOfOGCreature,  int nextCreature, int index) {
		if(creatureList.get(nextCreature).getLevel()<levelOfOGCreature && creatureList.get(index).getLevel()==1) {
			int x=creatureList.get(nextCreature).getMyLocation().getX();
			int y=creatureList.get(nextCreature).getMyLocation().getY();
			int lifeSpan= creatureList.get(nextCreature).getMyLifeSpan();
			int add= rgen.nextInt(0,99);
			if(add<20) {
				creatureList.set(index, new symptomatic(lifeSpan,new Location(x,y), Color.RED, this, levelOfOGCreature));
				int lifeSpan2=creatureList.get(nextCreature).getMyLifeSpan();
				creatureList.get(nextCreature).setAge(lifeSpan2);
			} else if (add<70) {
				creatureList.set(index, new aysmptomatic(lifeSpan,new Location(x,y), Color.BLUE, this, levelOfOGCreature));
				int lifeSpan2=creatureList.get(nextCreature).getMyLifeSpan();
				creatureList.get(nextCreature).setAge(lifeSpan2);
			} 

		}
		purgeTheDead();
	}

	public void purgeTheDead(){
		int i=0;
	//	System.out.println("in purge the dead");
		while(i<creatureList.size()){
			if(creatureList.get(i).isDead()) {
				creatureList.remove(i);
	//			System.out.println("purging");
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
