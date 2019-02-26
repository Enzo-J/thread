package com.thread.tc3;

/**
 * 程序顺序规则——
 * 单个线程中的每个操作，总是前一个操作happens-before于该线程中的任意后续操作
 */
public class Demo {
	
	private int a;
	private int b;
	private int c;
	
	/**
	 * 1 happens-before 2
	 * 2 happens-before 3
	 * 3 happens-before 4
	 * ...
	 */
	public void a () {
		a = 2;  // 1
		b = 10; // 2
		
		c = a + b; // 3
		
		System.out.println(c);  // 4
	}
	
	public static void main(String[] args) {
		new Demo().a();
	}

}
