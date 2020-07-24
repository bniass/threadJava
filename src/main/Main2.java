package main;

import java.util.concurrent.*;

public class Main2 {
    public  static  void main(String[] args) throws Exception
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime()+"\n");
        ScheduledFuture<?> future = executor.schedule(task, 5, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.SECONDS);
        System.out.printf("Remaining Delay: %sms\n", remainingDelay);
        //----------------------------------------------------------------
        /*
        ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            System.out.println("Scheduling 1: " + System.nanoTime());
        };


        int initialDelay = 0;
        int period = 2;
        executor1.scheduleAtFixedRate(task1, initialDelay, period, TimeUnit.SECONDS);
        */
        //---------------------------------------------------------
        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
        /*
        Runnable task2 = () -> {

            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Scheduling 2: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };
        executor2.scheduleWithFixedDelay(task2, 0, 2, TimeUnit.SECONDS);
        */
        ExecutorService executor5 = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

    }
}
