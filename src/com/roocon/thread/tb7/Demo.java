package com.roocon.thread.tb7;

import java.util.concurrent.Exchanger;

/**
 * Exchangerԭ��
 * ��һ���̵߳���exchange���õ�ʱ��������Ļ���̴߳�ǰ�Ѿ������˴˷�������ô���Ļ��ᱻ���Ȼ��Ѳ���֮���ж��󽻻���Ȼ����Է��ء�
 * ������Ļ�黹û���ｻ���㣬��ô��ǰ�߳̽��ᱻ����ֱ������̵߳������ɽ����������أ����ߵ�ǰ�̱߳��жϡ����׳��ж��쳣���ֻ����ǵȺ�ʱ�����׳���ʱ�쳣��
 * �����ṩ����Ĳ�����ͬ���ģ�
 * ���ڳɶԳ��ֵ��߳�֮�佻�����ݣ�
 * ��������˫���ͬ�����У�
 * ��Ӧ���ڻ����㷨����ˮ����Ƶȳ�����
 * Exchanger ��һ�������㷨����ǰ��SynchronousQueue һ��������ͨ��ѭ�� cas ��ʵ���̰߳�ȫ��������ַ�ʽ�ͻ��ԵñȽϳ�����鷳��
 *
 * https://www.cnblogs.com/davidwang456/p/4179488.html
 * �����������������/����֮���ʹ�ã�Ҳ��ͦ����ġ�
 *
 * https://blog.csdn.net/u014634338/article/details/78385521  --Դ�������Ŀǰû����
 */

public class Demo {
	
	public void a (Exchanger<String> exch) {
		
		System.out.println("a ����ִ��...");
		
		try {
			System.out.println("a �߳�����ץȡ����...");
			Thread.sleep(2000);
			System.out.println("a �߳�ץȡ������...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String res = "12345";
		
		try {
			System.out.println("a �ȴ��ԱȽ��...");
			exch.exchange(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void b (Exchanger<String> exch) {
		System.out.println("b ������ʼִ��...");
		try {
			System.out.println("b ������ʼץȡ����...");
			Thread.sleep(4000);
			System.out.println("b ����ץȡ���ݽ���...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = "12345";
		
		try {
			String value = exch.exchange(res); //�����õ���a�̴߳��ݹ�����ֵ
			System.out.println("��ʼ���бȶ�...");
			System.out.println("�ȶԽ��Ϊ��" + value.equals(res));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Demo d = new Demo();
		Exchanger<String> exch = new Exchanger<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.a(exch);
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.b(exch);
			}
		}).start();
		
	}

}
