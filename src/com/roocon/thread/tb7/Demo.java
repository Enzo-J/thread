package com.roocon.thread.tb7;

import java.util.concurrent.Exchanger;

/**
 * Exchanger原理：
 * 当一个线程到达exchange调用点时，如果它的伙伴线程此前已经调用了此方法，那么它的伙伴会被调度唤醒并与之进行对象交换，然后各自返回。
 * 如果它的伙伴还没到达交换点，那么当前线程将会被挂起，直至伙伴线程到达——完成交换正常返回；或者当前线程被中断——抛出中断异常；又或者是等候超时——抛出超时异常。
 * 此类提供对外的操作是同步的；
 * 用于成对出现的线程之间交换数据；
 * 可以视作双向的同步队列；
 * 可应用于基因算法、流水线设计等场景。
 * Exchanger 是一种无锁算法，和前面SynchronousQueue 一样，都是通过循环 cas 来实现线程安全，因此这种方式就会显得比较抽象和麻烦。
 *
 * https://www.cnblogs.com/davidwang456/p/4179488.html
 * 交换数据这个在生产/消费之间的使用，也是挺不错的。
 *
 * https://blog.csdn.net/u014634338/article/details/78385521  --源码分析，目前没看懂
 */

public class Demo {
	
	public void a (Exchanger<String> exch) {
		
		System.out.println("a 方法执行...");
		
		try {
			System.out.println("a 线程正在抓取数据...");
			Thread.sleep(2000);
			System.out.println("a 线程抓取到数据...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String res = "12345";
		
		try {
			System.out.println("a 等待对比结果...");
			exch.exchange(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void b (Exchanger<String> exch) {
		System.out.println("b 方法开始执行...");
		try {
			System.out.println("b 方法开始抓取数据...");
			Thread.sleep(4000);
			System.out.println("b 方法抓取数据结束...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = "12345";
		
		try {
			String value = exch.exchange(res); //这里拿的是a线程传递过来的值
			System.out.println("开始进行比对...");
			System.out.println("比对结果为：" + value.equals(res));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Demo d = new Demo();
		Exchanger<String> exch = new Exchanger<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.a(exch);
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.b(exch);
			}
		}).start();
		
	}

}
