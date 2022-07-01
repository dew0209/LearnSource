MQ：消息队列，FIFO（先进先出）。跨进程，上下游传递消息。

特点：流量消峰，应用解耦，异步处理。

接收，存储，转发消息。



生产者-----交换机 + 队列 （交换机和队列之间的关系是一对多）------消费者





```txt
生产者：产生数据发消息的程序是生产者。
交换机：交换机是RabbitMQ非常重要的一个部件，一方面它接收来自生产者的消息，另一方面它还将消息推送到队列中，交换机必须确切知道如何处理它接受到的消息，是将这些消息推送到特定队列还是推送到多个队列，亦或是把消息丢弃，这个得有交换机类型决定。
队列：队列是RabbitMQ内部使用的一种数据结构，尽管消息流经RabbitMQ和应用程序，但他们只能存储在队列中，队列仅受主机的内存和磁盘限制的约束，本质上是一个大的消息缓冲区。许多生产者可以将消息发送到一个队列，许多消费者可以尝试从一个队列接受数据。这就是我们使用队列的方式。
消费者：消费与接收具有相似的含义。消费者大多时候是一个等待接收消息的程序。请注意生产者。消费者和消息中间很多时候并不在同一机器上，同一个应用程序既可以是生产者又可以是消费者。
```







```txt
Broker：接收和分发消息的应用，RabbitMQ Server就是Message Broker。
Virtual host：出于多租户和安全因素涉及的，把AMQP的基本组件划分到一个虚拟的分组中，类似于网络中的namespace概念。当多个不同的用户使用同一个RabbitMQ Server提供的服务时，可以划分出多个vhost，每个用户在自己的vhost创建exchange/queue等。
Connection：publisher/consumer和broker之间的TCP连接。
Channel：如果每一次访问RabbitMQ都建立一个Connection，在消息量大的时候建立TCP Connection的开销是巨大的，效率也比较低。Channel实在connection内部建立的逻辑连结，如果应用程序支持多线程，通常每个thread创建单独的channel进行通讯，AMQP method包含了channel id帮助客户端和message broker识别channel，所以channel之间是完全隔开的。Channel作为轻量级的Connection极大减少了操作系统建立TCP connection的开销。
Exchange：message到达broker的第一站，根据分发规则，匹配查询表中的routing key，分发消息到queue中去。常用的类型有：direct（point-to-point），topic（publish-subscribe），fanout（multicast）。
Queue：消息最终被送到这里等待的consumer取走。
Binding：exchange和queue之间的虚拟连接，binding中可以包含routeing key，bingding消息被保存到exchange中的查询表中，用于message的分发依据。
```

