package com.thread.ta7;

public class Tmall {
	
	private int count;
	
	public final int MAX_COUNT = 10;
	
	public synchronized void push () {
        /**
         * Ϊʲô����ʹ��if
         * ��Ϊ������ʱ���߳�1 push����ִ��wait()��������ȴ��أ������ͷŶ���������ʱ������и��߳�2�õ�������push���� Ҳִ��wait()������
         * �������ĵȴ��ز����ͷ������߳�3�õ�����ִ��take������ʹ����������������ִ��notifyAll()�����߳�1��2�ӵȴ����з������ص�����
         * �п����߳�1�õ��������ִ��push����������������һ��Ԫ�أ������߳�1ִ�����ǡǡ�����߳�2�õ��������õ�����Ҳ����ִ��push������
         * Ҳ������һ��Ԫ�أ������ͳ����������������while�Ļ����������ж�һ�εĻ����߳�2��������������������Դ��� �������while����ʹ��if��ԭ��
         * ����ͬ��
		 *
		 * �򵥰桪������5���������̴߳���wait״̬��ʱ������õ���if����ô��count<MAX_COUNT��ʱ�򣬾�ȱ���жϣ�5���߳�һ���ԵĽ���count++������
         * Ϊ�˱�����һ���������while��
         */
        while(count >= MAX_COUNT) {
			try {
				System.out.println(Thread.currentThread().getName() + " ��������ﵽ���ޣ�������ֹͣ������");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count ++;
		System.out.println(Thread.currentThread().getName() + " ��������������ǰ���Ϊ��" + count);
		notifyAll();
	}
	
	public synchronized void take () {
        /**
         * ͨ����������Ĵ�ӡ��䣬���Է�����wait����֮��ִ�е���wait֮��Ĵ��룬��������ͷ��ʼ����ִ�С�
         */
//		System.out.println("��ʼ����");
		while(count <= 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " �������Ϊ�㣬�����ߵȴ���");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count --;
		System.out.println(Thread.currentThread().getName() + " ���������ѣ���ǰ���Ϊ��" + count);
		notifyAll();
	}

}
