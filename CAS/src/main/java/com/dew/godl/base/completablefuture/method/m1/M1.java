package com.dew.godl.base.completablefuture.method.m1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ”“
 *
 * @author LvLu
 * @className M1
 * @date 2023-02-13 20:45
 * @description
 */
public class M1 {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (Exception e) {
			}
			return "abc";
		});
		//System.out.println(completableFuture.get());
		//System.out.println(completableFuture.get(2L,TimeUnit.MILLISECONDS));
		//System.out.println(completableFuture.join());//不抛出异常
		//System.out.println(completableFuture.getNow("xxx"));//立即获得结果，获得不到就返回valueIfAbsent  立即获得结果不阻塞
		System.out.println(completableFuture.complete("ddd") + " --- " + completableFuture.join());
	}
}
