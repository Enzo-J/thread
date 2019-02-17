package com.roocon.thread.tb6;

import java.util.concurrent.Semaphore;

/**
 * Semaphore的用法：
 *     在很多情况下，可能有多个线程需要访问数目很少的资源。假想在服务器上运行着若干个回答客户端请求的线程。这些线程需要连接到同一数据库，但任一时刻
 *     只能获得一定数目的数据库连接。你要怎样才能够有效地将这些固定数目的数据库连接分配给大量的线程？
 *     答：1.给方法加同步锁，保证同一时刻只能有一个人去调用此方法，其他所有线程排队等待，但是此种情况下即使你的数据库链接有10个，也始终只有一个处于使
 *           用状态。这样将会大大的浪费系统资源，而且系统的运行效率非常的低下。
 *        2.另外一种方法当然是使用信号量，通过信号量许可与数据库可用连接数相同的数目，将大大的提高效率和性能。
 */
public class Demo {
	
	public void method (Semaphore semaphore) {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is run ...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		semaphore.release();
	}
	
	
	public static void main(String[] args) {
		
		Demo d = new Demo();
		Semaphore semaphore = new Semaphore(10);
		
		while(true) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					d.method(semaphore);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		
	}

}
