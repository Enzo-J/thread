package com.roocon.thread.tb6;

import java.util.concurrent.Semaphore;

/**
 * Semaphore���÷���
 *     �ںܶ�����£������ж���߳���Ҫ������Ŀ���ٵ���Դ�������ڷ����������������ɸ��ش�ͻ���������̡߳���Щ�߳���Ҫ���ӵ�ͬһ���ݿ⣬����һʱ��
 *     ֻ�ܻ��һ����Ŀ�����ݿ����ӡ���Ҫ�������ܹ���Ч�ؽ���Щ�̶���Ŀ�����ݿ����ӷ�����������̣߳�
 *     ��1.��������ͬ��������֤ͬһʱ��ֻ����һ����ȥ���ô˷��������������߳��Ŷӵȴ������Ǵ�������¼�ʹ������ݿ�������10����Ҳʼ��ֻ��һ������ʹ
 *           ��״̬��������������˷�ϵͳ��Դ������ϵͳ������Ч�ʷǳ��ĵ��¡�
 *        2.����һ�ַ�����Ȼ��ʹ���ź�����ͨ���ź�����������ݿ������������ͬ����Ŀ�����������Ч�ʺ����ܡ�
 */
public class Demo {
	
	public void method (Semaphore semaphore) {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is run ...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		semaphore.release();
	}
	
	
	public static void main(String[] args) {
		
		Demo d = new Demo();
		Semaphore semaphore = new Semaphore(10);
		
		while(true) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					d.method(semaphore);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		
	}

}
