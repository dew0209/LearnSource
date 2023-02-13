package com.dew.godl.base.completablefuture.method.m2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className M2
 * @date 2023-02-13 21:47
 * @description
 */
public class M2 {
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		/*CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (Exception e) {
			}
			return 1;
		},pool).thenApply(f->{
			System.out.println("2222");
			int i = 10 / 0;
			return f + 2;
		}).thenApply(f->{
			System.out.println("3333");
			return f + 3;
		}).whenComplete((v,e)->{
			if(e == null){
				System.out.println("计算结果：" + v);
			}
		}).exceptionally(e->{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		});*/
		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (Exception e) {
			}
			return 1;
		},pool).handle((f,e)->{
			System.out.println("2222");
			int i = 10 / 0;
			return f + 2;
		}).handle((f,e)->{
			System.out.println("3333");
			return f + 3;
		}).whenComplete((v,e)->{
			if(e == null){
				System.out.println("计算结果：" + v);
			}
		}).exceptionally(e->{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		});
		System.out.println(Thread.currentThread().getName() + "---先去忙其他任务");
		pool.shutdown();
	}
}
