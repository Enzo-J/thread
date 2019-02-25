package com.roocon.thread.tb9;

import java.util.Random;

/**
 * 工厂模式
 * 这个重看下视频，看视频有没有讲清楚这个点
 * sleep并没有释放锁，wait将锁释放了。
 */

public class ProductFactory {
	
	public Future createProduct(String name) {
		Future f = new Future(); // 创建一个订单
		System.out.println("下单成功，你可以去上班了》。。");
        /**
         * 为什么这个线程不会在工厂的方法被调用的时候执行
         * --   在主线程睡眠一秒就能发现了   只不过是线程执行的切换的顺序而已（军哥）
         * 但是，为什么是这样切换的
         *
         * 工厂方法在创建新的线程的时候cpu把线程切换到主线程执行。主线程执行f.get()方法的时候时候因为子线程还没执行完。所以又回到子线程执行(军哥)
         * 开辟线程在调用的时候是一个异步执行。
         */
		// 生产产品
		new Thread(new Runnable() {
			@Override
			public void run() {
			    System.out.println("进入线程");
				Product p = new Product(new Random().nextInt(), name);
				f.setProduct(p);
			}
		}).start();
		return f;//这里返回的是订单
	}

}
