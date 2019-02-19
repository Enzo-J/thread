package com.roocon.thread.td3;

/**
 * << 带符号左移
 * >>> 无符号右移
 *
 * 1= 或操作
 *  99 ==> 01100011
 *  99>>>1 00110001
 *  |=01110011 ==>115
 */
public class Demo {
	/**
	 * 1<<30
	 */
	private static final int MAXIMUM_CAPACITY = 1 << 30;
	private static final int tableSizeFor(int c) {  
	    int n = c - 1;  
	    n |= n >>> 1;  
	    n |= n >>> 2;  
	    n |= n >>> 4;  
	    n |= n >>> 8;  
	    n |= n >>> 16;  
	    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;  
	} 
	
	
public static void main(String[] args) {
	
	System.out.println(tableSizeFor(100));
	
}
}
