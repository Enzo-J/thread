package com.roocon.thread.tb5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier��CountDownLatch������
 *
 * 1.CountDownLatch���߳���֮��ĵȴ�����һ��(����)�̵߳ȴ�N���߳����ĳ������֮����ִ�У���CyclicBarrier�����߳����ڵĵȴ���
 *   ��ÿ���߳��໥�ȴ�����N���̶߳�������֮��Ȼ������ִ�С�
 * 3.CountDownLatch����Ϊ0�޷����ã���CyclicBarrier�����ﵽ��ʼֵ����������á�
 * 4.CountDownLatch�����Ը��ã���CyclicBarrier���Ը��á�
 * 5��CountDownLatch��ͬ�������е��̱߳���ͬʱ�����ѣ��ͺñȵ�������������˱���ͬʱ��ʼ�׸�һ����
 *    CountDownLatchֻҪ�����̵߳Ķ����������������߳�ִ����֮��ִ�о�OK
 *
 * CyclicBarrier��ʹ�÷�ʽ��
 *ÿ���߳�ʹ��await()��������CyclicBarrier���Ѿ����������ϣ�Ȼ��ǰ�̱߳�������
 * �̶߳�����await()����֮�󶼵�����դ��λ�ã�Ȼ�󣬹����̲߳ſ�ʼִ��ҵ����
 * ���һ���̴߳��ڵȴ�״̬ʱ����������̵߳���reset()�����ߵ��õ�barrierԭ�����Ǳ��𻵵ģ����׳�BrokenBarrierException�쳣��
 * ͬʱ���κ��߳��ڵȴ�ʱ���ж��ˣ������������̶߳����׳�BrokenBarrierException�쳣������barrier������״̬��
 *
 * http://www.importnew.com/21889.html
 * CyclicBarrier���жϺ��������⣺
 * https://blog.csdn.net/hanchao5272/article/details/79779639��������ñȽ�ȫ��
 *
 */
public class Demo {

	Random random = new Random();
	public void meeting(CyclicBarrier barrier) {
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ��������ң��ȴ�����..");

		if(Thread.currentThread().getName().equals("Thread-7")) {
			/**
			 * �����7���߳��жϣ������߳��׳�BrokenBarrierException�쳣����ֹͣ�ȴ�������ִ�С�
 			 */
//			Thread.currentThread().interrupt();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			barrier.reset();

		}
		
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Demo demo = new Demo();

		CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
			@Override
			public void run() {
				System.out.println("�ã����ǿ�ʼ����...");
			}
		});

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.meeting(barrier);
				}
			}).start();
		}
		
		// ��صȴ��߳���
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�ȴ����߳��� " + barrier.getNumberWaiting());
					System.out.println("is broken " + barrier.isBroken());
				}
			}
		}).start();
	}

}
