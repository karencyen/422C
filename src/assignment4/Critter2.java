package assignment4;

//import java.util.*;
/**
 * 
 * @author karen
 *
 */
public class Critter2 extends Critter {
	public String toString() { return "2"; }
	
	
	private int dir;
	//critter move in rectangles
	private int length;
	private int width;
	private int perim;
	public Critter2() {
		length = Critter.getRandomInt(Params.world_width/4);
		width = Critter.getRandomInt(Params.world_height/4);
		perim = 0; //2*length + 2*width;
	}
	
	public int chooseDir() {
		//choose a path of the rectangle to go
		if(perim <length) {
			perim++;
			return 0;
		}
		else if(perim <(length+width)) {
			perim++;
			return 2;
		}
		else if(perim <(length+width+length)) {
			perim++;
			return 4;
		}
		else if(perim <(length+width+length+width)) {
			perim = 0;
			return 6;
		}
		return 0;
	}
	@Override
	public void doTimeStep() {
		
		int walkOrRun = Critter.getRandomInt(1);
		if(walkOrRun == 0) {
			walk(chooseDir());
		}
		else {
			run(chooseDir());
		}
		
		//reproduce
		if(getEnergy() >= Params.min_reproduce_energy + 70) {
				
			Critter2 child = new Critter2();
			child.length = this.length;
			child.width = this.width;

			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 100) return true;
		return false;
	}
	
	
//	public void test (List<Critter> l) {
//		
//	}
}
