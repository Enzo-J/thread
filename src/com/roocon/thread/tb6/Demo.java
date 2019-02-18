package com.roocon.thread.tb6;

import java.util.concurrent.Semaphore;

/**
 * Semaphore的用法：
 *     在很多情况下，可能有多个线程需要访问数目很少的资源。假想在服务器上运行着若干个回答客户端请求的线程。这些线程需要连接到同一数据库，但任一时刻
 *     只能获得一定数目的数据库连接。你要怎样才能够有效地将这些固定数目的数据库连接分配给大量的线程？
 *     答：1.给方法加同步锁，保证同一时刻只能有一个人去调用此方法，其他所有线程排队等待，但是此种情况下即使你的数据库链接有10个，也始终只有一个处于使
 *           用状态。这样将会大大的浪费系统资源，而且系统的运行效率非常的低下。
 *        2.另外一种方法当然是使用信号量，通过信号量许可与数据库可用连接数相同的数目，将大大的提高效率和性能。
 */
/**
 * Semaphore鏈変袱绉嶆ā寮忥紝鍏钩妯″紡鍜岄潪鍏钩妯″紡銆傚叕骞虫ā寮忓氨鏄皟鐢╝cquire鐨勯『搴忓氨鏄幏鍙栬鍙瘉鐨勯『搴忥紝閬靛惊FIFO锛?
 * 鑰岄潪鍏钩妯″紡鏄姠鍗犲紡鐨勶紝涔熷氨鏄湁鍙兘涓?涓柊鐨勮幏鍙栫嚎绋嬫伆濂藉湪涓?涓鍙瘉閲婃斁鏃跺緱鍒颁簡杩欎釜璁稿彲璇侊紝鑰屽墠闈㈣繕鏈夌瓑寰呯殑绾跨▼銆?
 *
 * FairSync涓嶯onFairSync鐨勫尯鍒氨鍦ㄤ簬浼氶鍏堝垽鏂綋鍓嶉槦鍒椾腑鏈夋病鏈夌嚎绋嬪湪绛夊緟锛屽鏋滄湁锛屽氨鑰佽?佸疄瀹炶繘鍏ュ埌绛夊緟闃熷垪锛?
 * 鑰屼笉鍍廚onfairSync涓?鏍烽鍏堣瘯涓?鎶婏紝璇翠笉瀹氬氨鎭板ソ鑾峰緱浜嗕竴涓鍙紝杩欐牱灏卞彲浠ユ彃闃熶簡銆?
 *
 * 鍏跺疄锛岃繖涓緥瀛愶紝鍐欏緱涓嶆槸寰堝ソ锛岀湅涓嶅嚭Semaphore鐨勭湡瀹炵敤娉曘??
 * 鍘熺悊锛?
 * 浠ヤ竴涓仠杞﹀満鏄繍浣滀负渚嬨?備负浜嗙畝鍗曡捣瑙侊紝鍋囪鍋滆溅鍦哄彧鏈変笁涓溅浣嶏紝涓?寮?濮嬩笁涓溅浣嶉兘鏄┖鐨勩?傝繖鏃跺鏋滃悓鏃舵潵浜嗕簲杈嗚溅锛岀湅闂ㄤ汉鍏佽鍏朵腑涓夎締涓嶅彈闃荤鐨勮繘鍏ワ紝鐒跺悗鏀句笅杞︽嫤锛?
 * 鍓╀笅鐨勮溅鍒欏繀椤诲湪鍏ュ彛绛夊緟锛屾鍚庢潵鐨勮溅涔熼兘涓嶅緱涓嶅湪鍏ュ彛澶勭瓑寰呫?傝繖鏃讹紝鏈変竴杈嗚溅绂诲紑鍋滆溅鍦猴紝鐪嬮棬浜哄緱鐭ュ悗锛屾墦寮?杞︽嫤锛屾斁鍏ヤ竴杈嗭紝濡傛灉鍙堢寮?涓よ締锛屽垯鍙堝彲浠ユ斁鍏ヤ袱杈嗭紝
 * 濡傛寰?澶嶃?傝繖涓仠杞︾郴缁熶腑锛屾瘡杈嗚溅灏卞ソ姣斾竴涓嚎绋嬶紝鐪嬮棬浜哄氨濂芥瘮涓?涓俊鍙烽噺锛岀湅闂ㄤ汉闄愬埗浜嗗彲浠ユ椿鍔ㄧ殑绾跨▼銆傚亣濡傞噷闈緷鐒舵槸涓変釜杞︿綅锛屼絾鏄湅闂ㄤ汉鏀瑰彉浜嗚鍒欙紝
 * 瑕佹眰姣忔鍙兘鍋滀袱杈嗚溅锛岄偅涔堜竴寮?濮嬭繘鍏ヤ袱杈嗚溅锛屽悗闈㈠緱绛夊埌鏈夎溅绂诲紑鎵嶈兘鏈夎溅杩涘叆锛屼絾鏄緱淇濊瘉鏈?澶氬仠涓よ締杞︺?傚浜嶴emaphore绫昏?岃█锛屽氨濡傚悓涓?涓湅闂ㄤ汉锛岄檺鍒朵簡鍙椿鍔ㄧ殑绾跨▼鏁般??
 */
public class Demo {
	
	public void method (Semaphore semaphore) {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is run ...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		semaphore.release();
	}
	
	
	public static void main(String[] args) {
		
		Demo d = new Demo();
		Semaphore semaphore = new Semaphore(10);
		
		while(true) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					d.method(semaphore);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		
	}

}
