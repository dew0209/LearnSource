package com.dew.godl.increase.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Aqs源码分析：
 * 		Lock接口的实现类，基本都是通过聚合了一个队列同步器的子类完成线程访问控制的
 * 		ReentrantLock分析：
 * 			公平锁和非公平锁的区别：
 * 				公平锁：线程在获取锁时，如果这个锁的等待队列中已经有线程在等待，那么当前线程就会进入等待队列中
 * 				非公平锁：不管是否有等待队列，如果可以获取锁，则立刻占有锁对象。也就是说队列的第一个线程排队苏醒后，不一定就是排头的这个线程获得锁，它还是需要参加竞争锁，后来的线程就可能不讲武德插队抢锁了。
 * 				获取同步状态时多了一个限制条件hasQueuedPredecessors
 * 					hasQueuedPredecessors()：hasQueuedPredecessors是公平锁加锁判断等待队列中是否存在有效节点的方法
 *				lock：
 *					tryAcquire：
 *						return false：继续推进条件，走下一个方法
 *						return true：结束
 *					addWaiter：
 *						1：enq(node)
 *						2：双向链表中，第一个节点为虚拟节点（也叫哨兵节点），其实不存储任何信息，只是占位。真正的第一个有数据的节点，是从第二个节点开始的。
 *					acquireQueued：
 *						1：shouldParkAfterFailedAcquire
 *						2：parkAndCheckInterrupt
 *				unlock：
 *					tryRelease：状态位变化
 *
 *
 *	总结：
 *		1.尝试加锁
 *		2.加锁失败，线程入队列
 *		3.线程入队列后，进入阻塞状态
 *		4.等待唤醒，继续上述流程
 *
 *
 */
public class Base1 {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {

		}finally {
			lock.unlock();
		}
	}
}
