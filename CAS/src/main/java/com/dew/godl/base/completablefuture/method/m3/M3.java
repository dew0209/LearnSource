package com.dew.godl.base.completablefuture.method.m3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className M3
 * @date 2023-02-13 22:02
 * @description
 */
public class M3 {
	public static void main(String[] args) {
		//对计算结果进行消费，接受任务的处理结果，并消费处理，无返回结果
		/*CompletableFuture.supplyAsync(() -> {
			return 1;
		}).thenApply(f -> {
			return f + 2;
		}).thenApply(f -> {
			return f + 3;
		}).thenAccept(r -> {
			System.out.println(r);
		});*/
		System.out.println(CompletableFuture.supplyAsync(() -> {
			return "resultA";
		}).thenRun(() -> {
		}).join());//null
		System.out.println(CompletableFuture.supplyAsync(() -> {
			return "resultA";
		}).thenAccept(f -> {
			System.out.println(f);//resultA
		}).join());//null
		System.out.println(CompletableFuture.supplyAsync(() -> {
			return "resultA";
		}).thenApply(f -> f + "resultB"
		).join());//resultAresultB
	}
}
