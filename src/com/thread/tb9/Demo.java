package com.thread.tb9;

/**
 * 这里涉及到的，应该是线程的调度——
 * 1：main是主线程，在该线程的执行中，开辟了new ProductFactory(),打印；在遇到new Thread().start()之后，开辟出一个新的线程，
 * 这两个线程地位相同，各自运行；这时，主线程继续向下执行，所以退出new ProductFactory()，重回main函数，向下打印，在这段时间下，
 * 子线程正在开辟；开辟完成后的子线程开始运行（即开始生产蛋糕）。
 */
public class Demo {
	
	public static void main(String[] args) {
		
		ProductFactory pf = new ProductFactory();
		
		// 下单，交钱
		Future f = pf.createProduct("蛋糕");
//		try {
//			Thread.sleep(1000);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		System.out.println("我去上班了，下了班我来取蛋糕...");
		
		// 拿着订单获取产品
		System.out.println("我拿着蛋糕回家." + f.get());
	}

}
