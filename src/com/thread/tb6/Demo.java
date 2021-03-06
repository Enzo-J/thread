package com.thread.tb6;

import java.util.concurrent.Semaphore;

/**
 * *Semaphore的用法：
 *  在很多情况下，可能有多个线程需要访问数目很少的资源。假想在服务器上运行着若干个回答客户端请求的线程。这些线程需要连接到同一数据库，但任一时刻
 *  只能获得一定数目的数据库连接。你要怎样才能够有效地将这些固定数目的数据库连接分配给大量的线程？
 *   答：1.给方法加同步锁，保证同一时刻只能有一个人去调用此方法，其他所有线程排队等待，但是此种情况下即使你的数据库链接有10个，也始终只有一个处于使
 *      用状态。这样将会大大的浪费系统资源，而且系统的运行效率非常的低下。
 *      2.另外一种方法当然是使用信号量，通过信号量许可与数据库可用连接数相同的数目，将大大的提高效率和性能。
 *
 * Semaphore有两种模式，公平模式和非公平模式。公平模式就是调用acquire的顺序就是获取许可证的顺序，遵循FIFO；
 * 而非公平模式是抢占式的，也就是有可能一个新的获取线程恰好在一个许可证释放时得到了这个许可证，而前面还有等待的线程。
 *
 * FairSync与NonFairSync的区别就在于会首先判断当前队列中有没有线程在等待，如果有，就老老实实进入到等待队列；
 * 而不像NonfairSync一样首先试一把，说不定就恰好获得了一个许可，这样就可以插队了。
 *
 * 其实，这个例子，写得不是很好，看不出Semaphore的真实用法。
 * 原理：
 * 以一个停车场是运作为例。为了简单起见，假设停车场只有三个车位，一开始三个车位都是空的。这时如果同时来了五辆车，看门人允许其中三辆不受阻碍的进入，然后放下车拦，
 * 剩下的车则必须在入口等待，此后来的车也都不得不在入口处等待。这时，有一辆车离开停车场，看门人得知后，打开车拦，放入一辆，如果又离开两辆，则又可以放入两辆，
 * 如此往复。这个停车系统中，每辆车就好比一个线程，看门人就好比一个信号量，看门人限制了可以活动的线程。假如里面依然是三个车位，但是看门人改变了规则，
 * 要求每次只能停两辆车，那么一开始进入两辆车，后面得等到有车离开才能有车进入，但是得保证最多停两辆车。对于Semaphore类而言，就如同一个看门人，限制了可活动的线程数。
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
