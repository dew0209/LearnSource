package com.dew.godx.other.concurrent.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 当队列满的时候，插入元素的线程被阻塞，直到队列不满
 * 队列为空的时候，获取元素的线程被阻塞，直到队列不空
 * 生产者消费者模式，生产者和消费者之间能力不匹配的问题，加一个容器。
 * 		方法	抛出异常	返回值	一直阻塞	超时退出
 * 		插入	add			offer	put			offer(time)
 * 		移除	remove		poll	take		poll(time)
 * 		检查	element		peek	无			无
 * 常用阻塞队列
 * 		ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列
 * 			先进先出，要求设定初始大小
 * 		LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列
 * 			先进先出，可以不设定初始大小，默认Integer.MAX_VALUE
 * 		ArrayBlockingQueue只有一个锁，LinkedBlockingQueue用了两个锁，实现上，ArrayBlockingQueue直接插入，LinkedBlockingQueue需要转换
 *
 *		PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列
 *			没有指定大小，根据硬件来决定大小，默认情况下，按照字典顺排序
 *		DelayQueue：一个使用优先队列实现的无界阻塞队列
 *			支持延迟获取的元素的阻塞队列，放入队列中的元素，必须实现Delay接口。实现自己的缓存系统，订单到期，限时支付
 *		SynchronousQueue：一个不存储元素的阻塞队列
 *			每一个put操作，需要等待一个take操作
 *		LinkedTransferQueue：一个由链表结构组成的无界阻塞队列
 *			transfer()必须要消费者消费了以后才返回 tryTransfer()无论消费者是否接收，方法立即返回
 *		LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列
 *			从队列的头和尾都可以插入和移除元素。★：take=takeFirst 其他不指明是first的还是last的，默认是last
 */
public class Base {
	public static void main(String[] args) throws Exception {

		DelayQueue<ItemVo<Order>> itemVos = new DelayQueue<>();

		Thread t1 = new Thread(new PutOrder(itemVos));
		t1.start();
		Thread t2 = new Thread(new FetchOrder(itemVos));
		t2.start();
		for (int i = 1;i < 20;i++){
			Thread.sleep(500);
			System.out.println(i * 500);
		}
	}
}
