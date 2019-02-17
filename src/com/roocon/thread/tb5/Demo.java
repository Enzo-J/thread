package com.roocon.thread.tb5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier和CountDownLatch的区别：
 *
 * 1.CountDownLatch是线程组之间的等待，即一个(或多个)线程等待N个线程完成某件事情之后再执行；而CyclicBarrier则是线程组内的等待，
 *   即每个线程相互等待，即N个线程都被拦截之后，然后依次执行。
 * 3.CountDownLatch计数为0无法重置，而CyclicBarrier计数达到初始值，则可以重置。
 * 4.CountDownLatch不可以复用，而CyclicBarrier可以复用。
 * 5：CountDownLatch不同的是所有的线程必须同时被唤醒，就好比钓鱼比赛，所有人必须同时开始抛竿一样。
 *    CountDownLatch只要求主线程的动作在其他依赖的线程执行完之后执行就OK
 *
 * CyclicBarrier的使用方式：
 *每个线程使用await()方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
 * 线程都运行await()方法之后都到达了栅栏位置，然后，工作线程才开始执行业务处理。
 * 如果一个线程处于等待状态时，如果其他线程调用reset()，或者调用的barrier原本就是被损坏的，则抛出BrokenBarrierException异常。
 * 同时，任何线程在等待时被中断了，则其他所有线程都将抛出BrokenBarrierException异常，并将barrier置于损坏状态。
 *
 * http://www.importnew.com/21889.html
 * CyclicBarrier的中断和重置问题：
 * https://blog.csdn.net/hanchao5272/article/details/79779639（这个讲得比较全）
 *
 */
public class Demo {

	Random random = new Random();
	public void meeting(CyclicBarrier barrier) {
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会..");

		if(Thread.currentThread().getName().equals("Thread-7")) {
			/**
			 * 这里的7号线程中断，其他线程抛出BrokenBarrierException异常，并停止等待，继续执行。
 			 */
//			Thread.currentThread().interrupt();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			barrier.reset();

		}
		
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Demo demo = new Demo();

		CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
			@Override
			public void run() {
				System.out.println("好！我们开始开会...");
			}
		});

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.meeting(barrier);
				}
			}).start();
		}
		
		// 监控等待线程数
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("等待的线程数 " + barrier.getNumberWaiting());
					System.out.println("is broken " + barrier.isBroken());
				}
			}
		}).start();
	}

}
