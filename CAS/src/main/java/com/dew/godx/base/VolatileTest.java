package com.dew.godx.base;

import org.junit.Test;

/**
 * volatile最轻量的同步机制
 * 		不能保证原子性，只能保证可见性
 * 	用法：一写多读使用，能够保证安全
 */
public class VolatileTest {
	private static class VolatileVar implements Runnable{
		private volatile int a = 0;
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			a = a + 1;
			System.out.println(threadName + ":======:" + a);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			a = a + 1;
			System.out.println(threadName + ":======:" + a);
		}
	}
	@Test
	public void testVolatile() throws Exception{
		VolatileVar v1 = new VolatileVar();
		Thread t1 = new Thread(v1);
		Thread t2 = new Thread(v1);
		Thread t3 = new Thread(v1);
		Thread t4 = new Thread(v1);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}
}
