package com.thread.ta1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 公平锁是严格的以FIFO的方式进行锁的竞争，但是非公平锁是无序的锁竞争，刚释放锁的线程很大程度上能比较快的获取到锁，
 * 队列中的线程只能等待，所以非公平锁可能会有“饥饿”的问题。但是重复的锁获取能减小线程之间的切换，而公平锁则是严格的线程切换，
 * 这样对操作系统的影响是比较大的，所以非公平锁的吞吐量是大于公平锁的，这也是为什么JDK将非公平锁作为默认的实现。
 *
 */

public class Test {
	
	Lock lock = new ReentrantLock(false);
	
	
	public void a () {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "   a");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public static void main(String[] args) {
		
		Test t = new Test();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				t.a();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				t.a();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				t.a();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				t.a();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				t.a();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				t.a();
			}
		}).start();
		
		
		
	}

}


