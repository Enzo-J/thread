package com.thread.td1;

import java.util.concurrent.locks.StampedLock;

/**
 * ����Ƶ
 * StampedLock��1.8������������д���ĸĽ���
 * http://blog.sina.com.cn/s/blog_6f5e71b30102xfsb.html
 */
public class Demo {
	
	private int balance;
	
	private StampedLock lock = new StampedLock();
	//�������ĸ���
	public void conditionReadWrite (int value) {
		
		// �����ж�balance��ֵ�Ƿ���ϸ��µ�����
		long stamp = lock.readLock();
		while (balance > 0) {
			long writeStamp = lock.tryConvertToWriteLock(stamp);
			if(writeStamp != 0) { // �ɹ�ת����Ϊд��
				stamp = writeStamp;
				balance += value;
				break;
			} else {
				// û��ת����д����������Ҫ�����ͷŶ�����Ȼ�����õ�д��
				lock.unlockRead(stamp);
				// ��ȡд��
				stamp = lock.writeLock();
			}
 		}
		
		lock.unlock(stamp);
	}

    /**
     * �ֹ���
     */


	public void optimisticRead() {
		long stamp = lock.tryOptimisticRead();
		int c = balance;
		// ������ܻ������д���������Ҫ�����ж�
		if(!lock.validate(stamp)) {
			// Ҫ���¶�ȡ
			long readStamp = lock.readLock();
			c = balance;
			//����Ʊ��
			stamp = readStamp;
		}
		/// 
		lock.unlockRead(stamp);
	}

    /**
     * ������
     */

	public void read () {
		long stamp = lock.readLock();
		lock.tryOptimisticRead();
		int c = balance;
		// ...
		lock.unlockRead(stamp);
	}
	
	public void write(int value) {
		long stamp = lock.writeLock();
		balance += value;
		lock.unlockWrite(stamp);
	}
	

}