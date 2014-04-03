/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catsandmice;

import static catsandmice.Animal.isOccupied;
import static catsandmice.Animal.isValidLocation;
import static catsandmice.Animal.rgen;
import static catsandmice.Animal.simulationWorld;

/**
 *
 * @author Mandalaparthi
 */
public class Cat extends Animal {

    int CAT_BREEDING_INTERVAL = 8;
    int CAT_STARVATION_INTERVAL = 5;
    int timeSinceLastMeal;

    /**
     * No argument constructor that calls the super class constructor and
     * initializes timeSinceLastMeal to 0
     */
    public Cat() {
        super();
        timeSinceLastMeal = 0;
    }

    /**
     * Two argument constructor that calls the super class argument constructor
     * and assigns timeSinceLastMeal to 0
     *
     * @param x x coordinate of simulationWorld
     * @param y y coordinate of simulationWorld
     */
    public Cat(int x, int y) {
        super(x, y);
        timeSinceLastMeal = 0;
    }

    /**
     * This method allows a Cat to attempt to make exactly one movement of one
     * cell within the simulationWorld array in a pseudorandom direction (UP,
     * DOWN, LEFT, or RIGHT). You must use the pseudorandom number generator
     * rgen to choose the direction. You may only allow a Cat to make a movement
     * to either a valid, unoccupied position or a valid position that contains
     * a Mouse. Use the isValidLocation and isOccupied methods from the Animal
     * class to check the positions. If a Cat moves to an unoccupied position or
     * if it is unable to move, it must increment timeSinceLastMeal. If a Cat is
     * able to move, its reference is removed from its old location in the
     * simulationWorld and placed in its new location in the simulationWorld. If
     * a Cat moves to a position that contains a Mouse, it removes the Mouse
     * from the simulationWorld array, replaces it with its own reference, and
     * sets timeSinceLastMeal to zero. A Cat must increment its age within this
     * method, regardless of the results.
     */
    @Override
    public void move() {
        int loc = rgen.nextInt(4);
        int oldX = xLocation;
        int oldY = yLocation;
        int newX = xLocation;
        int newY = yLocation;
        if (loc == 0) {
            newX--;
        } else if (loc == 1) {
            newX++;
        } else if (loc == 3) {
            newY++;
        } else if (loc == 2) {
            newY--;
        }

        if (isValidLocation(newX, newY)) {
            xLocation = newX;
            yLocation = newY;
            if (isOccupied(newX, newY)) {
                if (simulationWorld[xLocation][yLocation] instanceof Cat) {
                    timeSinceLastMeal++;
                } else {
                    timeSinceLastMeal = 0;
                    simulationWorld[xLocation][yLocation] = simulationWorld[oldX][oldY];
                    simulationWorld[oldX][oldY] = null;
                }
            } else {
                simulationWorld[xLocation][yLocation] = simulationWorld[oldX][oldY];
                simulationWorld[oldX][oldY] = null;
                timeSinceLastMeal++;
            }
        } else {
            timeSinceLastMeal++;
        }
        age++;

    }

    /**
     * This method allows a Cat to create another Cat by placing the new Cat in
     * a cell in the simulationWorld that is adjacent (right next to) itself.
     * This location is chosen exactly once using the pseudorandom number
     * generator rgen. If the chosen cell contains another Animal, no breeding
     * occurs, meaning no new Cat is created. If the cell is empty, a new Cat is
     * created and placed in that cell. Once a Cat successfully breeds,
     * lastBreedingTime is set to the Cat’s current age. This method returns a
     * reference to the Cat that was created if breeding was successful. It
     * returns null, otherwise
     *
     * @return the Cat object that breed
     */
    @Override
    public Animal breed() {

        int loc = rgen.nextInt(4);
        int oldX = xLocation;
        int oldY = yLocation;
        int newX = xLocation;
        int newY = yLocation;
        if (loc == 0) {
            newX--;
        } else if (loc == 1) {
            newX++;
        } else if (loc == 3) {
            newY++;
        } else if (loc == 2) {
            newY--;
        }
        if (isValidLocation(newX, newY)) {
            if (!isOccupied(newX, newY)) {
                if (this.age - lastBreedingTime >= CAT_BREEDING_INTERVAL) {
                    xLocation = newX;
                    yLocation = newY;
                    lastBreedingTime = this.age;
                    Cat c = new Cat(xLocation, yLocation);
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * if timeSinceLastMeal is > 5 the simulationWorld location of cat is set to
     * null and returns true else false
     *
     * @return boolean value true if timeSinceLastMeal is > 5 else false
     */
    public boolean isStarving() {
        if (timeSinceLastMeal >= 5) {
            simulationWorld[xLocation][yLocation] = null;
            return true;
        }
        return false;
    }
}
