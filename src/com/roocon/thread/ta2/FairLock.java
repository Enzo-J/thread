package com.roocon.thread.ta2;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己实现的公平锁   不可重入
 * 提供一个队列，里面存放一个个对象，且每个对象都有自身的wait、notify方法。在每次进入队列的时候，调用wait方法，出队列的时候，调用该对象的notify方法，精准
 * 唤醒该对象，实现该效果。
 */


public class FairLock {
	private boolean isLocked = false;
	private Thread lockingThread = null;
	private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

	public void lock() throws InterruptedException {
		QueueObject queueObject = new QueueObject();
		boolean isLockedForThisThread =true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
        //可重入
		while (isLockedForThisThread){
		    synchronized (this){
		        isLockedForThisThread=isLocked|| waitingThreads.get(0) != queueObject;
		        if(!isLockedForThisThread){
		            isLocked=true;
		            waitingThreads.remove(queueObject);
		            lockingThread=Thread.currentThread();
		            return;
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}
}