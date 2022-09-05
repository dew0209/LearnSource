package com.dew.godx.other.aqs;

/**
 *	锁的实现：AQS
 *	AbstractQueuedSynchronizer
 *	AQS中的数据结构:
 *		节点和同步队列
 *	模板方法设计模式
 *
 *	模板方法：
 *		独占式：
 *			acquire
 *			acquireInterruptibly
 *			tryAcquireNanos
 *
 *			release
 *			需要子类覆盖的方法:
 *				tryAcquire
 *				tryRelease
 *		共享式：
 *			acquireShared
 *			acquireSharedInterruptibly
 *			tryAcquireSharedNanos
 *
 *			releaseShared
 *			需要子类覆盖的方法:
 *				tryAcquireShared
 *				tryReleaseShared
 *		额外需要子类覆盖的方法：
 *			isHeldExclusively 同步器是否处于独占模式 也就是当前实现的是独占锁还是共享锁
 *		state：
 *			同步状态
 *			getState	获取当前的同步状态
 *			setState	设置当前同步状态
 *			compareAndSetState 同步设置当前的同步状态（cas）
 *
 *
 * 一个线程没有获得锁，就会将其封装成为一个节点，然后放进同步队列里面
 * 		AQS中的数据结构
 * 			节点：
 * 				状态：waitStatus
 * 					CANCELLED：1  线程等待超时或者被中断了，需要从队列中移走
 * 					SIGNAL：-1	  后续的节点处于等待状态，当前节点通知后面的节点去运行
 * 					CONDITION：-2 当前节点处于等待队列
 * 					PROPAGATE：-3 当前的状态需要往后面的状态传播
 * 					0：当前的节点处于初始状态
 * 				prev：前驱
 * 				next：后继
 * 				thread：当前节点所存储的线程信息
 * 				nextWaiter：在等待队列里面，下一个等待的节点
 * 			同步队列：先进先出，双向链表
 * 				设置首节点，不需要cas
 * 				设置尾节点，需要cas
 * 		condition
 * 			一个同步队列可以对应多个等待队列
 * 			await：以节点的线程构造新的节点并加入等待队列
 * 			signal：等待队列线程移到同步队列
 * 		读写锁：
 * 			一个state，32bit，分两部分，一部分写锁，一部分读锁
 * 			每个读锁重入了几次有ThreadLocalHoldCounter维护
 * 			写锁可以降级为读锁，但是读锁不能升级为写锁。写锁是阻塞读锁的
 * 		共享锁的state控制共享，同时还可以进行多个节点的唤醒
 */
public class Base {

}
