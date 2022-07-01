package com.dew.godx.base;

/**
 *
 */
public class ThreadLocalTest {
	static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 1;
		}
	};
	//运行三个线程
	public void startThreadArray(){
		Thread thread[] = new Thread[3];
		for(int i = 0;i < 3;i++){
			thread[i] = new Thread(new TestThread(i));
		}
		for(int i = 0;i < 3;i++){
			thread[i].start();
		}

	}
	public static class TestThread implements Runnable{
		int id;
		public TestThread(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ":start");
			Integer s = threadLocal.get();
			s += id;
			threadLocal.set(s);
			System.out.println(Thread.currentThread().getName() + "：value：" + threadLocal.get());
			//threadLocal.remove();清除，非必须，有gc管理
		}
	}

	public static void main(String[] args) {
		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		threadLocalTest.startThreadArray();
	}
}
