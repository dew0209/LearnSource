package com.dew.godx.other.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock接口核心方法：
 * 	lock()
 * 	unlock()
 * 	tryLock()
 * sync获取锁是不能被中断的(一直阻塞在那里)，lock是可以被中断的
 * synchronized和lock比较
 *		synchronized代码简洁。
 *   	获取锁可以被中断，超时获取锁，尝试获取锁，使用lock
 * 使用锁的范式：
 * 	try finally 因为锁必须要释放
 *
 * 	可重入：
 * 		同一个线程多次需要同一把锁
 * 	公平锁和非公平锁：
 * 		如果在时间上，先对锁进行获取的请求，一定被满足，这个锁就是公平的，反之就是非公平的
 * 		非公平锁的效率略高于公平锁
 * 	排他锁（独占锁）：只允许一个线程进行访问
 * 	读写锁：同一时刻允许多个读线程进行访问，但是写线程访问的时候，所有的读写都被阻塞，读是多个的，写的独占的。
 */
public class Base {
	//可重入，独占锁
	private static Lock lock = new ReentrantLock();
	private int count;
	public static void main(String[] args) {

	}
	public void add(){
		lock.lock();
		try {
			count++;
		}catch (Exception e){

		}finally {
			//必须使用try，因为锁需要释放
			lock.unlock();
		}
	}
	public synchronized void add2(){
		count++;
	}
}
