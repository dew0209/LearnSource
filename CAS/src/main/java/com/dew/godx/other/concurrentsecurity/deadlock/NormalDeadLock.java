package com.dew.godx.other.concurrentsecurity.deadlock;

/**
 *
 *
 * @author LvLu
 * @className NormalDeadLock
 * @date 2022-11-03 20:13
 * @description 简单的死锁
 * 使用jps -m 和 jstack xxxx可以分析死锁
 */
public class NormalDeadLock {
	//第一个锁
	private static Object valueFirst = new Object();
	//第二个锁
	private static Object valueSecond = new Object();
	//先拿第一个，再拿第二个
	private static void firstToSecond() throws Exception{
		String threadName = Thread.currentThread().getName();
		synchronized (valueFirst){
			System.out.println(threadName + "---getFirst");
			Thread.sleep(100);
			synchronized (valueSecond){
				System.out.println(threadName + "---getSecond");
			}

		}
	}
	//先拿第二个，再拿第一个
	private static void SecondToFirst() throws Exception{
		String threadName = Thread.currentThread().getName();
		synchronized (valueSecond){
			System.out.println(threadName + "---getSecond");
			Thread.sleep(100);
			synchronized (valueFirst){
				System.out.println(threadName + "---getFirst");
			}

		}
	}
	private static class TestThread extends  Thread {

		private String name;

		public TestThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			Thread.currentThread().setName(name);
			try {
				SecondToFirst();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread.currentThread().setName("TestDeadLock");
		TestThread t = new TestThread("SubTestThread");
		t.start();
		try {
			firstToSecond();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
