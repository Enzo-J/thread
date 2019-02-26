package com.thread.ta7;

public class Tmall {
	
	private int count;
	
	public final int MAX_COUNT = 10;
	
	public synchronized void push () {
        /**
         * 为什么不能使用if
         * 因为当满的时候，线程1 push方法执行wait()后进入对象等待池，并且释放对象锁，此时如果又有个线程2得到锁进入push方法 也执行wait()方法，
         * 进入对象的等待池并且释放锁；线程3得到锁后执行take方法，使得满不成立，并且执行notifyAll()，将线程1，2从等待池中放入锁池等锁，
         * 有可能线程1得到锁后继续执行push方法，这样生产了一个元素，而当线程1执行完后，恰恰又是线程2得到锁，他得到锁后也继续执行push方法，
         * 也生产了一个元素，这样就超出了容量，如果是while的话，再让他判断一次的话，线程2不满足条件，继续进入对待池 这就是用while而不使用if的原因。
         * 下面同理。
		 *
		 * 简单版——当有5个生产者线程处于wait状态的时候，如果用的是if，那么当count<MAX_COUNT的时候，就缺乏判断，5个线程一股脑的进行count++操作。
         * 为了避免这一情况，改用while。
         */
        while(count >= MAX_COUNT) {
			try {
				System.out.println(Thread.currentThread().getName() + " 库存数量达到上限，生产者停止生产。");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count ++;
		System.out.println(Thread.currentThread().getName() + " 生产者生产，当前库存为：" + count);
		notifyAll();
	}
	
	public synchronized void take () {
        /**
         * 通过添加下述的打印语句，可以分析，wait方法之后，执行的是wait之后的代码，而不是重头开始重新执行。
         */
//		System.out.println("开始消费");
		while(count <= 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " 库存数量为零，消费者等待。");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count --;
		System.out.println(Thread.currentThread().getName() + " 消费者消费，当前库存为：" + count);
		notifyAll();
	}

}
