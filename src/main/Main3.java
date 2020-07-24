package main;

import java.util.concurrent.*;

public class Main3 {
    public  static  void main(String[] args) throws Exception
    {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        ExecutorService executor1 = Executors.newFixedThreadPool(10);
        Future<Integer> future = executor1.submit(task);
        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
        try {
            System.out.println("attempt to shutdown executor");
            executor1.shutdown();
            executor1.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor1.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor1.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
