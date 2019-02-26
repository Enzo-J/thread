package com.thread.t2;

/**
 * 匿名内部类的线程创建模式，只执行一次
 */
public class Demo3 {
	
	public static void main(String[] args) {
		
		/*new Thread() {
			public void run() {
				System.out.println("thread start ..");
			};
		}.start();*/
		
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread start ..");
			}
		}).start();*/

        /**
         * 下述的这种方式，是将thread以及runnable两种方式结合在一起了
         */
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable");
			}
		}) {
			public void run() {
				System.out.println("sub");
			}
		}.start();
		
		//运行的结果为sub，这个是为什么
        /**
         * 原因：子类的run（）方法覆盖了父类的run（）方法，通过排除，可以知道，runnable是不会被打印的
         * 然而，运行的还是thread子类的run方法——因为有子类对父类及逆行重写了，所以不管怎么调用原来的构造
         * 方法，一定不会执行。（jvm的模式）
         */
	}

}
