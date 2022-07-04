package com.dew.godx.base;

/**
 * 调用yield，sleep，wait，notify等方法对锁有什么影响
 * 		yield：让出cpu资源，但是锁不释放
 *  	sleep：让出cpu资源，但是锁不释放
 *  	wait：会释放锁，当wait方法返回的时候，会重新持有锁
 *  	notify：不会释放锁[但是一般放在最后一行]
 */
public class LockDiffTest {
	private Object lock = new Object();

	public static void main(String[] args) {
		LockDiffTest sleepTest = new LockDiffTest();
		Thread threadA = sleepTest.new ThreadSleep();
		threadA.setName("ThreadSleep");
		Thread threadB = sleepTest.new ThreadNoSleep();
		threadB.setName("ThreadNoSleep");
		threadA.start();
		try {
			Thread.sleep(100);
		}catch (Exception e){
			e.printStackTrace();
		}
		threadB.start();
	}

	private class ThreadSleep extends Thread{
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " will take the lock ");
			try {
				synchronized (lock){
					System.out.println(threadName + " taking the lock");
					Thread.sleep(5000);
					System.out.println("finish the lock: " + threadName);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	private class ThreadNoSleep extends Thread{
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " will take the lock time = " + System.currentTimeMillis());
			synchronized (lock){
				System.out.println(threadName + " taking the lock time = " + System.currentTimeMillis());
				System.out.println("finish the work: " + threadName);
			}
		}
	}

}
