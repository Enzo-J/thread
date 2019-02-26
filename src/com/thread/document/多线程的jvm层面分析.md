###重排序问题
为了提高性能，编译后的代码指令重排序。  
####三种依赖性
不可重排序，所以jvm在这三种情况下不会进行重排序（读后读没有依赖性，可重排序）  
写后读  
读后写  
写后写  
a=1  写操作  
c=a  读操作
解决方案：volatile（对于标志位），能有效的限制指令重排序
###happen-before
happen-before不影响重排序，这是两个概念，所以（a happen-before b，a不一定在b之前运行）  
用来指定两个指令（可能不在同个线程）的运行顺序，提供跨线程的内存可见性
（a happen-before b，a不一定在b之前运行）
在Java的内存模型中，如果一个操作的执行结果需要对另一个操作可见，那么这两个
操作之间必然存在happen-before关系。  
规则：
1：程序顺序规则—— 单个线程中的每个操作，总是前一个操作happens-before于该线程中的任意后续操作  
2：监视器规则—— 对一个锁的解锁，总是happens-before于随后对这个锁的加锁  
3：volatile变量规则——对一个volatile域的写，happens-before于任意后续对这个volatile域的读  
4：传递性  
5：...
###锁的内存语义
#####锁的释放与获取所建立的happens-before关系
```java
/**
 * 程序次序规则 
 * 监视器规则
 * 传递性
 * @author worker
 *
 */
public class Demo {
	
	private int value;
	
	public synchronized void a() { // 1  获取锁
		value ++; // 2
	} // 3 释放锁
	
	public synchronized void b() { // 4 获取锁
		int a = value; // 5
		// 处理其他的操作 
	} // 6 释放锁
	
}
```
#####锁的获取和释放的内存语义
释放锁的时候将线程独占空间中的对象刷回堆空间，获取锁的时候将自身空间的对象无效之后，从堆空间中重新获取；
a线程释放锁的时候，会通知b线程，我已经干完了。
###volatile的内存语义
volatile的读和写，就类似于锁的获取和释放。因为释放是在获取之前的，所以写一定是在读之前的。  
当写一个volatile变量时，Java内存模型会把该线程对应的本地内存中的共享变量值刷新到主内存中。  
当读一个volatile变量时，Java内存模型会把当前线程对应的本地内存中的共享变量置为无效，然后从主内存中读取共享变量。  

###final域的内存语义

###问题定位
1：网络问题  
2：操作系统分析 top（linux监控）  
3：虚拟机（jvm工具——jconsole），其实日志也能发现问题   
4：数据库层面  
5：代码层面  
