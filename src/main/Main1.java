package main;

public class Main1 {
    public  static  void main(String[] args) throws Exception
    {
        /*
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10){
                    System.out.println("i = "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int j = 0;
                while (j < 10){
                    System.out.println("j = "+j);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    j++;
                }
            }
        });
        thread1.start();
        thread2.start();
        */
        Thread1 t1 = new Thread1();
        t1.start();
        Thread2 t2 = new Thread2();
        t2.start();

        Runnable r = ()->{
            int k = 0;
            while (k < 10){
                System.out.println("k = "+k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                k++;
            }
        };
        Thread t3 = new Thread(r);
        t3.start();

        Thread t4 = new Thread(()-> {
            System.out.println("ffffffffff");
        });
        t4.start();
    }
}
