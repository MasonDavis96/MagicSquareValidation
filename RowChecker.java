/*
    Mason Davis
    CS 311 Fall 2019
    Assignment #4

    Description: RowChecker only has one job: given a specific row, add up all
    of the numbers in that row, and place that sum into a shared array
*/

import java.util.Random;
import java.util.*;

public class RowChecker extends Thread implements Runnable
{
    private Random sleepTime = new Random();

    private int array2D[][];
    private int rowNumber;
    private int sumArray[];
    private int sum;

    public RowChecker(int [][] array2D, int rowNumber, int [] sumArray)
    {
        this.array2D = array2D;
        this.rowNumber = rowNumber;
        this.sumArray = sumArray;
        this.sum  = 0;
    }

    public void run()
    {
        // make thread sleep between 0-5 seconds
        try
        {
            System.out.println("Thread row checker " + rowNumber + " is taking a nap! zzz...");
            Thread.sleep(sleepTime.nextInt(6000));
            System.out.println("Thread row checker " + rowNumber + " snorts and wakes up!");           
        }
        catch(Exception problem)
        {
            System.out.println(problem);
        }

        // calculate the specified row
        for(int i = 0; i < array2D.length; i++)
        {
            sum = sum + array2D[rowNumber][i];
        }  
        
        // add result in correct position in array
        sumArray[rowNumber * 2] = sum;

        System.out.println("\nThread row checker " + rowNumber + ": sum is " + sum + "\n");
    }
}