package com.dew.godx.other.threadpool.myself;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用
 */
public class UseThreadPool {
	static class Worker implements Runnable {

		private String taskName;
		private Random r = new Random();

		/**
		 * @return taskName
		 */
		public String getTaskName() {
			return taskName;
		}

		public Worker(String taskName){
			this.taskName = taskName;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " process the task... " + taskName);
			try {
				Thread.sleep(r.nextInt(100) * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class CallWorker implements Callable {

		private String taskName;
		private Random r = new Random();

		/**
		 * @return taskName
		 */
		public String getTaskName() {
			return taskName;
		}

		public CallWorker(String taskName){
			this.taskName = taskName;
		}

		@Override
		public Object call() throws Exception {
			System.out.println(Thread.currentThread().getName() + " process the task...call " + taskName);
			Thread.currentThread().sleep(10000);
			return Thread.currentThread().getName() + " : " + r.nextInt(10000) * 5;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
//		ExecutorService pool = new ThreadPoolExecutor(2,4,3, TimeUnit.SECONDS,
//				new ArrayBlockingQueue<Runnable>(10),new ThreadPoolExecutor.DiscardPolicy());
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i = 0;i < 6;i++){
			Worker worker = new Worker("work_" + i);
			pool.execute(worker);
		}
		Future future[] = new Future[6];
		for(int i = 0;i < 6;i++){
			CallWorker worker = new CallWorker("work_" + i);
			future[i] = pool.submit(worker);
			//System.out.println(submit.get());
		}
		for(int i = 0;i < 6;i++){
			System.out.println(future[i].get());
		}
		pool.shutdown();
	}
}
