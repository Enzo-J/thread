package com.roocon.thread.td5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * new thread�ı׶ˣ�
 * a. ÿ��new Thread�½��������ܲ
 * b. �߳�ȱ��ͳһ���������������½��̣߳��໥֮�侺����������ռ�ù���ϵͳ��Դ����������oom��
 * c. ȱ�����๦�ܣ��綨ʱִ�С�����ִ�С��߳��ж�
 *
 * �̳߳صĺô����ڣ�
 * a. ���ô��ڵ��̣߳����ٶ��󴴽��������Ŀ��������ܼѡ�
 * b. ����Ч������󲢷��߳��������ϵͳ��Դ��ʹ���ʣ�ͬʱ���������Դ���������������
 * c. �ṩ��ʱִ�У�newScheduledThreadPool(10)��������ִ�У�newScheduledThreadPool(10)�������̣߳�newSingleThreadExecutor()�������������ƣ�newFixedThreadPool()���ȹ��ܡ�
 * d. newCachedThreadPool���һ�㲻�ã����׳�OOM����õģ�����newFixedThreadPool()��https://blog.csdn.net/wenniuwuren/article/details/51700080
 *
 * ExecutorService��������ʵ�������ӣ�
 * newSingleThreadExecutor������һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ�С�
 * newCachedThreadPool������һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
 * newFixedThreadPool������һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
 * newScheduledThreadPool������һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
 *
 *
 * newCachedThreadPool�����������͵��̳߳��ص��ǣ�
 * �����̵߳Ĵ�����������û������(��ʵҲ�����Ƶ�,��ĿΪInterger. MAX_VALUE),?�������������̳߳�������̡߳�
 * �����ʱ��û�����̳߳����ύ���񣬼���������߳̿�����ָ����ʱ��(Ĭ��Ϊ1����)����ù����߳̽��Զ���ֹ����ֹ����������ύ���µ��������̳߳����´���һ�������̡߳�
 * ��ʹ��CachedThreadPoolʱ��һ��Ҫע�����������������������ڴ����߳�ͬʱ���У����л����ϵͳ̱����
 */

public class Demo {
	
	public static void main(String[] args) {
		
		
		// 10���߳����������������
//		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		ExecutorService pool = Executors.newFixedThreadPool(10);
//		ExecutorService pool = Executors.newCachedThreadPool();
//		ExecutorService pool = Executors.newSingleThreadExecutor();
//		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
//		ExecutorService pool = Executors.newWorkStealingPool();

		while(true) {
			
			Future<?> f = pool.submit(new Runnable() {
				
				@Override
				public void run() {
					
				}
			});
			
			
//			pool.schedule(new Runnable() {
//				
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName());
//				}
//			}, 5, TimeUnit.SECONDS);
			
			
//			pool.execute(new Runnable() {
//				
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
		}
		
	}

}
