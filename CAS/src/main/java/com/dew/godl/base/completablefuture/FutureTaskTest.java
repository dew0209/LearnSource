package com.dew.godl.base.completablefuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author LvLu
 * @className FutureTaskTest
 * @date 2023-02-02 21:14
 * @description
 */
public class FutureTaskTest {
	public static void main(String[] args) throws Exception{
		//模拟3个任务
		long st = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(3);
		FutureTask<String> futureTask1 = new FutureTask<>(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (Exception e) {
			}
			return "task1 over";
		});
		FutureTask<String> futureTask2 = new FutureTask<>(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (Exception e) {
			}
			return "task1 over";
		});
		//线程池-线程复用
		pool.submit(futureTask1);
		pool.submit(futureTask2);
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (Exception e) {
		}
		System.out.println(futureTask1.get());
		System.out.println(futureTask2.get());
		long ed = System.currentTimeMillis();
		System.out.println("costTime----" + (ed - st) + " 毫秒");
		System.out.println(Thread.currentThread().getName() + "  end");
		pool.shutdown();
	}
	//传统方式
	public static void m1(){
		long st = System.currentTimeMillis();
		try {TimeUnit.MILLISECONDS.sleep(500);}catch (Exception e){}
		try {TimeUnit.MILLISECONDS.sleep(300);}catch (Exception e){}
		try {TimeUnit.MILLISECONDS.sleep(300);}catch (Exception e){}
		long ed = System.currentTimeMillis();
		System.out.println("costTime----" + (ed - st) + " 毫秒");
		System.out.println(Thread.currentThread().getName() + "  end");
	}
}
