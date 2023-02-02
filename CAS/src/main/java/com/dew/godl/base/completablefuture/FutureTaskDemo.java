package com.dew.godl.base.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className FutureTaskDemo
 * @date 2023-02-02 21:26
 * @description
 */
public class FutureTaskDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		FutureTask<String> futureTask = new FutureTask<String>(() -> {
			System.out.println(Thread.currentThread().getName() + "\t come in...");
			try {TimeUnit.SECONDS.sleep(5);}catch (Exception e){}
			return "task over";
		});
		Thread t1 = new Thread(futureTask, "t1");
		t1.start();
		//System.out.println(Thread.currentThread().getName() + "\t 忙其他任务了");
		//1.和上面的模拟主线程任务交换，容易阻塞，所以get一般放到最后。其实我个人觉得这不是缺点（与具体业务实现有关），可能是一个可以使用其他方案进行优化的问题
		//补充，get有一个超时机制
		//System.out.println(futureTask.get(1,TimeUnit.SECONDS));
		//isDone轮询，但是会耗费cpu资源
		while (true){
			if(futureTask.isDone()){
				System.out.println(futureTask.get());
				break;
			}else {
				//暂停
				try {TimeUnit.MILLISECONDS.sleep(1000);}catch (Exception e){}
				System.out.println("正在处理中，不要再催了！");
			}
		}
	}
}
