package com.dew.godx.base;

/**
 * join()：a,b两个线程，在a线程里面b.join(),那么a线程必须等待b线程执行完毕才能继续执行。
 *
 *
 */
public class JoinTest {
	//join
	static class JumpQueue implements Runnable{
		private Thread thread;

		public JumpQueue(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---end");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread per = Thread.currentThread();
		for(int i = 0;i < 10;i++){
			Thread thread = new Thread(new JumpQueue(per),String.valueOf(i));
			System.out.println(per.getName() + " jump a queue the thread " + thread.getName());
			thread.start();
			per = thread;
		}
		Thread.currentThread().sleep(2000);
		System.out.println(Thread.currentThread().getName() + "---end");
	}




}
