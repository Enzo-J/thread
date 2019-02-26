package com.thread.tc3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2：监视器规则—— 对一个锁的解锁，总是happens-before于随后对这个锁的加锁
 */
public class Demo2 {
	
	private Lock lock = new ReentrantLock();
	
	public void a() {
		lock.lock();
		System.out.println("...");
		lock.unlock(); // 1 解锁
	}
	
	public void b() {
		lock.lock(); // 2  加锁
		System.out.println("...");
		lock.unlock();
	}

}
