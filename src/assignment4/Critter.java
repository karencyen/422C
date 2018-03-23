package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	
	
//	protected void setEnergy(int e) {
//		energy = e;
//	}
//	protected void setCoords(int x, int y) {
//		x_coord = x;
//		y_coord = y;
//	}
	protected int getX() {
		return x_coord;
	}
	protected int getY() {
		return y_coord;
	}
	private void moveInDir(int direction) {
		//moving in the direction
				if(direction == 0) {
					x_coord = x_coord + 1;
					//y_coord = y_coord;
				}
				if(direction == 1) {
					x_coord = x_coord + 1;
					y_coord = y_coord + 1;		}
				if(direction == 2) {
					//x_coord = x_coord;
					y_coord = y_coord + 1;
				}
				if(direction == 3) {
					x_coord = x_coord - 1;
					y_coord = y_coord + 1;
				}
				if(direction == 4) {
					x_coord = x_coord - 1;
					//y_coord = y_coord;
				}
				if(direction == 5) {
					x_coord = x_coord - 1;
					y_coord = y_coord - 1;
				}
				if(direction == 6) {
					//x_coord = x_coord;
					y_coord = y_coord - 1;
				}
				if(direction == 7) {
					x_coord = x_coord + 1;
					y_coord = y_coord - 1;
				}
				
				//wrap around if coords arent on the map
				if(x_coord >= Params.world_width) {
					x_coord = 0;
				}
				if(x_coord < 0) {
					x_coord = Params.world_width;
				}
				if(y_coord >= Params.world_height) {
					y_coord = 0;
				}
				if(y_coord < 0) {
					y_coord = Params.world_height;
				}
	}
	/**
	 * This method walks the critter in any direction
	 * @param direction This integer is the direction in which the critter will walk
	 */
	protected final void walk(int direction) {
		moveInDir(direction);
//		//moving in the direction
//		if(direction == 0) {
//			x_coord = x_coord + 1;
//			//y_coord = y_coord;
//		}
//		if(direction == 1) {
//			x_coord = x_coord + 1;
//			y_coord = y_coord + 1;		}
//		if(direction == 2) {
//			//x_coord = x_coord;
//			y_coord = y_coord + 1;
//		}
//		if(direction == 3) {
//			x_coord = x_coord - 1;
//			y_coord = y_coord + 1;
//		}
//		if(direction == 4) {
//			x_coord = x_coord - 1;
//			//y_coord = y_coord;
//		}
//		if(direction == 5) {
//			x_coord = x_coord - 1;
//			y_coord = y_coord - 1;
//		}
//		if(direction == 6) {
//			//x_coord = x_coord;
//			y_coord = y_coord - 1;
//		}
//		if(direction == 7) {
//			x_coord = x_coord + 1;
//			y_coord = y_coord - 1;
//		}
//		
//		//wrap around if coords arent on the map
//		if(x_coord >= Params.world_width) {
//			x_coord = 0;
//		}
//		if(x_coord < 0) {
//			x_coord = Params.world_width;
//		}
//		if(y_coord >= Params.world_height) {
//			y_coord = 0;
//		}
//		if(y_coord < 0) {
//			y_coord = Params.world_height;
//		}
		
		//deplete energy
		energy = getEnergy() - Params.walk_energy_cost;
		
	}
	
	/**
	 * This method allows the critter to move two spaces in one direction
	 * @param direction Integer that shows which direction the critter will go
	 */
	protected final void run(int direction) {
		moveInDir(direction);
		moveInDir(direction);
		
		energy = getEnergy() - Params.run_energy_cost;
	}
	
	/**
	 * This method instantiates the baby critter with the default stats
	 * @param offspring Critter that is the baby of the critter
	 * @param direction Integer that indicates where the baby will spawn in relation to the parent critter
	 */
	protected final void reproduce(Critter offspring, int direction) {
		
		//half of parent's energy to child
		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;
		
		offspring.energy = this.getEnergy()/2;
		int energyInt = this.getEnergy()/2;
		double enD = this.getEnergy()/2;
		
		//rounding up
		if((enD - energyInt) >= 0.5) {
			energyInt += 1;
		}
		
		//half of parent's energy
		this.energy = energyInt;
		
		//place child coords
		offspring.moveInDir(direction);
		
		//place in babies list
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
//		Class cl =   Class.forName(critter_class_name);//.newInstance();
//		Constructor ctor = cl.getConstructor(String.class);
//		Critter cr = (Critter) ctor.newInstance();
		try {
			String pkg = "assignment4.";

			pkg = pkg + critter_class_name;
			//System.out.println(pkg);
//			Class cl =   Class.forName(pkg);//.newInstance();
//			Constructor ctor = cl.getConstructor(String.class);
//			Critter crit = (Critter) ctor.newInstance();
//			
			Class c =   Class.forName(pkg);//.newInstance();
			Critter crit = (Critter) c.newInstance();

			crit.x_coord = getRandomInt(Params.world_width);
			crit.y_coord = getRandomInt(Params.world_height);
			
			//System.out.print(crit.toString());

			crit.energy = Params.start_energy;

			population.add(crit);
			//population.add(crit);
		}
		catch(ClassNotFoundException e1) {
			throw new InvalidCritterException(critter_class_name);
		}
		catch(InstantiationException e2) {
			//throw new InvalidCritterException(critter_class_name);
			System.out.println("instantiation exception");
		}
		catch(IllegalAccessException e3) {
			//throw new InvalidCritterException(critter_class_name);
			System.out.println("illegal exception");
		}

		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//			catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(int i = 0; i < population.size(); i++) {
			if(critter_class_name.equals("Craig") && population.get(i).toString().equals("C")) {
				result.add(population.get(i));
			}
			if(critter_class_name.equals("Algae") && population.get(i).toString().equals("@")) {
				result.add(population.get(i));
			}
			if(critter_class_name.equals("Critter1") && population.get(i).toString().equals("1")) {
				result.add(population.get(i));
			}
			if(critter_class_name.equals("Critter2") && population.get(i).toString().equals("2")) {
				result.add(population.get(i));
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
		// Complete this method.
	}
	/**
	 * This method runs through all doTimeStep()s and updates the world
	 */
	public static void worldTimeStep() {
		//timestep++;
		
		//2. doing timesteps for pop and babies
		for(int i = 0; i<population.size();i++) {
			population.get(i).doTimeStep();
		//	population.get(i).energy = population.get(i).getEnergy() - Params.rest_energy_cost;
		}
//		for(int i = 0; i<babies.size();i++) {
//			babies.get(i).doTimeStep();
//		//	babies.get(i).energy = babies.get(i).getEnergy() - Params.rest_energy_cost;
//
//		}
		
		//3.doEncounters();
		
		for(int i = 0; i < population.size(); i++) {
			for( int k = 0; k < population.size(); k++) {
				if(population.get(k).x_coord == population.get(i).x_coord && population.get(k).y_coord == population.get(i).y_coord && (i!=k)) {
					//occupying same spot == fight
					//k response to i
					boolean kFight = population.get(k).fight(population.get(i).toString());
					boolean iFight = population.get(i).fight(population.get(k).toString());

					//run away
					int tempX;
					int tempY;
					if(kFight == false) {

					}
					if(iFight == false) {
						
					}
					//i response to k
					int rollK;
					int rollI;
					if( (population.get(k).getEnergy()>0) && (population.get(i).getEnergy()>0) && population.get(k).x_coord == population.get(i).x_coord && population.get(k).y_coord == population.get(i).y_coord) {
						//if both critters are still alive and are in the same place
						if(kFight == true) {
							rollK = Critter.getRandomInt(population.get(k).getEnergy());
							
						}
						else {
							rollK = 0;
							
						}
						if(iFight == true) {
							rollI = Critter.getRandomInt(population.get(i).getEnergy());

						}
						else {
							rollI = 0;
						}
						
						//see who wins the dice roll and gets to live
						if(rollK > rollI) {
							System.out.println(population.get(k).toString() + " defeated " + population.get(i).toString() );
							population.get(k).energy += (population.get(i).getEnergy()/2);
							population.get(i).energy = 0;
						}
						else {
							System.out.println(population.get(i).toString() + " defeated " + population.get(k).toString() );

							population.get(i).energy += (population.get(k).getEnergy()/2);
							population.get(k).energy = 0;

						}
					}
				}
			}
		}
			
		//4.update rest energy
		for(int i = 0; i < population.size(); i++) {
			population.get(i).energy -= Params.rest_energy_cost;
		}
		//NEED TO REMOVE THE DEAD
		List<Critter> deadCrits = new java.util.ArrayList<Critter>();
		for(Critter c : population) {
			if(c.getEnergy()<=0) {
				deadCrits.add(c);
			}
		}
		System.out.println(deadCrits.size() + " total critters dead");
		population.removeAll(deadCrits);
			
		//5.generate algae
		
		for(int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter("Algae");
			} catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
			
		//6.move babies
		for(int i = 0; i < babies.size(); i++) {
			population.add(babies.get(i));
		}
		babies.clear();
		// Complete this method.
	}
	/**
	 * This method displays the coordinate map of the critters.
	 */
	public static void displayWorld() {
		boolean critStamp = false;
		//top vertical border
		System.out.print("+");
		for(int i = 0; i<Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		
		for(int i = 0; i < Params.world_height; i++) {

			System.out.print("|");
			for(int k = 0; k < Params.world_width; k++) {
				critStamp = false;

				//print white spaces if no critters
				if(population.size()==0) {
					System.out.print(" ");
					
				}
				//check each coord to see if there is a critter
				else {
					for(int j = 0; j < population.size(); j++) {
						if((population.get(j).x_coord == k) && (population.get(j).y_coord == i)) {
							//System.out.print("gu");

							System.out.print(population.get(j).toString());
							critStamp = true;
							j = population.size()+5;
						}

					}
					if(critStamp == false) {
						System.out.print(" ");
					}
				}

			}
			System.out.println("|");

		}
		
		System.out.print("+");
		for(int i = 0; i<Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		// Complete this method.
	}
}
