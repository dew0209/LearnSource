package com.dew.godx.increase.semaphore;

import java.sql.Connection;
import java.util.Random;

/**
 * Semaphore:
 *		控制同时访问某个特定资源的线程数量，用在流量控制
 */
public class SemaphoreTest {
	private static DBPoolSemaphore dbPool = new DBPoolSemaphore();
	private static class BusiThread extends Thread {
		@Override
		public void run() {
			Random r = new Random();//让每一个线程持有连接的时间不一样
			long start = System.currentTimeMillis();
			try {
				Connection connection = dbPool.takeConnect();
				System.out.println(Thread.currentThread().getId() + "_获取数据库连接共耗时[" + (System.currentTimeMillis() - start) + "] ms");
				Thread.currentThread().sleep(100 + r.nextInt(100));//模拟业务操作，线程持有连接查询数据
				System.out.println("查询数据完成，归还连接");
				dbPool.returnConnect(connection);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for(int i = 0;i < 50;i++){
			Thread thread = new BusiThread();
			thread.start();
		}
	}
}
