package com.dew.godx.other.threadpool.myself;

import java.util.Random;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className TestMySelfThread
 * @date 2022-11-02 19:13
 * @description
 */
public class TestMySelfThread {
	public static void main(String[] args) throws InterruptedException {
		//创建3个线程类
		MySelfThreadPool pool = new MySelfThreadPool();
		pool.execute(new MyTask("testA"));
		pool.execute(new MyTask("testB"));
		pool.execute(new MyTask("testC"));
		pool.execute(new MyTask("testD"));
		pool.execute(new MyTask("testE"));
		pool.execute(new MyTask("testF"));
		Thread.sleep(10000);
		pool.destroy();
	}
	static class MyTask implements Runnable{

		private String name;
		private Random r = new Random();

		public MyTask(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(r.nextInt(1000) + 2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getId() + " 被中断 " + Thread.currentThread().isInterrupted());
			}
			System.out.println("任务：" + name + " 完成");
		}

		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}
	}
}
