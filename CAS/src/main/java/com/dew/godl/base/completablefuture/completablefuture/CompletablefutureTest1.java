package com.dew.godl.base.completablefuture.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class CompletablefutureTest1 {
	public static void main(String[] args) throws Exception{
		/**
		 * 优点：
		 * 		1.异步任务结束时，会自动回调某个对象的方法
		 * 		2.主线程设置好回调后，不再关心异步任务的执行，异步任务之间可以顺序执行
		 * 		3.异步任务出错时，会自动回调某个对象的方法
		 **/
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
		try {
			CompletableFuture.supplyAsync(()->{
				System.out.println(Thread.currentThread().getName() + "----come in");
				int result = ThreadLocalRandom.current().nextInt(10);
				if(result > 5){
					int i = 10 / 0;
				}
				try {TimeUnit.MILLISECONDS.sleep(2000);}catch (Exception e){}
				return result;
			},executorService).whenComplete((v,e)->{
				if (e == null) {
					System.out.println("----计算完成,更新系统UpdateVal：" + v);
				}
			}).exceptionally(e -> {
				e.printStackTrace();
				System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
				return null;
			});
			System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
		}catch (Exception e){

		}finally {
			executorService.shutdown();
		}
		//主线程不要立刻结束，否则CompletableFuture使用的默认线程池会立刻关闭
		//try {TimeUnit.MILLISECONDS.sleep(30000);}catch (Exception e){}
	}
	public static void future1() throws Exception{
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
			System.out.println(Thread.currentThread().getName() + "----come in");
			int result = ThreadLocalRandom.current().nextInt(10);
			try {TimeUnit.MILLISECONDS.sleep(1000);}catch (Exception e){}
			return result;
		});
		System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
		System.out.println(future.get());
	}
}
