package com.thread.t2;

public class InterruptionInJava implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava(),"InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        //interrupt thread
        testThread.interrupt();

        System.out.println("main end");

    }

    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Yes,I am interruted,but I am still running");

            }else{
                System.out.println("not yet interrupted");
            }
        }
    }

    /**
     * 这样写是可以中断的
     */
//    @Override
//    public void run() {
//        while(!interrupted()){
//                System.out.println("not yet interrupted");
//        }
//    }
}
