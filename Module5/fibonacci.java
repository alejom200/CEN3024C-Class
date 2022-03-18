
/* 
Class:      Software Development CEN-3024C
Project:    Module 5 Assignment
Program:    Fibonacci Functions
Version:    1.0
Created:    3/12/2022
Updated:    3/18/2022
Author:     Alex Marin <alejandro.marin@lmco.com>

Description:
- Iterative Method to Find Fibonacci Functions.
- Recursive Methinf to Find Fibonacci Functions.
- Start/Stop using long startTime = System.nanoTime();
- Results with time on the Y axis and Input on the X axis.

*/

// Libraries
import java.util.Random;
import java.util.*;
import java.util.Scanner;

// Public Class Main
public class Main
{
   // Function Recursive method to find Fibonacci
   public static int fibonaccir(int n) {
        if(n<=1) {
            return 1;
        }
        else {
            return fibonaccir(n-1)+fibonaccir(n-2);
        }
    }

    // Function Iterative method to find Fibonacci
    public static int fibonaccii(int n) {
        if(n<=1) {
            return 1;
        }

        int r=0,p=1,pp=1,i;
        for(i=2;i<=n;i++) {
            r = p + pp;
            pp =p;
            p =r;
        }
        return r;
    }

    // Entry Area
    public static void main(String[] args) {
        int i,n=40,f1,f2;
        long start_time,end_time;
        int difference,difference2;

        // header print statements
        System.out.print("n\t   Iterative\t            Recursive\n");
        System.out.print("--\t  ---------\t           -----------\n");

         //For loop from 20 to 40
        for(i=20;i<=n;i++) {
            // Start Time in nanoseconds
            start_time  = System.nanoTime();
            // Run Function: Find Fibonacci numbers using Ite rative Method
            f1 = fibonaccii(i);
            // Stop Time in nanoseconds
            end_time  = System.nanoTime();
            // Calculate the difference                    
            difference  = (int)((end_time - start_time) / 1e6);

            // Start Time in nanoseconds
            start_time = System.nanoTime();
            // Run Function: Find Fibonacci using recursive method
            f2 = fibonaccir(i);
            // Stop Time in nanoseconds
            end_time = System.nanoTime();
            // Calculate the difference
            difference2 = (int)((end_time - start_time) / 1e6);
            System.out.print(i+"\t\t"+difference+"\t\t\t"+difference2 +"\t\n");
        }
    }
}