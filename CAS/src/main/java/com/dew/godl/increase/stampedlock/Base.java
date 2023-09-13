package com.dew.godl.increase.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock：邮戳锁，版本锁
 * 		对读写锁的优化
 *		stamp：戳记，long类型，代表了锁的状态，当stamp返回零时，表示线程获取锁失败，并且，当释放锁或者转换锁的时候，都要传入最初的stamp值
 *		和ReentrantReadWriteLock的对比：
 *			ReentrantReadWriteLock：
 *				允许多个线程同时读，但是只允许一个线程写，在线程获取到写锁的时候，其他操作和读操作都会处于阻塞状态
 *				读锁和写锁也是互斥的，所以在读的时候是不允许写的，读写锁比传统的synchronized速度要快很多
 *				原因就是在于	ReentrantReadWriteLock支持读并发，读读可以共享
 *		StampedLock横空出世：
 *			ReentrantReadWriteLock的读锁被占用的时候，其他线程尝试获取写锁的时候会被阻塞
 *			但是StampedLock采取乐观锁获取锁后，其他线程尝试获取写锁的时候不会被阻塞，这其实是对锁的优化
 *			所以，在获取乐观读锁后，还需要对结果进行校验
 *		特点：
 *			1.所有获取锁的方法，都返回一个邮戳（stamp），stamp为零表示获取失败，其余都表示成功
 *			2.所有释放锁的方法，都需要一个邮戳（stamp），这个stamp必须和成功获取锁时得到的stamp一致
 *			3.StampedLock是不可重入的，危险（如果一个线程已持有了写锁，再去获取写锁就会产生写锁）
 *			4.StampedLock有三种访问模式：
 *				a.Reading（读模式悲观）：功能和ReentrantReadWriteLock的读锁类似
 *				b.Writing（写模式）：功能和ReentrantReadWriteLock的写锁类似
 *				c.Optimistic reading（乐观读模式）：无锁机制，类似于数据库中的乐观锁，支持读写并发，很乐观认为读取时没人修改，假如被修改再实现升级为悲观读模式
 *		缺点：
 *			不支持重入
 *			读写都不支持条件（condition）
 *			一定不要调用interrupt()方法
 */
public class Base {

	static int number = 37;

	static StampedLock lock = new StampedLock();

	public void write(){
		long stamp = lock.writeLock();
		System.out.println(Thread.currentThread() + "\t写线程准备修改");
		try {
			number = number + 13;
		}finally {
			lock.unlockWrite(stamp);
		}
		System.out.println(Thread.currentThread() + "\t写线程结束修改");
	}

	public void read(){
		long stamp = lock.readLock();
		System.out.println(Thread.currentThread().getName() + "\t come in readLock，4 seconds continue...");
		for(int i = 0;i < 4;i++){
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "\t正在读取中...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			int result = number;
			System.out.println(Thread.currentThread().getName() + "\t获得成员变量值result：" + result);
			System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
		}finally {
			lock.unlockRead(stamp);
		}
	}

	public void optimisticReading(){
		long stamp = lock.tryOptimisticRead();
		int result = number;
		System.out.println("4s前stampLock validate方法返回值，true没有修改，false有修改：" + lock.validate(stamp));
		for(int i = 0;i < 4;i++){
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "\t正在读取中...");
				System.out.println("stampLock validate方法返回值，true没有修改，false有修改：" + lock.validate(stamp));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!lock.validate(stamp)){
			System.out.println("有写操作");
			stamp = lock.readLock();
			try {
				System.out.println("乐观锁升级为悲观读");
				result = number;
				System.out.println("重新悲观读后result：" + result);
			}catch (Exception e){
				lock.unlockRead(stamp);
			}finally {

			}
		}
		System.out.println(Thread.currentThread().getName() + "\t finally value：" + result);
	}

	//传统的读写功能，和ReentrantReadWriteLock差不多
	public void m1(){
		Base base = new Base();
		new Thread(()->{
			base.read();
		},"readThread").start();
		try {
			Thread.sleep(1000);
		}catch (Exception e){

		}
		new Thread(()->{
			System.out.println("写线程come in");
			base.write();
		},"writeThread").start();
	}

	public static void main(String[] args) {
		Base base = new Base();
		new Thread(()->{
			base.optimisticReading();
		},"readThread").start();
		try {
			Thread.sleep(1000);
		}catch (Exception e){

		}
		new Thread(()->{
			System.out.println("写线程come in");
			base.write();
		},"writeThread").start();
	}

}
