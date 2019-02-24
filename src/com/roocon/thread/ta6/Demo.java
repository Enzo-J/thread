package com.roocon.thread.ta6;

import com.roocon.thread.t5.Singleton2;

/**
 * 这种写法和wait notify的区别是什么
 * ——这样写的话，在sleep的时候，会出现不及时，不同步的情况，而wait和notify不会出现这种情况。
 */

public class Demo {
	
	private volatile int signal;
	
	public void set (int value) {
		this.signal = value;
	}
	
	public int get () {
		return signal;
	}
	
	public static void main(String[] args) {
		Demo d = new Demo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("修改状态的线程执行...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.set(1);
				System.out.println("状态值修改成功。。。");
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// 等待signal为1开始执行，否则不能执行
				/**
				 * 采用自旋方式进行判定
				 */
				while(d.get() != 1) {
					try {
						Thread.sleep(1500);  //休眠不占用CPU资源
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// 当信号为1 的时候，执行代码
				System.out.println("模拟代码的执行...");
				
				
			}
		}).start();
	}

}
