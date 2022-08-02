package com.dew.godx.increase.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Future接口
 * 		isDone(): 结束，正常还是异常结束，或者自己取消，返回true
 * 	 	isCanceled()：任务完成前被取消，返回true，其他情况返回false
 * 	 	cancel()：尝试去中止任务，任务没开始，返回false，任务已经移动，cancel(true)会尝试中断正在运行的任务，中断成功返回true，cancel(false)不会去中断已经运行的任务。任务已经结束了，直接返回false
 *
 */
public class CallableTest {
	private static class UseCallable implements Callable<Integer>{
		private int sum;

		@Override
		public Integer call() throws Exception {
			System.out.println("Callable子线程开始计算");
			Thread.currentThread().sleep(2000);
			for(int i = 0;i < 5000;i++){
				sum += i;
			}
			return sum;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		UseCallable useCallable = new UseCallable();
		FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
		Thread thread = new Thread(futureTask);
		thread.start();
		Random r = new Random();
		Thread.currentThread().sleep(1000);
		if(r.nextBoolean()){
			System.out.println("Get UseCallable result = " + futureTask.get());
		}else {
			futureTask.cancel(true);
			System.out.println("中断计算");
			System.out.println(futureTask.get());//抛出异常CancellationException
		}
	}
}
