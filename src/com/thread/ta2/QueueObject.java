package com.thread.ta2;

public class QueueObject {

	private boolean isNotified = false;

	public synchronized void doWait() throws InterruptedException {
		//自旋
		while (!isNotified) {
			this.wait();
		}

		this.isNotified = false;

	}

	public synchronized void doNotify() {
		this.isNotified = true;
		this.notify();
	}

	public boolean equals(Object o) {
		return this == o;
	}

}
