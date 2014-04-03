/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catsandmice;

import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Mandalaparthi
 */
public abstract class Animal {

    int NUM_DIRECTIONS = 4;
    int UP = 0;
    int DOWN = 1;
    int LEFT = 2;
    int RIGHT = 3;
    protected static int SIM_X_SIZE = 10;
    protected static int SIM_Y_SIZE = 10;
    static final int SEED = 1234567;
    protected static Animal[][] simulationWorld = new Animal[SIM_X_SIZE][SIM_Y_SIZE];
    protected static Random rgen = new Random(SEED);
    
    protected int xLocation;
    protected int yLocation;
    protected int age;
    protected int direction;
    protected int lastBreedingTime;

    /*
     * Two argumnet constructor that initializes age and lastBreedingTime to zero
     * direction to psuedorandom number and  xLocation and yLocation to the input parameters
     */
    public Animal(int x, int y) {
        age = 0;
        lastBreedingTime = 0;
        direction = rgen.nextInt(4);
        xLocation = x;
        yLocation = y;
    }
    /*
     * No argumnet constructor that initializes age and lastBreedingTime to zero
     * direction to psuedorandom number and  xLocation and yLocation to the input parameterspsuedorandom number between zero and 9
     */

    public Animal() {
        age = 0;
        lastBreedingTime = 0;
        direction = rgen.nextInt(4);
        do {
            xLocation = rgen.nextInt(SIM_X_SIZE);
            yLocation = rgen.nextInt(SIM_Y_SIZE);
        } while (isOccupied(xLocation, yLocation));
    }

    /**
     * Abstract method move to be overridden in subclasses
     */
    public abstract void move();

    /**
     * Abstract method breed to be overridden in subclasses
     */
    public abstract Animal breed();

    /**
     * if any of the cell in simulationWorld is empty it returns false else true
     *
     * @return boolean value true or false based on the empty cell in
     * simulationWorld
     */
    public static boolean isFull() {
        for (int i = 0; i < SIM_X_SIZE; i++) {
            for (int j = 0; j < SIM_Y_SIZE; j++) {
                if (simulationWorld[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Setter to set the simulationWorld present location value
     */
    public void setWorldLocation() {
        simulationWorld[xLocation][yLocation] = this;
    }

    /**
     * check the boundary values of the coordinates whether they are in
     * simulationWorld or not
     *
     * @param x x-coordinate of simulationWorld
     * @param y y-coordinate of simulationWorld
     * @return boolean value true if the coordinates are with in the range of
     * simulationWorld else false
     */
    public static boolean isValidLocation(int x, int y) {
        if (x >= 0 && x < SIM_X_SIZE && y >= 0 && y < SIM_Y_SIZE) {
            return true;
        }
        return false;
    }

    /**
     * check the current location of simulationWorld is occupied with animal or
     * not
     *
     * @param x x-coordinate of simulationWorld
     * @param y y-coordinate of simulationWorld
     * @return boolean value true if the location of simulationWorld is occupied
     * by animal object
     */
    public static boolean isOccupied(int x, int y) {
        if (simulationWorld[x][y] instanceof Animal) {
            return true;
        }
        return false;
    }

    /**
     * Prints data into to a file using simulationWorld array  
     * @param pw to write the data into a file (output.txt)
     */
    public static void printSimWorld(PrintWriter pw) {

        for (int i = 0; i < simulationWorld.length; i++) {
            for (int j = 0; j < simulationWorld[i].length; j++) {
                pw.write("+-");
            }
            pw.write("+\n");

            for (int j = 0; j < simulationWorld[i].length; j++) {
                if (simulationWorld[i][j] == null) {
                    pw.write("|" + " ");
                } else {
                    String str = simulationWorld[i][j] instanceof Cat ? "C" : "M";
                    pw.write("|" + str);
                }
            }
            pw.write("|\n");
        }
        for (int i = 0; i < simulationWorld.length; i++) {
            pw.write("+-");
        }
        pw.write("+\n");
        
    }
}