# MagicSquareValidation
A Java program that utilizes multiple threads to validate a magic square<br>

This program, written in java, uses threads to validate each row, column, and diagonal of a geometrical square of numbers that could represent a magic square. It is implemented in a way to clearly display the workings of each thread as it executes, but to also provide a foundation on which to further learn about the mechanics of threads and how to involve them in a program. Each Thread is given a single row/column/diagonal to validate depending on how large the square is. At the end of validation, the program will determine whether the square is a magic square - or not.<br>

This project was a task given to me to complete in my Operating Systems class, to introduce the concepts of threads, and to try and understand how they work in a simple setting such as this. Overall, It only took me a few days of working on it until it was completed out of the standard ~2 week deadline given for assignments. Becuase of the narrow scope of this project, I was better able to learn the working of threads (at least on a foundational level) and to see in person their effects on a program. I learned how there is no order that can be determined when threads run, and that they can be swapped out at any time - and so the programmer must be aware of this when trying to execute a program and that they cannot rely on a certain thread of execution to finish before another.<br>

to compile, simply use the command: `javac *.java`<br>
to run the program: `java MagicSquare square.txt`
