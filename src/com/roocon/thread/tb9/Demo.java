package com.roocon.thread.tb9;

import java.util.Random;

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
