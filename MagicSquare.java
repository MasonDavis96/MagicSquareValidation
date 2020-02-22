/*
    Mason Davis
    CS 311 Fall 2019
    Assignment #4

    Description: This program validates from a text file whether a group of
    numbers is a magic square (a two-dimensional array of integers where the
    numbers in each row, column, and the two main diagonals all add up to the
    same value). It creates an array of threads, which all individually go and
    calculate the sums of the rows/columns/and diagonals. It then validates
    whether the sums are all the same, and prints the result to the terminal.
*/

import java.io.*;
import java.util.*;

public class MagicSquare
{
    static int arraySize;

    public static void main (String args []) throws IOException, InterruptedException
    {
        // check if the command line arguments are correct
        if(args.length != 1)
        {
            System.out.println("Usage: program.java textFile.txt");
            System.exit(0);
        }

        String fileName = args[0];
        File file = new File(fileName);

        // if file does not exist - exit
        if(!file.exists())
        {
            System.out.println("File does not exist! Exiting.");
            System.exit(0);
        }

        // read through file
        Scanner scan = new Scanner(file);

        arraySize = scan.nextInt();
        int array2D[][] = new int[arraySize][arraySize];
        int sumArray[] = new int[(arraySize * 2) + 2];

        // populate 2D array with file data
        for(int i = 0; i < arraySize; i++)
        {
            for(int j = 0; j < arraySize; j++)
            {
                array2D[i][j] = scan.nextInt();
            }
        }

        System.out.println("\n~*~*~* MAGIC SQUARE VALIDATION PROGRAM *~*~*~\n");
        
        System.out.println("Is this a Magic Square?");

        printSquare(array2D, arraySize);

        System.out.println("\nLet's find out!\n");

        // create array of row/column checkers to check sums
        Thread rowThreads[] = new Thread[arraySize];
        Thread columnThreads[] = new Thread[arraySize];

        // set up arrays with appropriate "checkers" and start running
        for(int i = 0; i < arraySize; i++)
        {
            rowThreads[i] = new RowChecker(array2D, i, sumArray);
            columnThreads[i] = new ColumnChecker(array2D, i, sumArray);

            rowThreads[i].start();
            columnThreads[i].start();
        }

        // set up/start two threads for the diagonals
        Thread diagonalThread_0 = new DiagonalChecker(array2D, 0, sumArray);
        Thread diagonalThread_1 = new DiagonalChecker(array2D, 1, sumArray);

        diagonalThread_0.start();
        diagonalThread_1.start();

        // wait for rows to finish calculating before continuing
        for(int i = 0; i < arraySize; i++)
        {
            rowThreads[i].join();
        }

        // wait for columns to finish calculating before continuing
        for(int i = 0; i < arraySize; i++)
        {
            columnThreads[i].join();
        }

        // wait for diagonals to finish calculating before continuing
        diagonalThread_0.join();
        diagonalThread_1.join();

        // print out summary of calculations
        System.out.println("\n=== All threads completed ===\n");

        System.out.println("Summary");
        System.out.println("----------------------");
        System.out.println("#\tRow\tColumn");
        System.out.println("----------------------");

        // print out rows/columns summary
        int tracker = 0;
        for(int i = 0; i < arraySize; i++)
        {
            System.out.print(i + "\t");
            System.out.print(sumArray[tracker] + "\t");
            tracker++;
            System.out.print(sumArray[tracker] + "\n");
            tracker++;
        }

        System.out.println("\nDiagonals");
        System.out.println("---------");

        // print out diagonals summary
        for(int i = 0; i < 2; i++)
        {
            System.out.println(i + "\t" + sumArray[tracker]);
            tracker++;
        }

        System.out.println("\n~*~*~* Result *~*~*~\n");

        printSquare(array2D, arraySize);        

        // determine if the square is a magic square
        int temp = sumArray[0];
        boolean isMagic = true;
        for(int i = 0; i < sumArray.length; i++)
        {
            if(sumArray[i] == temp)
                continue;
            else
            {
                isMagic = false;
                break; 
            }
        }

        if(isMagic == true)
        {
            System.out.println("\nIS a magic square!");
            System.out.println("magic sum = " + temp + "\n");
        }
        else
            System.out.println("\nIs NOT a magic square.");
    }

    // print the square. returns nothing, takes the array to print, and its
    // size
    static void printSquare(int [][] array2D, int arraySize)
    {
        for(int i = 0; i < arraySize; i++)
        {
            for(int j = 0; j < arraySize; j++)
            {
                System.out.print(array2D[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}