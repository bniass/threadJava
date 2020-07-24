package main;

public class Thread2 extends Thread{

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
}
