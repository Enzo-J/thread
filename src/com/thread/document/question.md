###1:案件同步
#####知识点：rabbitmq
不适用于下游需要实时反馈的技术点——例如登陆  
rabbitmq序列文档：https://www.cnblogs.com/daiwei1981/p/9394587.html  
————————背景—————————  
将一个系统的案件同步到另一个系统，量不大，但追求稳    
1:案件怎么保证不丢失（可靠性）  
1）生产者弄丢了数据——使用confirm  
在生产者那里设置开启confirm模式之后，你每次写的消息都会分配一个唯一的id，然后如果写入了rabbitmq中，rabbitmq会给你回传一个ack消息，告诉你说这个消息ok了。如果rabbitmq没能处理这个消息，会回调你一个nack接口，
告诉你这个消息接收失败，你可以重试。而且你可以结合这个机制自己在内存里维护每个消息id的状态，如果超过一定时间还没接收到这个消息的回调，那么你可以重发。  
2）rabbitmq弄丢了数据——持久化  
设置持久化有两个步骤，第一个是创建queue的时候将其设置为持久化的，这样就可以保证rabbitmq持久化queue的元数据，但是不会持久化queue里的数据；第二个是发送消息的时候将消息的deliveryMode设置为2，就是将消息设置为持久化的，
此时rabbitmq就会将消息持久化到磁盘上去。必须要同时设置这两个持久化才行，rabbitmq哪怕是挂了，再次重启，也会从磁盘上重启恢复queue，恢复这个queue里的数据。  
而且持久化可以跟生产者那边的confirm机制配合起来，只有消息被持久化到磁盘之后，才会通知生产者ack了，所以哪怕是在持久化到磁盘之前，rabbitmq挂了，数据丢了，生产者收不到ack，你也是可以自己重发的。  
exchangeDeclare()和queueDeclare()这两个方法中有相应的参数进行持久化（true——持久化，false——非持久化）  
3）消费端弄丢了数据——这个时候得用rabbitmq提供的ack机制，简单来说，就是你关闭rabbitmq自动ack，可以通过一个api来调用就行，然后每次你自己代码里确保处理完的时候，再程序里ack一把。这样的话，如果你还没处理完，不就没有ack？
   那rabbitmq就认为你还没处理完，这个时候rabbitmq会把这个消费分配给别的consumer去处理，消息是不会丢的。  
   basicConsume()里面的参数置false,使用basicAck()及进行手动ack    
2：怎么保证高可用  
    镜像集群模式——rabbitmq有很好的管理控制台，就是在后台新增一个策略，这个策略是镜像集群模式的策略，指定的时候可以要求数据同步到所有节点的，也可以要求就同步到指定数量的节点，然后你再次创建queue的时候，
    应用这个策略，就会自动将数据同步到其他的节点上去了。  
     
3：如何保证消息不被重复消费（如何保证消息消费时的幂等性）  
3.1：幂等性，我通俗点说，就一个数据，或者一个请求，给你重复来多次，你得确保对应的数据是不会改变的，不能出错。  
3.2：解决方案——在代码层面进行判断，举个例子：让生产者发送每条数据的时候，里面加一个全局唯一的id，类似订单id之类的东西，然后你这里消费到了之后，先根据这个id去比如redis里查一下，之前消费过吗？如果没有消费过，
     你就处理，然后这个id写redis。如果消费过了，那你就别处理了，保证别重复处理相同的消息即可。         

4：如何保证消息的顺序性
    拆分多个queue，每个queue一个consumer，就是多一些queue而已，确实是麻烦点；或者就一个queue但是对应一个consumer，然后这个consumer内部用内存队列做排队，然后分发给底层不同的worker来处理。
    不能一个queue，多个consumer，这明显乱了。  

5：如何解决消息队列的延时以及过期失效问题？消息队列满了以后该怎么处理？有几百万消息持续积压几小时，说说怎么解决？
    从消费端解决（10倍queue+customer进行处理）；万一数据TTL过期，没办法，重导数据；mq快写满了，没办法，重导数据。  

6：消息队列的使用场景  
    解耦  异步  削峰
    
7：自己如何实现消息队列
  生产者  消费者   阻塞队列  

8：rabbitmq自动及手动ACK  
https://blog.csdn.net/m0_38140657/article/details/80915561  
https://blog.csdn.net/youbl/article/details/80425959  

具体的代码实现：
https://blog.csdn.net/vbirdbest/article/details/78670550  
basicQos——https://blog.csdn.net/zhongjay/article/details/84753291


###2：
#####知识点：netty
————————背景—————————
为什么选择 Netty
说说业务中，Netty 的使用场景
原生的 NIO 在 JDK 1.7 版本存在 epoll bug
什么是TCP 粘包/拆包
TCP粘包/拆包的解决办法
Netty 线程模型
说说 Netty 的零拷贝
Netty 内部执行流程
Netty 重连实现  


###3：
#####知识点：mongo
————————背景—————————


###4：
#####知识点：redis
————————背景—————————
Redis 有哪些类型
Redis 内部结构
聊聊 Redis 使用场景
Redis 持久化机制
Redis 如何实现持久化
Redis 集群方案与实现
Redis 为什么是单线程的
缓存奔溃
缓存降级
使用缓存的合理性问题


技术点——https://juejin.im/post/5a94a8ca6fb9a0635c049e67