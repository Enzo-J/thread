package com.thread.t6;

/**
 * 锁重入例子
 */
public class Demo {
	/**
	 * 这里的两个方法，锁的是当前类的实例
	 */
	
	public synchronized void a () {
		System.out.println("a");
//		b();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void b() {
		System.out.println("b");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Demo d1= new Demo();
		Demo d2= new Demo();
        /**
         * （换另一个可能会明显一些，一个线程且a方法中调用b方法）
         */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d1.a();
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
			    d1.b();
                /**
                 * 这个是锁不住的——不同的对象
                 */
//				d2.b();
			}
		}).start();
	}

}
