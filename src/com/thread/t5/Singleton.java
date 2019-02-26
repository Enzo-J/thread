package com.thread.t5;

/**
 * 饿汉式，不存在线程安全性问题
 */
public class Singleton {
	
	// 私有化构造方法
	private Singleton () {}

	private static Singleton instance = new Singleton();  //这里只创建了一次对象，所以后续不管调用了几次getInstance(),都是同一个对象。
	
	public static  Singleton getInstance() {
		return instance;
	}
	
	// 多线程的环境下
	// 必须有共享资源 instance
	// 对资源进行非原子性操作   仅仅是返回对象，是原子性操作

	/**
	 * 上述代码中的一个缺点是该类加载的时候就会直接new 一个静态对象出来，当系统中这样的类较多时，会使得启动速度变慢 。
     *  这种适合在小系统。
	 */
}
