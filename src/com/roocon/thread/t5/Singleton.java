package com.roocon.thread.t5;

public class Singleton {
	
	// ˽�л����췽��
	private Singleton () {}

	private static Singleton instance = new Singleton();  //����ֻ������һ�ζ������Ժ������ܵ����˼���getInstance(),����ͬһ������
	
	public static  Singleton getInstance() {
		return instance;
	}
	
	// ���̵߳Ļ�����
	// �����й�����Դ
	// ����Դ���з�ԭ���Բ���

	/**
	 * ���������е�һ��ȱ���Ǹ�����ص�ʱ��ͻ�ֱ��new һ����̬�����������ϵͳ����������϶�ʱ����ʹ�������ٶȱ��� ��
     *  �����ʺ���Сϵͳ��
	 */
}
