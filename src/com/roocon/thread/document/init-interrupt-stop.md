#线程状态详解：
###中断线程的方式
中断，很容易想到的是stop和interrupt。stop方式过于粗暴，锁等资源都没有进行释放就强行
中断，这样很不友好。  
interrupt就比较友好，但是不会中断正在运行的线程，只能改变中断状态而已，所以也不
是中断的常用方法。  
其实，最为常用的方式，是采用共享变量进行控制。  
###interrupt的用法
https://blog.csdn.net/hjiacheng/article/details/73277254
###sleep interrupt Exception的解析：
https://blog.csdn.net/lilylove1994/article/details/78964330
###Runnable 
就相当于一个线程任务，最终依旧是交给线程来执行
###spring的异步使用
@Async——异步注解  