package com.dew.godl.base.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 *
 * @author LvLu
 * @className CompletetableFutureTest
 * @date 2023-02-02 9:18
 * @description
 */
public class CompletableFutureTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<String> futureTask = new FutureTask<String>(new MyThread2());
		Thread t1 = new Thread(futureTask,"t1");
		t1.start();
		String res = futureTask.get();
		System.out.println(res);
	}
}
//无返回 无异常抛出
class MyThread implements Runnable{
	@Override
	public void run() {

	}
}
//有返回 抛出异常
class MyThread2 implements Callable<String>{
	@Override
	public String call() throws Exception {
		System.out.println("come in...");
		return "Hello";
	}
}
