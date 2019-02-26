package com.thread.tc3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2�����������򡪡� ��һ�����Ľ���������happens-before������������ļ���
 */
public class Demo2 {
	
	private Lock lock = new ReentrantLock();
	
	public void a() {
		lock.lock();
		System.out.println("...");
		lock.unlock(); // 1 ����
	}
	
	public void b() {
		lock.lock(); // 2  ����
		System.out.println("...");
		lock.unlock();
	}

}
