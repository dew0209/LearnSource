package com.dew.godl.base.completablefuture.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Case
 * @date 2023-02-08 14:48
 * @description
 */
public class Case {
	/**
	 * Lambda表达式+stream流式调用+chain链式调用+java8函数式编程
	 * 		函数式接口名称		方法名称		参数		返回值
	 * 		Runnable			run				无参数		无返回值
	 * 		Function			apply			1个参数		有返回值
	 * 		Consume				accept			1个参数		无返回值
	 * 		supplier			get				没有参数	有返回值
	 * 		BiConsumer			accept			2个参数		无返回值
	 **/
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		new Student().setId(1).setMajor("嘿嘿").setMajor("cs");
		CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
			return "hello 1234";
		});
		//抛出异常
		//System.out.println(future.get());
		//不会抛出异常
		System.out.println(future.join());

	}
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student{
	private Integer id;
	private String studentName;
	private String major;
}
