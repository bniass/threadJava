package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


public class ConcurrentThread {
    int count = 0;
    public  void mysunchro() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10)
                .forEach(i ->
                        {
                            executor.submit(this::incrementReentrantLock);
                        }
                );
        stop(executor);

        System.out.println(count);
    }

    ReentrantLock lock = new ReentrantLock();

    void incrementReentrantLock() {
        lock.lock();
        System.out.println("je suis " + Thread.currentThread().getName() + " en attente");

        System.out.println("je suis " + Thread.currentThread().getName() + " i am in");
        try {
            TimeUnit.SECONDS.sleep(1);
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("je suis " + Thread.currentThread().getName() + " i am going to exit");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        lock.unlock();
    }
    public void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }
}
