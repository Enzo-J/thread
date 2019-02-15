package com.roocon.thread.tb3;

/**
 * 线程本地变量
 *
 * 　1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
 *
 * 　2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像上面代码中的longLocal和stringLocal；
 *
 * 　3）在进行get之前，必须先set，否则会报空指针异常；
 *
 * 　　 如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。
 *
 *
 *   https://www.cnblogs.com/dolphin0520/p/3920407.html
 *
 *   https://www.jianshu.com/p/98b68c97df9b
 *
 *   这里涉及到一个内存泄露的概念——
 *   https://juejin.im/post/5c6128c96fb9a049f36290ed?utm_source=gold_browser_extension
 *
 */

public class Demo {

	private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return new Integer(0);
		};
	};

	public int getNext() {
		Integer value = count.get();
		value++;
		count.set(value);
		return value;
	}

	public static void main(String[] args) {
		Demo d = new Demo();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " " + d.getNext());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " " + d.getNext());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
