 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catsandmice;

import static catsandmice.Animal.isValidLocation;
import static catsandmice.Animal.rgen;

/**
 *
 * @author Mandalaparthi
 */
public class Mouse extends Animal {

    int MOUSE_BREEDING_INTERVAL = 5;

    /**
     * No argument constructor that calls the super class constructor.
     */
    public Mouse() {
        super();
    }

    /**
     * Two argument constructor that calls the super class argument constructor
     *
     * @param x x-coordinate of the stimulatedWorld
     * @param y y-coordinate of the stimulatedWorld
     */
    public Mouse(int x, int y) {
        super(x, y);
    }

    /**
     * This method allows a Mouse to create another Mouse by placing the new
     * Mouse in a cell in the simulationWorld that is adjacent (right next to)
     * itself. This location is chosen exactly once using the pseudorandom
     * number generator rgen. If the chosen cell contains another Animal, no
     * breeding occurs, meaning no new Mouse is created. If the cell is empty a
     * new Mouse is created and placed in that cell. Once a Mouse successfully
     * breeds, lastBreedingTime is set to the Mouse’s current age. This method
     * returns a reference to the Mouse that was created if breeding was
     * successful. It returns null, otherwise.
     *
     * @return the Mouse object that breed
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
                if (this.age - lastBreedingTime >= MOUSE_BREEDING_INTERVAL) {
                    xLocation = newX;
                    yLocation = newY;
                    lastBreedingTime = this.age;
                    Mouse m = new Mouse(xLocation, yLocation);
                    return m;
                }
            }
        }
        return null;
    }

    /**
     * This method allows a Mouse to attempt to make exactly one movement of one
     * cell within the simulationWorld array in a pseudorandom direction (UP,
     * DOWN, LEFT, or RIGHT). You must use the pseudorandom number generator
     * rgen to choose the direction. You may only allow a Mouse to make a
     * movement to a valid, unoccupied position. Use the isValidLocation and
     * isOccupied methods from the Animal class to check the position. If a
     * Mouse is able to move, its reference is removed from its old location in
     * the simulationWorld and placed in its new location in the
     * simulationWorld. A Mouse must increment its age within this method,
     * regardless of the results.
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
            if (!isOccupied(newX, newY)) {
                xLocation = newX;
                yLocation = newY;
                simulationWorld[xLocation][yLocation] = simulationWorld[oldX][oldY];
                simulationWorld[oldX][oldY] = null;
            }
        }
        age++;
    }

    /**
     * This method returns true if simulationWorld[xLocation][yLocation] is not
     * equal to the reference for this Mouse. It returns false, otherwise.
     *
     * @return boolean value true if the current position belongs to the present
     * Mouse object else false
     */
    public boolean wasEaten() {
        if (simulationWorld[xLocation][yLocation] != this) {
            return true;
        }
        return false;
    }
}
