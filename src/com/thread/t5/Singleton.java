package com.thread.t5;

/**
 * ����ʽ���������̰߳�ȫ������
 */
public class Singleton {
	
	// ˽�л����췽��
	private Singleton () {}

	private static Singleton instance = new Singleton();  //����ֻ������һ�ζ������Ժ������ܵ����˼���getInstance(),����ͬһ������
	
	public static  Singleton getInstance() {
		return instance;
	}
	
	// ���̵߳Ļ�����
	// �����й�����Դ instance
	// ����Դ���з�ԭ���Բ���   �����Ƿ��ض�����ԭ���Բ���

	/**
	 * ���������е�һ��ȱ���Ǹ�����ص�ʱ��ͻ�ֱ��new һ����̬�����������ϵͳ����������϶�ʱ����ʹ�������ٶȱ��� ��
     *  �����ʺ���Сϵͳ��
	 */
}
