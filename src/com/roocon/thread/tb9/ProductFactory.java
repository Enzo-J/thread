package com.roocon.thread.tb9;

import java.util.Random;

/**
 * ����ģʽ
 * ����ؿ�����Ƶ������Ƶ��û�н���������
 * sleep��û���ͷ�����wait�����ͷ��ˡ�
 */

public class ProductFactory {
	
	public Future createProduct(String name) {
		Future f = new Future(); // ����һ������
		System.out.println("�µ��ɹ��������ȥ�ϰ��ˡ�����");
        /**
         * Ϊʲô����̲߳����ڹ����ķ��������õ�ʱ��ִ��
         * --   �����߳�˯��һ����ܷ�����   ֻ�������߳�ִ�е��л���˳����ѣ����磩
         * ���ǣ�Ϊʲô�������л���
         *
         * ���������ڴ����µ��̵߳�ʱ��cpu���߳��л������߳�ִ�С����߳�ִ��f.get()������ʱ��ʱ����Ϊ���̻߳�ûִ���ꡣ�����ֻص����߳�ִ��(����)
         * �����߳��ڵ��õ�ʱ����һ���첽ִ�С�
         */
		// ������Ʒ
		new Thread(new Runnable() {
			@Override
			public void run() {
			    System.out.println("�����߳�");
				Product p = new Product(new Random().nextInt(), name);
				f.setProduct(p);
			}
		}).start();
		return f;//���ﷵ�ص��Ƕ���
	}

}
