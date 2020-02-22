# MagicSquareValidation
A Java program that utilizes multiple threads to validate a magic square

This program, written in java, uses threads to validate each row, column, and diagonal of a geometrical square of numbers that could represent a magic square. It is implemented in a way to clearly display the workings of each thread as it executes, but to also provide a foundation on which to further learn about the mechanics of threads and how to involve them in a program. Each Thread is given a single row/column/diagonal to validate depending on how large the square is. At the end of validation, the program will determine whether the square is a magic square - or not.

to compile, simply use the command javac *.java
to run the program: java MagicSquare square.txt
