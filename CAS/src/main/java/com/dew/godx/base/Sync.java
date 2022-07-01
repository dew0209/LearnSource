package com.dew.godx.base;

import org.junit.Test;

/**
 * synchronized内置锁：锁的是对象
 * 		对象锁 锁的是当前对象 this
 * 		类锁  static关键字修饰的方法 类对象
 */
//演示对象锁和类锁
public class Sync {
	//使用类锁的线程
	private static class SynClass extends Thread{
		@Override
		public void run() {
			System.out.println("TestClass is Running...");
			try {
				syncClass();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//使用对象锁的线程
	private static class InstanceSyn implements Runnable{
		private Sync sync;

		public InstanceSyn(Sync sync) {
			this.sync = sync;
		}

		@Override
		public void run() {
			System.out.println("TestInstance is Running...");
			try {
				sync.instance();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//使用对象锁的线程
	private static class Instance2Syn implements Runnable{
		private Sync sync;

		public Instance2Syn(Sync sync) {
			this.sync = sync;
		}

		@Override
		public void run() {
			System.out.println("TestInstance is Running...");
			try {
				sync.instance2();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void instance() throws InterruptedException {
		Thread.currentThread().sleep(3000);
		System.out.println("syncInstance is going ..." + this.toString());
		Thread.currentThread().sleep(3000);
		System.out.println("syncInstance is ended " + this.toString());
	}

	private synchronized void instance2() throws InterruptedException {
		Thread.currentThread().sleep(3000);
		System.out.println("syncInstance2 is going ..." + this.toString());
		Thread.currentThread().sleep(3000);
		System.out.println("syncInstance2 is ended " + this.toString());
	}

	private static synchronized void syncClass() throws InterruptedException {
		Thread.currentThread().sleep(3000);
		System.out.println("syncClass is going ...");
		Thread.currentThread().sleep(3000);
		System.out.println("syncClass is ended ");
	}
	//锁对象  this
	@Test
	public void testInstance() throws InterruptedException {
		Sync sync = new Sync();
		Sync sync1 = new Sync();
		Thread t1 = new Thread(new InstanceSyn(sync));
		//Thread t2 = new Thread(new Instance2Syn(sync));
		Thread t2 = new Thread(new Instance2Syn(sync1));
		//加入类锁 和 对象锁 互不影响
		SynClass synClass = new SynClass();
		synClass.start();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		synClass.join();


	}
	//类锁  锁的是每个类的class对象
	@Test
	public void testClass() throws InterruptedException{
		SynClass synClass = new SynClass();
		synClass.start();
		synClass.join();
	}


}
