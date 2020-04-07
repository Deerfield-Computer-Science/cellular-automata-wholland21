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
		
		makeNewCreatures();
		checkAround();
		moveCreatures();
		
		//eatThings();
		creaturesGetOlder();
		purgeTheDead();		
	}
	
	public void makeNewCreatures() {
		
		int currentSizeOfCreatureList = creatureList.size();
		System.out.println("size of list is "+currentSizeOfCreatureList);
		for(int i=0; i< currentSizeOfCreatureList; i++) {
			creatureList.get(i).reproduce();
		}
	}
	
	public void moveCreatures() {
		for(int i=0; i< creatureList.size(); i++) {
			creatureList.get(i).getMyColor();
			if(creatureList.get(i).getMyColor()==Color.BLACK) {
				int ex= creatureList.get(i).getMyLocation().getX();
				ex += rgen.nextInt(-1, 1);
				creatureList.get(i).getMyLocation().setX(ex);
				
				int why= creatureList.get(i).getMyLocation().getY();
				why += rgen.nextInt(-1, 1);
				creatureList.get(i).getMyLocation().setY(why);
			}
			if(creatureList.get(i).getMyColor()==Color.ORANGE) {
				int ex= creatureList.get(i).getMyLocation().getX();
				ex += rgen.nextInt(-1, 1);
				creatureList.get(i).getMyLocation().setX(ex);
				
				int why= creatureList.get(i).getMyLocation().getY();
				why += rgen.nextInt(-1, 1);
				creatureList.get(i).getMyLocation().setY(why);
			}
		}
	}
	//This tries to have characters eat. It says it works but there is an error 
	//and I cannot figure out where.
	public void checkAround() {
		for(int index=0; index<creatureList.size(); index++) {
			int ex=creatureList.get(index).getMyLocation().getX();			
			int why=creatureList.get(index).getMyLocation().getY();
			int level=creatureList.get(index).getLevel();
			
			for (int plus=index+1; plus<creatureList.size()-1;plus++) {	
				if( (creatureList.get(plus).getMyLocation().getX())+1==ex 
						&& (creatureList.get(plus).getMyLocation().getY())+1==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())+1==ex 
						&& (creatureList.get(plus).getMyLocation().getY())-1==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())+1==ex 
						&& (creatureList.get(plus).getMyLocation().getY())==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())-1==ex 
						&& (creatureList.get(plus).getMyLocation().getY())+1==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())-1==ex 
						&& (creatureList.get(plus).getMyLocation().getY())-1==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())-1==ex 
						&& (creatureList.get(plus).getMyLocation().getY())==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())==ex 
						&& (creatureList.get(plus).getMyLocation().getY())-1==why) {
					eatThings(level, plus);
				}
				if( (creatureList.get(plus).getMyLocation().getX())==ex 
						&& (creatureList.get(plus).getMyLocation().getY())+1==why) {
					eatThings(level, plus);
				}
				
			}
			
		}
	}

	
	public void eatThings(int level,  int plus) {
	//	System.out.print("trying to eat	");
		if(creatureList.get(plus).getLevel()<level) {
	//		System.out.print("ate");
			creatureList.get(plus).isDead();
		}
		purgeTheDead();
	}
	
	public void purgeTheDead(){
		int i=0;
		while(i<creatureList.size()){
			if(creatureList.get(i).isDead())
				creatureList.remove(i);
			else
				i++;
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
