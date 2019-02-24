package com.roocon.thread.ta6;

import com.roocon.thread.t5.Singleton2;

/**
 * ����д����wait notify��������ʲô
 * ��������д�Ļ�����sleep��ʱ�򣬻���ֲ���ʱ����ͬ�����������wait��notify����������������
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
				System.out.println("�޸�״̬���߳�ִ��...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.set(1);
				System.out.println("״ֵ̬�޸ĳɹ�������");
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// �ȴ�signalΪ1��ʼִ�У�������ִ��
				/**
				 * ����������ʽ�����ж�
				 */
				while(d.get() != 1) {
					try {
						Thread.sleep(1500);  //���߲�ռ��CPU��Դ
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// ���ź�Ϊ1 ��ʱ��ִ�д���
				System.out.println("ģ������ִ��...");
				
				
			}
		}).start();
	}

}
