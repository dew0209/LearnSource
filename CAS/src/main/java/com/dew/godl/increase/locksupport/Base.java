package com.dew.godl.increase.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockSupport
 * 用于创建锁和其他同步类的基本线程阻塞原语
 **/
public class Base {
	public static void main(String[] args) {
		/**
		 * 线程先要获得并持有锁，必须在锁块中
		 * 必须要先等待后唤醒，线程才能够被唤醒
		 **/
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		new Thread(()->{
			try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}//死锁--因为先进行通知了
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "\t ------");
				condition.await();//会释放锁 必须要持有锁
				System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		},"t1").start();
		//try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
		new Thread(()->{
			lock.lock();
			try {
				condition.signal();
				System.out.println(Thread.currentThread().getName() + "\t ------发出通知");
			}catch (Exception e){

			}finally {
				lock.unlock();
			}

		},"t2").start();
	}
	public static void m1(){
		Object obj = new Object();
		new Thread(()->{
			try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}//死锁--因为先进行通知了
			synchronized (obj){
				System.out.println(Thread.currentThread().getName() + "\t ------");
				try {
					obj.wait();//会释放锁 等待之前必须要持有锁
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
			}
		},"t1").start();
		//try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
		new Thread(()->{
			synchronized (obj){
				obj.notify();
				System.out.println(Thread.currentThread().getName() + "\t ------发出通知");
			}
		},"t2").start();
	}
}
