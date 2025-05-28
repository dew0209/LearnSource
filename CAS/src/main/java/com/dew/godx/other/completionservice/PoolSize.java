package com.dew.godx.other.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * @author LvLu
 * @className PoolSize
 * @date 2022-11-03 18:49
 * @description
 */
public class PoolSize {
	private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
	private final int TOTAL_TASK = Runtime.getRuntime().availableProcessors() * 10;
	// way 1：自己写集合来实现获取线程池中任务的返回结果
	public void testByQueue() throws Exception{
		long start = System.currentTimeMillis();
		AtomicInteger count = new AtomicInteger(0);
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
		LinkedBlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();
		//向里面仍任务
		for(int i = 0;i < TOTAL_TASK;i++){
			Future<Integer> future = pool.submit(new WorkTask("ExecTask" + i));
			queue.add(future);//i = 0的任务先进，依次类推...
		}
		//检查线程池任务执行结果
		for(int i = 0;i < TOTAL_TASK;i++){
			int sleepTime = queue.take().get();//i = 0的任务先拿，依次类推...拿结果只能按照放的顺序
			System.out.println(" slept " + sleepTime + " ms ");
			count.addAndGet(sleepTime);
		}
		pool.shutdown();
		System.out.println("-----task sleep time " + count.get() + " ms,and spend time " + (System.currentTimeMillis() - start) + " ms");
	}
	//先完成的先拿到结果
	// way 2：使用CompletionService
	public void testByCompletion() throws Exception {
		long start = System.currentTimeMillis();
		AtomicInteger count = new AtomicInteger(0);
		ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
		CompletionService<Integer> cService = new ExecutorCompletionService<Integer>(pool);
		//向里面仍任务
		for(int i = 0;i < TOTAL_TASK;i++){
			cService.submit(new WorkTask("ExecTask" + i));
		}
		for(int i = 0;i < TOTAL_TASK;i++){
			int sleptTime = cService.take().get();
			System.out.println(" slept " + sleptTime + " ms");
			count.addAndGet(sleptTime);
		}
		pool.shutdown();
		System.out.println("------tasks sleep time " + count.get() + " ms,and spend time " + (System.currentTimeMillis() - start) + " ms");

	}

	public static void main(String[] args) throws Exception{
		PoolSize t = new PoolSize();
		t.testByQueue();
		t.testByCompletion();
	}
}
