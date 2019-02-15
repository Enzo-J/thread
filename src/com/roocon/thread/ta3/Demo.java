package com.roocon.thread.ta3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ����ReentrantReadWriteLock��Lock����һ��ʵ�ַ�ʽ�������Ѿ�֪����ReentrantLock��һ����������ͬһʱ��ֻ����һ���̷߳��ʣ�
 *    ��ReentrantReadWriteLock���������߳�ͬʱ���ʣ���������д�̺߳Ͷ��̡߳�д�̺߳�д�߳�ͬʱ���ʡ������������������˲����ԡ�
 *    ��ʵ��Ӧ���У��󲿷�����¶Թ������ݣ��绺�棩�ķ��ʶ��Ƕ�����Զ����д��������ʱReentrantReadWriteLock�ܹ��ṩ�����������õĲ����Ժ���������
 *
 * ������д���ڲ�ά������������һ�����ڶ�������һ������д���������� ReadWriteLockʵ�ֶ����뱣֤ writeLock�������ڴ�ͬ��Ч��ҲҪ��������� readLock����ϵ��
 *     Ҳ����˵���ɹ���ȡ�������̻߳ῴ��д����֮ǰ�汾���������и��¡�
 *
 *    ���̳߳��ж���������£����̲߳���ȡ��д��(��Ϊ��ȡд����ʱ��������ֵ�ǰ�Ķ�����ռ�ã������ϻ�ȡʧ�ܣ����ܶ����ǲ��Ǳ���ǰ�̳߳���)��
 *
 *    ���̳߳���д��������£����߳̿��Լ�����ȡ��������ȡ����ʱ�������д����ռ�ã�ֻ��д��û�б���ǰ�߳�ռ�õ�����Ż��ȡʧ�ܣ���
 *
 *    ��ϸ���룬�������Ǻ���ģ���Ϊ���̻߳�ȡ������ʱ�򣬿����������߳�ͬʱҲ�ڳ��ж�������˲��ܰѻ�ȡ�������̡߳�������Ϊд���������ڻ��д�����̣߳�
 *    ��һ����ռ�˶�д������˿��Լ���������ȡ����������ͬʱ��ȡ��д���Ͷ����󣬻��������ͷ�д���������ж���������һ��д���͡�������Ϊ�˶�����
 *
 *    ���ϣ�
 *
 *    һ���߳�Ҫ��ͬʱ����д���Ͷ����������Ȼ�ȡд���ٻ�ȡ������д�����ԡ�������Ϊ�������������ܡ�������Ϊд����
 *
 *    https://www.cnblogs.com/xiaoxi/p/9140541.html
 *    https://www.cnblogs.com/zaizhoumo/p/7782941.html
 */


public class Demo {

	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	public Object get(String key) {
		r.lock();
		System.out.println(Thread.currentThread().getName() + " ��������ִ��..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " ����ִ�����..");
		}
	}

	public void put(String key, Object value) {
		w.lock();
		System.out.println(Thread.currentThread().getName() + " д������ִ��..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " д����ִ�����..");
		}
	}

}
