package com.dew.godl.base.completablefuture.other;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-14 16:56
 * @description
 */
public class Main {
	public static void main(String[] args) {
		//以thenRun和thenRunAsync为例，讲述加Async的作用
		ExecutorService pool = Executors.newFixedThreadPool(5);
		try {
			CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (Exception e) {
				}
				System.out.println("1号任务。" + Thread.currentThread().getName());
				return "abcd";
			},pool).thenRunAsync(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (Exception e) {
				}
				System.out.println("2号任务。" + Thread.currentThread().getName());
			}).thenRunAsync(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (Exception e) {
				}
				System.out.println("3号任务。" + Thread.currentThread().getName());
			}).thenRunAsync(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (Exception e) {
				}
				System.out.println("4号任务。" + Thread.currentThread().getName());
			});
			Void re = future.get(2, TimeUnit.SECONDS);
			System.out.println(re);

		}catch (Exception e){

		}finally {
			pool.shutdown();
		}
	}
}
