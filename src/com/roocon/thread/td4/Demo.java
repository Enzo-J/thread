package com.roocon.thread.td4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.TimeUnit;

/**
 * 线程池——
 * https://blog.csdn.net/holmofy/article/details/77411854
 * https://blog.csdn.net/holmofy/article/details/81271839
 */
public class Demo {
	
	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 50, 10, TimeUnit.DAYS, new ArrayBlockingQueue<>(10),new ThreadPoolExecutor.CallerRunsPolicy());
		AtomicInteger count = new AtomicInteger();
		for(int i = 0; i < 100 ;i ++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					count.getAndIncrement();
				}
			});
		}
		
		threadPool.shutdown();
		
		
		while(Thread.activeCount() > 1) {
			
		}
		System.out.println(count.get());
	}

}
