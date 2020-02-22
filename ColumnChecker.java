/*
    Mason Davis
    CS 311 Fall 2019
    Assignment #4

    Description: ColumnChecker only has one job: given a specific column, add
    up all of the numbers in that column, and place that sum into a shared
    array 
*/

import java.util.Random;
import java.util.*;

public class ColumnChecker extends Thread implements Runnable
{
    private Random sleepTime = new Random();

    private int array2D[][];
    private int columnNumber;
    private int sumArray[];
    private int sum;

    public ColumnChecker(int [][] array2D, int columnNumber, int [] sumArray)
    {
        this.array2D = array2D;
        this.columnNumber = columnNumber;
        this.sumArray = sumArray;
        this.sum  = 0;
    }

    public void run()
    {
        // make thread sleep between 0-5 seconds
        try
        {
            System.out.println("Thread column checker " + columnNumber + " is taking a nap! zzz...");
            Thread.sleep(sleepTime.nextInt(6000));
            System.out.println("Thread column checker " + columnNumber + " snorts and wakes up!");           
        }
        catch(Exception problem)
        {
            System.out.println(problem);
        }
        
        // calculate the specified column
        for(int i = 0; i < array2D.length; i++)
        {
            sum = sum + array2D[i][columnNumber];
        }  

        // add result in correct position in array
        sumArray[(columnNumber * 2) + 1] = sum;

        System.out.println("\nThread column checker " + columnNumber + ": sum is " + sum + "\n");
    }
}