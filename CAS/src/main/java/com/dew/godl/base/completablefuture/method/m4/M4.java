package com.dew.godl.base.completablefuture.method.m4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className M4
 * @date 2023-02-14 17:16
 * @description
 */
public class M4 {
	public static void main(String[] args) {
		//对计算速度进行选用
		CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
			System.out.println("A come in!");
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (Exception e) {
			}
			return "playA";
		});

		CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
			System.out.println("B come in!");
			try {
				TimeUnit.MILLISECONDS.sleep(3000);
			} catch (Exception e) {
			}
			return "playB";
		});
		CompletableFuture<String> res = playA.applyToEither(playB, f -> {
			return f + "is winer";
		});
		System.out.println(res.join());//playAis winer
	}
}
