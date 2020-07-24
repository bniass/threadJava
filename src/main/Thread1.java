package main;

public class Thread1 extends Thread{

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
}
