package com.dew.godx.increase.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch：
 * 	作用：是一组线程等待其他线程完成工作以后在执行(这样说有点模糊了，应该是latch.countDown()减少到0，线程就有资格去执行了，不需要等待初始化线程执行完毕，因为初始化线程可能有自己的工作)，加强版join
 * 	方法：
 * 		await：等待
 * 		countDown：计数器减一
 *
 * 	演示：
 * 		有5个初始化的线程，有6个扣除点
 * 		扣除完毕之后，主线程和业务线程才能继续自己的工作
 */
public class CountDownLatchTest {
	static CountDownLatch latch = new CountDownLatch(6);
	//初始化线程
	private static class InitThread implements Runnable{
		@Override
		public void run() {
			System.out.println("Thread_" + Thread.currentThread().getId() + " ready init work...");
			latch.countDown();//初始化线程完成工作了
			for(int i = 0;i < 2;i++){
				System.out.println("Thread_" + Thread.currentThread().getId() + "...continue to its work");
			}
		}
	}
	//业务线程
	private static class BusiThread implements Runnable {
		@Override
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i = 0;i < 3;i++){
				System.out.println("BusiThread_" + Thread.currentThread().getId() + " do business~~");
			}
		}
	}

	public static void main(String[] args) throws Exception{
		//单独的初始化线程
		new Thread(()->{
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread_" + Thread.currentThread().getId() + " ready init work step 1st...");
			latch.countDown();
			System.out.println("begin step 2nd...");
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread_" + Thread.currentThread().getId() + " ready init work step 2st...");
			latch.countDown();
		}).start();
		new Thread(new BusiThread()).start();
		for(int i = 0;i < 4;i++){
			new Thread(new InitThread()).start();
		}
		latch.await();
		System.out.println("Main thread do its work...");
	}
}
