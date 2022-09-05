package com.dew.godx.other.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试自己的锁 selfLock
 */
public class TestMyLock {
	public void test(){
		final Lock lock = new SelfLock();
		class Worker extends Thread {
			@Override
			public void run() {
				while (true){
					lock.lock();
					try {
						Thread.currentThread().sleep(1000);
						System.out.println(Thread.currentThread().getName());
					}catch (Exception e){

					}finally {
						lock.unlock();
					}
					try {
						Thread.currentThread().sleep(2000);
					}catch (Exception e){

					}
				}
			}
		}
		//启动10个线程
		for(int i = 0;i < 10;i++){
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}
		for(int i = 0;i < 10;i++){
			try {
				Thread.currentThread().sleep(1000);
				System.out.println();
			}catch (Exception e){

			}
		}
	}

	public static void main(String[] args) {
		TestMyLock testMyLock = new TestMyLock();
		testMyLock.test();
	}
}
