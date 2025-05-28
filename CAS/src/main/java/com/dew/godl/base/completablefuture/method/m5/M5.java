package com.dew.godl.base.completablefuture.method.m5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ”“
 *
 * @author LvLu
 * @className M5
 * @date 2023-02-14 17:22
 * @description
 */
public class M5 {
	//对计算结果进行合并
	public static void main(String[] args) {
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "\t启动");
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (Exception e) {
			}
			return 10;
		});
		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "\t启动");
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (Exception e) {
			}
			return 20;
		});
		CompletableFuture<Object> res = future1.thenCombine(future2,(x,y)->{
			System.out.println("开始合并");
			return x + y;
		});
		System.out.println(res.join());
	}
}
