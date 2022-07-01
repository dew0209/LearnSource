package com.dew.godx.base.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class DBPoolTest {
	static DBPool pool = new DBPool(10);
	static CountDownLatch end;

	public static void main(String[] args) throws InterruptedException {
		int threadCount = 50;//线程数量
		end = new CountDownLatch(threadCount);
		int count = 20;//每个线程的操作次数
		AtomicInteger got = new AtomicInteger();//统计拿到线程的连接
		AtomicInteger notGot = new AtomicInteger();//统计没有拿到线程的连接
		for(int i = 0;i < threadCount;i++){
			Thread thread = new Thread(new Worker(count,got,notGot),"worker_" + i);
			thread.start();
		}
		end.await();//main线程在此等待
		System.out.println("总共尝试了：" + threadCount * count);
		System.out.println("拿到连接的次数：" + got);
		System.out.println("没能拿到连接的次数：" + notGot);
	}

	static class Worker implements Runnable{
		int count;
		AtomicInteger got;
		AtomicInteger notGot;
		public Worker(int count,AtomicInteger got,AtomicInteger notGot){
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}
		@Override
		public void run() {
			while (count > 0){
				try {
					Connection connection = pool.fetchConn(1000);
					if(connection != null){
						try {
							connection.createStatement();
							connection.commit();

						}finally {
							pool.releaseConn(connection);
							got.incrementAndGet();
						}
					}else {
						notGot.incrementAndGet();
						System.out.println(Thread.currentThread().getName() + "等待超时");
					}
				}catch (Exception e){

				}finally {
					count--;
				}
			}
			end.countDown();
		}
	}

}
