package com.thread.t2;

public class Demo1 extends Thread {
	/**
	 * 这里重写了构造方法，要不原来调用的是Thread()构造函数。
	 * @param name
	 */
	public Demo1(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(!interrupted()) {
			System.out.println(getName() + "线程执行了 .. ");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Demo1 d1 = new Demo1("first-thread");
		Demo1 d2 = new Demo1("second-thread");
		
		
		d1.start();
		d2.start();
		
//		d1.stop();
        /**
         * 单单使用interrupt是无法实现禁止d1线程运行的，因为其不会禁止正在运行的线程，所以，这里需要配合!interrupted()进行操作。
         */
		d1.interrupt();
	}
	
}
