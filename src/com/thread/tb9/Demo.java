package com.thread.tb9;

/**
 * �����漰���ģ�Ӧ�����̵߳ĵ��ȡ���
 * 1��main�����̣߳��ڸ��̵߳�ִ���У�������new ProductFactory(),��ӡ��������new Thread().start()֮�󣬿��ٳ�һ���µ��̣߳�
 * �������̵߳�λ��ͬ���������У���ʱ�����̼߳�������ִ�У������˳�new ProductFactory()���ػ�main���������´�ӡ�������ʱ���£�
 * ���߳����ڿ��٣�������ɺ�����߳̿�ʼ���У�����ʼ�������⣩��
 */
public class Demo {
	
	public static void main(String[] args) {
		
		ProductFactory pf = new ProductFactory();
		
		// �µ�����Ǯ
		Future f = pf.createProduct("����");
//		try {
//			Thread.sleep(1000);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		System.out.println("��ȥ�ϰ��ˣ����˰�����ȡ����...");
		
		// ���Ŷ�����ȡ��Ʒ
		System.out.println("�����ŵ���ؼ�." + f.get());
	}

}
