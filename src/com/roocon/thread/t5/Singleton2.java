package com.roocon.thread.t5;

public class Singleton2 {
	
	private Singleton2() {}

	/**
	 *  1：这里的volatile，解决了synchronized下创建对象时可能出现的指令重排序问题（增加了可视性，避免了指令的重排序）
     *  2：增加了可视性：虽然线程0先进的第一个if，但创建对象的确实线程1，而此时，线程0已经判断了instance==null,所以他还会再创建一个对象，
     *                因为并没有人告诉线程0，线程1已经创建过对象了，而内部的这个if，就是为了告诉别人，对象我已经创建过了！
     *                这里的volatile，就是来告诉另一个线程，对象已经创建了，另一个线程在进行里层的if判断你的时候，就能够做出正确的判断。
	 */
	private static volatile Singleton2 instance;
	
	/**
	 * 双重检查加锁
	 * ——延迟加载的思想（懒加载）
	 * @return
	 */
	public static Singleton2 getInstance () {
		// 自旋   while(true)
		if(instance == null) {
			synchronized (Singleton2.class) {
				if(instance == null) {
					//这里为什么还要再次进行做判空操作
                    /**
                     * 虽然线程0先进的第一个if，但创建对象的确实线程1，而此时，线程0已经判断了instance==null,所以他还会再创建一个对象，
                     * 因为并没有人告诉线程0，线程1已经创建过对象了，而内部的这个if，就是为了告诉别人，对象我已经创建过了！
                     * 链接：https://www.cnblogs.com/Geodon/p/3798371.html
                     */
					instance = new Singleton2();  // 指令重排序
					
					// 申请一块内存空间   // 1
					// 在这块空间里实例化对象  // 2
					// instance的引用指向这块空间地址   // 3
				}
			}
		}
		return instance;
	}
	
	// 多线程的环境下
	// 必须有共享资源
	// 对资源进行非原子性操作
    /**
     * public static synchronized Singleton getInstance(){    //对获取实例的方法进行同步
     *        if (instance == null)
     *          instance = new Singleton();
     *        return instance;
     * }
     * 这个锁的粒度有点大
     */
}
