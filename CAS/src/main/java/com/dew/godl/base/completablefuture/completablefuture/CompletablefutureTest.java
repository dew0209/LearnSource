package com.dew.godl.base.completablefuture.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ”“
 *
 * @author LvLu
 * @className CompletablefutureTest
 * @date 2023-02-08 13:32
 * @description
 */
public class CompletablefutureTest {
	public static void main(String[] args) throws Exception{
		//不推荐使用new的方式创建对象
		//CompletableFuture<Object> completableFuture = new CompletableFuture<>();
		/**
		 * 推荐的方式：
		 * 	1.runAsync无返回值
		 *		runAsync(Runnable runnable)
		 *		runAsync(Runnable runnable,Executor executor)
		 * 	2.supplyAsync有返回值
		 * 		supplyAsync(Supplier<U> supplier)
		 * 		supplyAsync(Supplier<U> supplier,Executor executor)
		 * Executor executor说明：
		 * 		没有指定Executor的方法，直接使用默认的ForkJoinPool.commonPool()作为它的线程执行异步代码
		 * 		如果指定线程池，则使用我们自定义的或者特别指定的线程池执行异步代码
		 **/
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//			System.out.println(Thread.currentThread().getName());
//			try {TimeUnit.MILLISECONDS.sleep(500);}catch (Exception e){}
//		},scheduledExecutorService);
//		System.out.println(future.get());
		CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
			System.out.println(Thread.currentThread().getName());
			try {TimeUnit.MILLISECONDS.sleep(500);}catch (Exception e){}
			return "hello";
		},scheduledExecutorService);
		System.out.println(future.get());
		scheduledExecutorService.shutdown();
	}
}
