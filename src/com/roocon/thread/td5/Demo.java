package com.roocon.thread.td5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * new thread的弊端：
 * a. 每次new Thread新建对象性能差。
 * b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
 * c. 缺乏更多功能，如定时执行、定期执行、线程中断
 *
 * 线程池的好处在于：
 * a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
 * b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
 * c. 提供定时执行（newScheduledThreadPool(10)）、定期执行（newScheduledThreadPool(10)）、单线程（newSingleThreadExecutor()）、并发数控制（newFixedThreadPool()）等功能。
 * d. newCachedThreadPool大家一般不用，容易出OOM，最常用的，还是newFixedThreadPool()。https://blog.csdn.net/wenniuwuren/article/details/51700080
 *
 * ExecutorService的若干种实例化例子：
 * newSingleThreadExecutor：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool：创建一个定长线程池，支持定时及周期性任务执行。
 *
 *
 * newCachedThreadPool——这种类型的线程池特点是：
 * 工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE),?这样可灵活的往线程池中添加线程。
 * 如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
 * 在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。
 */

public class Demo {
	
	public static void main(String[] args) {
		
		
		// 10个线程来处理大量的任务
//		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		ExecutorService pool = Executors.newFixedThreadPool(10);
//		ExecutorService pool = Executors.newCachedThreadPool();
//		ExecutorService pool = Executors.newSingleThreadExecutor();
//		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
//		ExecutorService pool = Executors.newWorkStealingPool();

		while(true) {
			
			Future<?> f = pool.submit(new Runnable() {
				
				@Override
				public void run() {
					
				}
			});
			
			
//			pool.schedule(new Runnable() {
//				
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName());
//				}
//			}, 5, TimeUnit.SECONDS);
			
			
//			pool.execute(new Runnable() {
//				
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
		}
		
	}

}
