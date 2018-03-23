package assignment4;
/* CRITTERS Main.java
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

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.List;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        while(true) {
        	System.out.flush();
        System.out.println("critters>");

        String word1 = kb.next();
        
        //quit program DONE
		if(word1.equals("quit")) {
			System.out.println("quit");
			return;
		}
		
		//display coordinate map of critters DONE
		else if(word1.equals("show")) {
//			boolean extraWord = kb.hasNext();
//			if(extraWord == true) {
//				String word2 = kb.next();
//
//				System.out.println("error processing: " + word1 + " " + word2);
//
//			}
//			else {
			System.out.println("show");
			

			Critter.displayWorld();
//			}
		}
		
		//step through world
		else if(word1.equals("step")) {
			String word2 = kb.next();
//			boolean extraWord = kb.hasNext();
//			if(extraWord == true) {
//				String word3 = kb.next();
//
//				System.out.println("error processing: " + word1 + " " + word2 + " " + word3);
//
//			}
//			else{
				try {
					int numSteps = Integer.parseInt(word2);
					for(int i = 0; i < numSteps; i++) {
						Critter.worldTimeStep();
					}
				}
				catch(NumberFormatException e1){
					System.out.println("error processing: " + word1 + " " + word2);
				}
			
			//run worldtimestep for how many times
//			for(int i = 0; i < numSteps; i++) {
//				Critter.worldTimeStep();
//			}
			
				System.out.println("step");
			
			//Critter.worldTimeStep();
			//return list;
//			}
		}
		
		//seed
		else if(word1.equals("seed")) {
			String word2 = kb.next();
//			boolean extraWord = kb.hasNext();
//			if(extraWord == true) {
//				String word3 = kb.next();
//
//				System.out.println("error processing: " + word1 + " " + word2 + " " + word3);
//
//			}
//			else {
				try {
					int seedNum = Integer.parseInt(word2);
					Critter.setSeed(seedNum);
				}
				catch(NumberFormatException e1){
					System.out.println("error processing: " + word1 + " " + word2);
				}
//			}
			
		}
		
		//making new critters
		else if(word1.equals("make")) {
			 
			 //stage 1
			System.out.println("make");
			
			//for stage 2 and 3
			String word2 = kb.next();
			String word3 = kb.next();
			//String word4 = kb.
			//boolean extraWord = kb.hasNext();
//			if(extraWord == true) {
//				System.out.println("makebad");
//
//				String word4 = kb.next();
//
//				System.out.println("error processing: " + word1 + " " + word2 + " " + word3);
//
//			}
//			else {
			//	System.out.println("makegood");

			try {
				int makeNum = Integer.parseInt(word3);
				for(int i = 0; i < makeNum; i++) {
					try {
						Critter.makeCritter(word2);
					} catch (InvalidCritterException e) {
						// TODO Auto-generated catch block
						System.out.println("Invalid Critter Class: " + word2);
						
						//e.printStackTrace();
					}
				}
			}
			catch(NumberFormatException e1){
				System.out.println("error processing: " + word1 + " " + word2);
			}

//			}
		}
		 
		 //showing critter stats
		else if(word1.equals("stats")){
			String word2 = kb.next();
//			boolean extraWord = kb.hasNext();
//			if(extraWord == true) {
//				String word3 = kb.next();
//
//				System.out.println("error processing: " + word1 + " " + word2);
//
//			}
//			else {
			try {
				//Critter.runStats(Critter.getInstances(word2));
				//Critter.runStats();
				//Critter.runStats(Critter.getInstances(word2));
				//java.lang.reflect.Method method = null;
				Object obj = null;
				String runStats = "runStats";
//				try {
				String pkg = "assignment4.";

				pkg = pkg + word2;

				Class c =   Class.forName(pkg);//.newInstance();
				Critter crit = (Critter) c.newInstance();
				Class cd = crit.getClass();

				//Method method = cd.getMethod("runStats", List.class);

				
				
				java.lang.reflect.Method method = c.getMethod("runStats", List.class);
				System.out.println("lel");
				method.invoke(c, Critter.getInstances(word2));
				
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid Critter Class: " + word2);
				e.printStackTrace();
			}
//			catch (InvalidCritterException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
			catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			}
// catch (InstantiationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
 catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("invalid command: " + word1);
		}
		

        // System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();
        }
    }
}
