/*
    Mason Davis
    CS 311 Fall 2019
    Assignment #4

    Description: DiagonalChecker only has one job: given a specific diagonal,
    add up all of the numbers in that diagonal, and place that sum into a
    shared array 
*/

import java.util.Random;
import java.util.*;

public class DiagonalChecker extends Thread implements Runnable
{
    private Random sleepTime = new Random();

    private int array2D[][];
    private int diagonalNumber;
    private int sumArray[];
    private int sum;

    public DiagonalChecker(int [][] array2D, int diagonalNumber, int [] sumArray)
    {
        this.array2D = array2D;
        this.diagonalNumber = diagonalNumber;
        this.sumArray = sumArray;
        this.sum  = 0;
    }

    public void run()
    {
        // make thread sleep between 0-5 seconds
        try
        {
            System.out.println("Thread diagonal checker " + diagonalNumber + " is taking a nap! zzz...");
            Thread.sleep(sleepTime.nextInt(6000));
            System.out.println("Thread diagonal checker " + diagonalNumber + " snorts and wakes up!");           
        }
        catch(Exception problem)
        {
            System.out.println(problem);
        }
        
        // calculate the specified column
        int decrement = array2D.length - 1;
        for(int i = 0; i < array2D.length; i++)
        {
            if(diagonalNumber == 1) // up-right diagonal
            {
                sum = sum + array2D[decrement][i];
                decrement--;               
            }
            else // up-left diagonal
            {
                sum = sum + array2D[i][i];
            }
        }        

        // place result into last two positions in array
        if(diagonalNumber == 1)
            sumArray[sumArray.length - 2] = sum;
        else
            sumArray[sumArray.length - 1] = sum;

        System.out.println("\nThread diagonal checker " + diagonalNumber + ": sum is " + sum + "\n");
    }
}