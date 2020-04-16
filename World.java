import java.awt.Color;
import java.util.ArrayList;

import acm.util.RandomGenerator;

public class World {

	private int width;
	private int height;
	private ArrayList<LifeForm> creatureList;

	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.creatureList = new ArrayList<LifeForm>();
	}

	public void letTimePass(){

		checkAround3();
		moveCreatures();
		creaturesGetOlder();
		purgeTheDead();		
	}


	public void moveCreatures() {
		for(int i=0; i< creatureList.size(); i++) {
		//	if (creatureList.get(i).getMyColor()== Color.GREEN)
			creatureList.get(i).move(i);
		}
	}

/*
	public void checkAround() {
		for(int index=0; index<creatureList.size(); index++) {
			int x=creatureList.get(index).getMyLocation().getX();			
			int y=creatureList.get(index).getMyLocation().getY();
			int levelOfOGCreature=creatureList.get(index).getLevel();

			for (int plus=index+1; plus<creatureList.size()-1;plus++) {	

				if( (creatureList.get(plus).getMyLocation().getX())+1==x 
						&& (creatureList.get(plus).getMyLocation().getY())+1==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())+1==x 
						&& (creatureList.get(plus).getMyLocation().getY())-1==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())+1==x 
						&& (creatureList.get(plus).getMyLocation().getY())==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())-1==x 
						&& (creatureList.get(plus).getMyLocation().getY())+1==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())-1==x 
						&& (creatureList.get(plus).getMyLocation().getY())-1==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())-1==x 
						&& (creatureList.get(plus).getMyLocation().getY())==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())==x 
						&& (creatureList.get(plus).getMyLocation().getY())-1==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())==x 
						&& (creatureList.get(plus).getMyLocation().getY())+1==y ) {
					spreadDisease(levelOfOGCreature, plus);
				}

			}

		}
	}
*/
	public void checkAround2() {
		boolean x2 = false;
		boolean y2 = false;
		for(int index=0; index<creatureList.size(); index++) {
			int x=creatureList.get(index).getMyLocation().getX();             
			int y=creatureList.get(index).getMyLocation().getY();
			int levelOfOGCreature=creatureList.get(index).getLevel();
			for (int nextCreature=index+1; nextCreature<creatureList.size();nextCreature++) {
				if(creatureList.get(nextCreature).getMyLocation().getX()==x 
						&& creatureList.get(nextCreature).getMyLocation().getY()==y) {
				} else {
					for (int xVal = -1 ; xVal <= 1 ;xVal++) {     
						for(int yVal = -1 ; yVal <= 1; yVal++) {
							if(creatureList.get(nextCreature).getMyLocation().getX()==x+xVal) {
								x2=true;
							}
							if(creatureList.get(nextCreature).getMyLocation().getY()==y+yVal) {
								y2=true;
							}
							if (x2 == true && y2 == true) {
								spreadDisease(levelOfOGCreature, nextCreature, index);
							}
						}
					}
				}
			}
		}
	}
	public void checkAround3() {
		
		for(int index=0; index<creatureList.size(); index++) {
			int x=creatureList.get(index).getMyLocation().getX();             
			int y=creatureList.get(index).getMyLocation().getY();
			int levelOfOGCreature=creatureList.get(index).getLevel();
			for (int nextCreature=0; nextCreature<creatureList.size();nextCreature++) {
				if(creatureList.get(nextCreature).getMyLocation().getX()==x 
						&& creatureList.get(nextCreature).getMyLocation().getY()==y) {
				} else {
					for (int xVal = -1 ; xVal <= 1 ;xVal++) {     
						for(int yVal = -1 ; yVal <= 1; yVal++) {
							if(creatureList.get(nextCreature).getMyLocation().getX()==x+xVal && 
									creatureList.get(nextCreature).getMyLocation().getY()==y+yVal 
									&& creatureList.get(index).getLevel() != 0 ) {
								spreadDisease(levelOfOGCreature, nextCreature, index);
							}
						}
					}
				}
			}
		}
	}

	public void spreadDisease(int levelOfOGCreature,  int nextCreature, int index) {
		System.out.println("in Spread Disease");
		System.out.println(creatureList.get(nextCreature).getClass());
		System.out.println(creatureList.get(index).getClass());
		if(creatureList.get(nextCreature).getLevel()<levelOfOGCreature && creatureList.get(nextCreature).getLevel()==0) {
			int x=creatureList.get(nextCreature).getMyLocation().getX();
			int y=creatureList.get(nextCreature).getMyLocation().getY();
			int lifeSpan= creatureList.get(nextCreature).getMyLifeSpan();
			creatureList.add(new aysmptomatic(lifeSpan,new Location(x,y), Color.BLUE, this, levelOfOGCreature));
			System.out.println("added another asymp");
			
		//	creatureList.get(plus).isDead();
			

			int lifeSpan2=creatureList.get(nextCreature).getMyLifeSpan();
			creatureList.get(nextCreature).setAge(lifeSpan2);
		}
		purgeTheDead();
	}

	public void purgeTheDead(){
		int i=0;
		System.out.println("in purge the dead");
		while(i<creatureList.size()){
			if(creatureList.get(i).isDead()) {
				creatureList.remove(i);
				System.out.println("purging");
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
