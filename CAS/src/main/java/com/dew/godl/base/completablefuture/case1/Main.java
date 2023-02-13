package com.dew.godl.base.completablefuture.case1;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-08 15:12
 * @description
 */
public class Main {

	static List<NetMall> list = Arrays.asList(new NetMall("jd"),new NetMall("dangdang"),new NetMall("taobao"));
	//传统的方式
	public static List<String> getPrice(List<NetMall> list, String productName){
		return list
				.stream()
				.map(netMall -> String.format(productName + " in %s price %.2f", netMall.getNetMallName(), netMall.calcPrice(productName)))
				.collect(Collectors.toList());
	}

	public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName){
		return list
				.stream()
				.map(netMall -> CompletableFuture.supplyAsync(()->String.format(productName + " in %s price %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))))
				.collect(Collectors.toList())
				.stream()
				.map(s->s.join())
				.collect(Collectors.toList());
	}

	/**
	 * 功能->性能
	 * 电商网站比价需求分析
	 * 	1.需求说明
	 * 		1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价
	 * 		1.2	同一款产品，同时搜索出本产品在用一个电商下，各个入驻卖家售价多少
	 **/
	public static void main(String[] args) {
		long st = System.currentTimeMillis();
		List<String> res = getPrice(list, "mysql");
		for (String re : res) {
			System.out.println(re);
		}
		long ed = System.currentTimeMillis();
		System.out.println("---- costTime: " + (ed - st) + " ms");
		st = System.currentTimeMillis();
		List<String> ans = getPriceByCompletableFuture(list, "mysql");
		for (String re : ans) {
			System.out.println(re);
		}
		ed = System.currentTimeMillis();
		System.out.println("---- costTime: " + (ed - st) + " ms");
	}
}
class NetMall{
	@Getter
	private String netMallName;

	public NetMall(String netMallName) {
		this.netMallName = netMallName;
	}

	public double calcPrice(String productName){
		try {TimeUnit.MILLISECONDS.sleep(1000);}catch (Exception e){}
		return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
	}
}