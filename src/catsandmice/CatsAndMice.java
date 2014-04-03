/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catsandmice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mandalaparthi
 */
public class CatsAndMice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        int numberOfCats = 0;
        int numberOfMice = 0;
        int numberOfTimeSteps = 0;
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        ArrayList<Animal> catsAndMice = new ArrayList<>();

        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            numberOfCats = sc.nextInt();
            numberOfMice = sc.nextInt();
            numberOfTimeSteps = sc.nextInt();
        }
        for (int i = 0; i < numberOfCats; i++) {
            Cat c = new Cat();
            catsAndMice.add(c);
            c.setWorldLocation();
        }
        for (int i = 0; i < numberOfMice; i++) {
            Mouse m = new Mouse();
            catsAndMice.add(m);
            m.setWorldLocation();
        }
        pw.write("simulation world\n");
        Animal.printSimWorld(pw);
        //
        for (int i = 0; i < numberOfTimeSteps; i++) {
            pw.write("Time step " + (i + 1) + "\n");
            int numAnimals = catsAndMice.size();
            for (int j = 0; j < numAnimals; j++) {
                Animal a = catsAndMice.get(j);
                if (a instanceof Mouse) {
                    if (((Mouse) a).wasEaten()) {
                        catsAndMice.remove(a);
                        numAnimals--;
                        j--;
                    } else {
                        a.move();
                        Animal animal = a.breed();
                        if (animal != null) {
                            catsAndMice.add(animal);
                            animal.setWorldLocation();
                        }
                    }
                } else {
                    if (((Cat) a).isStarving()) {
                        catsAndMice.remove(a);
                        numAnimals--;
                        j--;
                    } else {
                        a.move();
                        Animal animal = a.breed();
                        if (animal != null) {
                            catsAndMice.add((Cat) animal);
                            animal.setWorldLocation();
                        }

                    }
                }

            }
            Animal.printSimWorld(pw);
        }
        pw.close();

    }
}
