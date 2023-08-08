/*
 * Euromillionen
 * Generating random Euromillion tips based on the current time
 * Author: Michael Muehlberger
 * Last Change:  20.03.2023
 */

import java.util.*;

public class Euromillion {

    //creates unique random numbers for a single tip
    public static long createRandoNum(ArrayList<Long> numbers, int bound) {

        long rando = 0;
        Random random = new Random();

        do {
            rando = random.nextLong(bound) + 1;
        } while (numbers.contains(rando));

        return rando;
    }

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        long refTimeVar = 0;
        long timeDigit = 0;
        ArrayList<Long> numbers = new ArrayList<>();
        int sumofTime = 0;
        int tipNum = 0;

        final int EURO_NUMS = 5;
        final int STERNZAHLEN = 2;
        final int EURO_RANGE = 50;
        final int STERN_RANGE = 12;

        System.out.printf("Current time in ms: %d\n", time);

        refTimeVar = time;

        //gets Quersumme
        while (refTimeVar != 0) {

            timeDigit = refTimeVar % 10;
            numbers.add(timeDigit);
            refTimeVar = (refTimeVar - timeDigit) / 10;

            sumofTime += timeDigit;
        }

        System.out.println(numbers);
        System.out.printf("Sum of all digits: %d\n", sumofTime);

        // tips are 5 numbers between 1 and 50 and 2 numbers between 1 - 12
        //creates tips
        for (int i = 0; i < sumofTime; i++) {

            tipNum++;
            System.out.printf("Tipp %d: ", tipNum);
            numbers.clear();

            //adds euro numbers
            for (int m = 0; m < EURO_NUMS; m++) {

                numbers.add(createRandoNum(numbers, EURO_RANGE));
            }

            Collections.sort(numbers);
            System.out.printf("# " + numbers);

            numbers.clear();

            //adds sternzahlen
            for (int n = 0; n < STERNZAHLEN; n++) {

                numbers.add(createRandoNum(numbers, STERN_RANGE));
            }

            Collections.sort(numbers);
            System.out.println(" * " + numbers);

        }
    }
}