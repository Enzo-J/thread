package com.thread.t2;

public class Demo1 extends Thread {
	/**
	 * ������д�˹��췽����Ҫ��ԭ�����õ���Thread()���캯����
	 * @param name
	 */
	public Demo1(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(!interrupted()) {
			System.out.println(getName() + "�߳�ִ���� .. ");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Demo1 d1 = new Demo1("first-thread");
		Demo1 d2 = new Demo1("second-thread");
		
		
		d1.start();
		d2.start();
		
//		d1.stop();
        /**
         * ����ʹ��interrupt���޷�ʵ�ֽ�ֹd1�߳����еģ���Ϊ�䲻���ֹ�������е��̣߳����ԣ�������Ҫ���!interrupted()���в�����
         */
		d1.interrupt();
	}
	
}
