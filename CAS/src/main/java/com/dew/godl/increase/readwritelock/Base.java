package com.dew.godl.increase.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *	总线：
 *		无锁->独占锁->读写锁->邮戳锁
 *	读写锁：
 *		读写锁定义为：一个资源能够被多个线程访问，或者被一个写线程访问，但是不能同时存在读写线程
 *				读写互斥
 *				读读可以共享，多线程并发可以访问，大面积的可以容许多个线程来读取
 *		使用场景：读多写少的情况下，读写锁才具有较高的性能体现
 *		缺点：
 *			1.写锁饥饿问题，读多写少的时候容易饥饿
 *			2.注意，锁降级
 *		锁降级：
 *			将写入锁降级为读锁，锁的严苛程序变强叫做升级，反之叫降级
 *			遵循获取写锁，获取读锁再释放写锁的次序，写锁能够降级成为读锁
 *			一句话:同一个线程自己持有写锁再去拿读锁,其本质相当于重入
 */
public class Base {
	public static void main(String[] args) throws InterruptedException {
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		Lock readLock = readWriteLock.readLock();
		Lock writeLock = readWriteLock.writeLock();

		//正常情况下
//		writeLock.lock();
//		System.out.println("------写入------");
//		writeLock.unlock();
//
//		readLock.lock();
//		System.out.println("------读取------");
//		readLock.unlock();

		//同一个线程
		//降级
//		writeLock.lock();
//		System.out.println("------写入------");
//
//		//马上可以获取读锁
//		readLock.lock();
//		System.out.println("-------读取------");
//		writeLock.unlock();
//
//		readLock.unlock();
		//不存在读锁升级为写锁，注意
		readLock.lock();
		System.out.println("------读取------");

		//马上可以获取读锁
		writeLock.lock();
		System.out.println("-------写入------");
		readLock.unlock();

		writeLock.unlock();



	}

	public static void m1() throws InterruptedException {
		MyResource myResource = new MyResource();
		for(int i = 0;i < 10;i++){
			final int v = i;
			new Thread(()->{
				myResource.write(v + "",v + "");
			},String.valueOf(i)).start();
		}
		for(int i = 0;i < 10;i++){
			final int v = i;
			new Thread(()->{
				myResource.read(v + "");
			},String.valueOf(i)).start();
		}
		Thread.sleep(1000);
		for(int i = 0;i < 10;i++){
			final int v = i + 10;
			new Thread(()->{
				myResource.write(v + "",v + "");
			},"新写锁线程" + i).start();
		}
	}
}

class MyResource{

	Map<String,String> map = new HashMap<>();

	Lock lock = new ReentrantLock();

	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void write(String key,String value){
		readWriteLock.writeLock().lock();

		try {
			System.out.println(Thread.currentThread().getName() + "\t正在写入");
			map.put(key,value);
			Thread.sleep(500);
			System.out.println(Thread.currentThread().getName() + "\t完成写入");
		}catch (Exception e){

		} finally{
			readWriteLock.writeLock().unlock();
		}
	}

	public void read(String key){
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t正在读取");
			String value = map.get(key);
			System.out.println(value);
			//Thread.sleep(200);
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + "\t完成读取");
		}catch (Exception e){

		} finally{
			readWriteLock.readLock().unlock();
		}
	}

}
