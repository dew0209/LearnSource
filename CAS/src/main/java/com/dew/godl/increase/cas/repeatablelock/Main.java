package com.dew.godl.increase.cas.repeatablelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-16 16:19
 * @description
 */
public class Main {


	public synchronized void m1(){
		System.out.println(Thread.currentThread().getName() + "\t ----come in");
		m2();
		System.out.println(Thread.currentThread().getName() + "\t ----end m1");
	}

	public synchronized void m2(){
		System.out.println(Thread.currentThread().getName() + "\t ----come in");
		m3();
		System.out.println(Thread.currentThread().getName() + "\t ----end m2");
	}

	public synchronized void m3(){
		System.out.println(Thread.currentThread().getName() + "\t ----come in");
	}

	static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
//		Main main = new Main();
//		new Thread(()->{
//			main.m1();
//		},"t1").start();
		new Thread(()->{
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "\t ----come in外层");
				lock.lock();
				try {
					System.out.println(Thread.currentThread().getName() + "\t ----come in内层");
				}finally {
					//lock.unlock();//注释掉这个会阻塞，t2线程获取不到，锁没有正确释放
				}
			}finally {
				lock.unlock();
			}
		},"t1").start();
		new Thread(()->{
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "\t ----come in外层");
				lock.lock();
				try {
					System.out.println(Thread.currentThread().getName() + "\t ----come in内层");
				}finally {
					lock.unlock();
				}
			}finally {
				lock.unlock();
			}
		},"t2").start();

	}

	private static void repeatableM1() {
		final Object o = new Object();
		new Thread(()->{
			synchronized (o){
				System.out.println(Thread.currentThread().getName() + "\t -----外层调用");
				synchronized (o){
					System.out.println(Thread.currentThread().getName() + "\t -----中层调用");
					synchronized (o){
						System.out.println(Thread.currentThread().getName() + "\t -----内层调用");
					}
				}
			}
		},"t1").start();
	}

}
