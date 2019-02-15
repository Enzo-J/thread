package com.roocon.thread.t5;

public class Singleton2 {
	
	private Singleton2() {}

	/**
	 *  1�������volatile�������synchronized�´�������ʱ���ܳ��ֵ�ָ�����������⣨�����˿����ԣ�������ָ���������
     *  2�������˿����ԣ���Ȼ�߳�0�Ƚ��ĵ�һ��if�������������ȷʵ�߳�1������ʱ���߳�0�Ѿ��ж���instance==null,�����������ٴ���һ������
     *                ��Ϊ��û���˸����߳�0���߳�1�Ѿ������������ˣ����ڲ������if������Ϊ�˸��߱��ˣ��������Ѿ��������ˣ�
     *                �����volatile��������������һ���̣߳������Ѿ������ˣ���һ���߳��ڽ�������if�ж����ʱ�򣬾��ܹ�������ȷ���жϡ�
	 */
	private static volatile Singleton2 instance;
	
	/**
	 * ˫�ؼ�����
	 * �����ӳټ��ص�˼�루�����أ�
	 * @return
	 */
	public static Singleton2 getInstance () {
		// ����   while(true)
		if(instance == null) {
			synchronized (Singleton2.class) {
				if(instance == null) {
					//����Ϊʲô��Ҫ�ٴν������пղ���
                    /**
                     * ��Ȼ�߳�0�Ƚ��ĵ�һ��if�������������ȷʵ�߳�1������ʱ���߳�0�Ѿ��ж���instance==null,�����������ٴ���һ������
                     * ��Ϊ��û���˸����߳�0���߳�1�Ѿ������������ˣ����ڲ������if������Ϊ�˸��߱��ˣ��������Ѿ��������ˣ�
                     * ���ӣ�https://www.cnblogs.com/Geodon/p/3798371.html
                     */
					instance = new Singleton2();  // ָ��������
					
					// ����һ���ڴ�ռ�   // 1
					// �����ռ���ʵ��������  // 2
					// instance������ָ�����ռ��ַ   // 3
				}
			}
		}
		return instance;
	}
	
	// ���̵߳Ļ�����
	// �����й�����Դ
	// ����Դ���з�ԭ���Բ���
    /**
     * public static synchronized Singleton getInstance(){    //�Ի�ȡʵ���ķ�������ͬ��
     *        if (instance == null)
     *          instance = new Singleton();
     *        return instance;
     * }
     * ������������е��
     */
}
