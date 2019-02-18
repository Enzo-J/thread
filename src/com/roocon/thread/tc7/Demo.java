package com.roocon.thread.tc7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * https://www.oschina.net/question/1379006_2236083?sort=time
 * ConcurrentHashMap性能是明显优于Hashtable和SynchronizedMap的,ConcurrentHashMap花费的时间比前两个的一半还少。
 */
public class Demo {
	
	public static void main(String[] args) {
		
		ArrayList<String> s = new ArrayList<>();
		Collections.synchronizedList(s);
		
		HashMap<String, Object> res = new HashMap<>();
		Collections.synchronizedMap(res);
	}

}
