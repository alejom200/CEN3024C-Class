/* 
Class:      Software Development CEN-3024C
Project:    Module 8 Assignment
Program:    Concurrency Assignment

Description:
- Implement a parallel arrayay sum
- Compare performance with single thread sum.
- Make an arrayay of 200 million random numbers.
- Random numbers between 1 and 10.
- Compute the sum in parallel using multiple threads.
- Compute the sum with only one thread.
- Display the sum and times for both cases.
Ref: https://www.chegg.com/homework-help/questions-and-answers/java-programming-question-implement-parallel-array-sum-compare-performance-single-thread-s-q46195841
*/

// Import Java Library
import java.util.*;

class SumCalculation extends Thread {
    private int[] array;
    private int lowNum, highNum, partial;

// Function: Sum Calculation
    public SumCalculation(int[] array, int lowNum, int highNum) {
        this.array = array;
        this.lowNum = lowNum;
        this.highNum = Math.min(highNum, array.length);
    }

    public int getPartialSum() {
        return partial;
    }

    public void run() {
        partial = sum(array, lowNum, highNum);

    }

    public static int sum(int[] array) {
        return sum(array, 0, array.length);

    }

    public static int sum(int[] array, int lowNum, int highNum) {
        int total = 0;

        for (int i = lowNum; i < highNum; i++) {
            total += array[i];

        }

        return total;

    }

    public static int sumParallel(int[] array) {
        return sumParallel(array, Runtime.getRuntime().availableProcessors());
    }

    public static int sumParallel(int[] array, int threads) {
        int size = (int) Math.ceil(array.length * 1.0 / threads);
        SumCalculation[] sums = new SumCalculation[threads];

        for (int i = 0; i < threads; i++) {
            sums[i] = new SumCalculation(array, i * size, (i + 1) * size);
            sums[i].start();
        }

        try {

            for (SumCalculation sum : sums) {
                sum.join();

            }

        } catch (InterruptedException e) {
        }

        int total = 0;
        for (SumCalculation sum : sums) {
            total += sum.getPartialSum();
        }

        return total;

    }

}


public class sumParallel {
    public static void main(String[] args)

    {
        // Make an arrayay of 200 million random numbers
        Random rand = new Random();
        int[] array = new int[200000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10) + 1; // random numbers between 1 and 10

        }

        // Call Functions for Calculations and Print Results
        long start = System.currentTimeMillis();
        System.out.println("\nTime in Milliseconds: " + SumCalculation.sum(array));
        System.out.println("Single Thread Sum: " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println("\nTime in Milliseconds: " + SumCalculation.sumParallel(array));
        System.out.println("Parallel Thread Sum: " + (System.currentTimeMillis() - start));

    }

}