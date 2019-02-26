package com.thread.t2;

/**
 * https://www.cnblogs.com/jenkov/p/juc_interrupt.html
 */
public class InterruptionInJava2 implements Runnable{
    private volatile static boolean on = false;
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava2(),"InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava2.on = true;
        testThread.interrupt();

        System.out.println("main end");

    }

    @Override
    public void run() {
        while(!on){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("caught exception right now: "+e);
            }
        }
    }
}
