package _coursework.Question6;

/*
 * Question 6 (A)
a)
You are given a class NumberPrinter with three methods: printZero, printEven, and printOdd.
These methods are designed to print the numbers 0, even numbers, and odd numbers, respectively.
Task:
Create a ThreadController class that coordinates three threads:
5. ZeroThread: Calls printZero to print 0s.
6. EvenThread: Calls printEven to print even numbers.
7. OddThread: Calls printOdd to print odd numbers.
These threads should work together to print the sequence "0102030405..." up to a specified number n.
The output should be interleaved, ensuring that the numbers are printed in the correct order.
Example:
If n = 5, the output should be "0102030405".
Constraints:
 The threads should be synchronized to prevent race conditions and ensure correct output.
 The NumberPrinter class is already provided and cannot be modified.
 */

class NumberPrinter {
    public void printZero() {
        System.out.print("0");
    }
    public void printEven(int number) {
        System.out.print(number);
    }
    public void printOdd(int number) {
        System.out.print(number);
    }
}

public class A_ThreadController {
    private final int n;
    private int currentNumber = 1;
    // turn: 0 = ZeroThread, 1 = OddThread, 2 = EvenThread.
    private int turn = 0;
    private final NumberPrinter printer;
    private final Object lock = new Object();

    public A_ThreadController(int n, NumberPrinter printer) {
        this.n = n;
        this.printer = printer;
    }

    // Thread that prints zero.
    class ZeroThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // Wait until it's ZeroThread's turn or we're done.
                    while (turn != 0 && currentNumber <= n) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (currentNumber > n) {
                        lock.notifyAll();
                        break;
                    }
                    printer.printZero();
                    // Decide which thread should run next.
                    if (currentNumber % 2 == 1) {
                        turn = 1; // odd thread's turn.
                    } else {
                        turn = 2; // even thread's turn.
                    }
                    lock.notifyAll();
                }
            }
        }
    }

    // Thread that prints odd numbers.
    class OddThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // Wait until it's odd thread's turn.
                    while (turn != 1 && currentNumber <= n) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (currentNumber > n) {
                        lock.notifyAll();
                        break;
                    }
                    printer.printOdd(currentNumber);
                    currentNumber++;
                    turn = 0; // Return turn to zero thread.
                    lock.notifyAll();
                }
            }
        }
    }

    // Thread that prints even numbers.
    class EvenThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // Wait until it's even thread's turn.
                    while (turn != 2 && currentNumber <= n) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (currentNumber > n) {
                        lock.notifyAll();
                        break;
                    }
                    printer.printEven(currentNumber);
                    currentNumber++;
                    turn = 0; // Return turn to zero thread.
                    lock.notifyAll();
                }
            }
        }
    }

    // Starts all threads.
    public void startThreads() {
        new ZeroThread().start();
        new OddThread().start();
        new EvenThread().start();
    }

    // Main method to run the controller.
    public static void main(String[] args) {
        int n = 5; // Example: print sequence up to n (output: 0102030405)
        NumberPrinter printer = new NumberPrinter(); // Provided class (stub here)
        ThreadController controller = new ThreadController(n, printer);
        controller.startThreads();
    }
}