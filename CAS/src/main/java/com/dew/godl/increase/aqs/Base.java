package com.dew.godl.increase.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 是用来实现锁或者其他同步组件的公共基础部分的抽象实现，是重量级基础框架及整个JUC体系的基石，主要用于解决锁分配给“谁”的问题
 * 整体就是一个FIFO队列来完成资源获取线程的排队工作，并通过一个int类变量表示持有锁的状态
 *
 * 和aqs有关的（但不限于）：
 * 		ReentrantLock CountDownLatch Semaphore ...
 * 锁和同步器的关系：
 * 		锁，面向锁的使用者：使用锁
 * 		同步器，面向锁的使用者：统一规范并简化了锁的实现，将其抽象出来，屏蔽了同步状态管理，同步队列的管理和维护，阻塞线程排队和通知，唤醒机制等，
 * 								是一切锁和同步组件实现的公共基础部分
 * 加锁会导致阻塞：有阻塞就需要排队，实现排队必然需要队列
 *				如果共享资源被占用，就需要一定的阻塞等待唤醒机制来保证锁的分配，这个机制主要用的是CLH队列的变体实现的，将暂时获取不到锁的线程加入到队列中，
 *					这个队列就是AQS同步队列的抽象实现，它将要请求共享资源的线程及自身的等待状态封装成队列的结点对象（Node），通过CAS，自旋以及LockSupport.park()的方式，
 *					维护state变量，使并发达到同步的效果
 * AQS使用一个volatile的int类型的成语变量来表示同步状态，通过内置的fifo队列来完成资源获取的排队工作，将每条要去抢占资源的线程封装成一个Node节点来实现锁的分配，通过cas完成对state值的修改
 *
 * AQS分析：
 * 		1.int state：AQS的同步状态state成员变量
 * 		2.CLH队列：双向队列  尾部入队，头部入队
 * 		3.内部类Node：
 * 			waitStatus：Node的等待状态
 * 						new Node() 共享
 * 						null：独占
 * 						1：线程被取消了
 * 						-1：后继线程需要唤醒
 * 						-2：等待condition唤醒
 * 						-3：共享式同步状态获取将会无条件地传播下去
 * 						初始为0
 * 			pre：前置
 * 			next：后置
 * 			thread：被那个线程拥有
 *
 *
 */
public class Base {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {

		}finally {
			lock.unlock();
		}
//		new CountDownLatch(10);
//
//		new Semaphore(10);
	}
}
