import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedCounter {
    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println("Counter: " + counter);
        } finally {
            lock.unlock();
        }
    }

    // Getter method to access the counter value
    public int getCounter() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }
}

class IncrementingThread extends Thread {
    private final SharedCounter sharedCounter;

    public IncrementingThread(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedCounter.increment();
            try {
                Thread.sleep(100); // Sleep for demonstration
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class SharedMemoryCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for name and roll number
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your roll number: ");
        String rollNumber = scanner.nextLine();

        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);

        SharedCounter sharedCounter = new SharedCounter();

        // Create multiple threads to increment the counter
        Thread thread1 = new IncrementingThread(sharedCounter);
        Thread thread2 = new IncrementingThread(sharedCounter);
        Thread thread3 = new IncrementingThread(sharedCounter);

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Counter Value: " + sharedCounter.getCounter());
    }
}
