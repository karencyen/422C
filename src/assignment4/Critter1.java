package assignment4;

//import java.util.*;
/**
 * 
 * @author karen
 *
 */
public class Critter1 extends Critter {
	public String toString() { return "1"; }
	
	private static final int cluster = 4;
	private int goal;
	private int goal_x;
	private int goal_y;
	//private int[] genes = new int[8];
	private int dir;
	private int randOrGo;
	
	public Critter1() {
		//where the critter will like to cluster
		goal = Critter.getRandomInt(cluster);
		setGoalCoords();
		randOrGo = Critter.getRandomInt(5);
	}
	/**
	 * This method indicates the coordinates of where the critter wants to go
	 */
	public void setGoalCoords() {
		//top left corner
		if(goal == 0) {
			goal_x = (Params.world_width/4);
			goal_y = (Params.world_height/4);
		}
		//top right corner
		else if(goal == 1) {
			goal_x = (3*Params.world_width)/4;
			goal_y = (Params.world_height/4);
		}
		//bottom left corner
		else if(goal == 2) {
			goal_x = (Params.world_width/4);
			goal_y = (3*Params.world_height/4);
		}
		//bottom right corner.
		else if(goal == 3) {
			goal_x = (3*Params.world_width/4);
			goal_y = (3*Params.world_height/4);
		}
	}
	/**
	 * This method indicates which direction the critter should go.
	 * @return The direction the critter will go
	 */
	public int chooseDir() {
		//see if the critter should go randomly, or go to their goal
		
		//if 1-4, the critter will go randomly
		if(randOrGo != 0) {
			randOrGo++;
			if(randOrGo >=3) {
				randOrGo = 0;
			}
			//randOrGo =randOrGo % 4;
			return Critter.getRandomInt(8);

		}
		
		//if 0, critter will find fastest path to goal
		
		int[] dirArray = {0,1,2,3,4,5,6,7};
		int[] x_co = new int[8];
		int[] y_co = new int[8];
		double[] delta = new double[8];
		double goThisDir = 999999999;
		int intDir = 0;
		
			//get potential coordinates
			x_co[0] = getX() + 1;
			y_co[0] = getY();

			x_co[1] = getX() + 1;
			y_co[1] = getY() + 1;
			
			x_co[2] = getX();
			y_co[2] = getY() + 1;
			
			x_co[3] = getX() - 1;
			y_co[3] = getY() + 1;


			x_co[4] = getX() - 1;
			y_co[4] = getY();

			x_co[5] = getX() - 1;
			y_co[6] = getY() - 1;

			x_co[6] = getX();
			y_co[6] = getY() - 1;

			x_co[7] = getX() + 1;
			y_co[7] = getY() - 1;

		
		
		for(int i = 0; i < 8; i++) {
			
			//wrap around coords
			if(x_co[i] >= Params.world_width) {
				x_co[i] = 0;
			}
			if(x_co[i] < 0) {
				x_co[i] = Params.world_width;
			}
			if(y_co[i] >= Params.world_height) {
				y_co[i] = 0;
			}
			if(y_co[i] < 0) {
				y_co[i] = Params.world_height;
			}
			//find shortest distance
			delta[i] = (goal_x - x_co[i])*(goal_x - x_co[i]) + (goal_y -y_co[i])*(goal_y -y_co[i]);
			delta[i] = Math.sqrt(delta[i]);
			
			//System.out.println(delta[i] + "  ");
			
			if(delta[i]<goThisDir) {
				goThisDir = delta[i];
				intDir = i;
			}
		}
//		for(int i = 0; i < 8; i++) {
//			if(delta[i]<goThisDir) {
//				goThisDir = i;
//			}
//		}
		//System.out.println("here" + delta[intDir]);

		//go direction of the least direction
		randOrGo++;
		//randOrGo =randOrGo % 4;
		return intDir;
	}
	@Override
	/**
	 * This method determines whether the critter will move towards the goalm or move randomly
	 */
	public void doTimeStep() {
		
		//take either a random step or a step towards goal
		int walkOrRun = Critter.getRandomInt(1);
		if(walkOrRun == 0) {
			walk(chooseDir());
		}
		else {
			run(chooseDir());
		}
		//walk(chooseDir());
		
		//reproduce
//		if(getEnergy() >= Params.min_reproduce_energy+50) {
//
//			Critter1 child = new Critter1();
//			child.goal = this.goal;
//			child.goal_x = this.goal_x;
//			child.goal_y = this.goal_y;
//
//			reproduce(child, Critter.getRandomInt(8));
//		}
		
		
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 80) return true;
		return false;
	}
	
	
	public static void runStats(java.util.List<Critter> critter1s) {
		int topLeft = 0;
		int topRight = 0;
		int botLeft = 0;
		int botRight = 0;
		
		for (Object obj : critter1s) {
			Critter1 c = (Critter1) obj;
			if(c.goal == 0) {
				topLeft++;
			}
			else if(c.goal == 1) {
				topRight++;
			}
			else if(c.goal == 2) {
				botLeft++;
			}
			else if(c.goal == 3) {
				botRight++;
			}
		}
		System.out.println("" + critter1s.size() + " total Critter1s    ");
		System.out.println("" + topLeft + " Critter1s going to the top left    ");
		System.out.println("" + topRight + " Critter1s going to the top right    ");
		System.out.println("" + botLeft + " Critter1s going to the bottom left    ");
		System.out.println("" + botRight + " Critter1s going to the bottom right    ");

		System.out.println();
	}
//	public void test (List<Critter> l) {
//		
//	}
}
