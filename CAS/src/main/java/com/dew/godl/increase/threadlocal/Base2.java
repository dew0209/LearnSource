package com.dew.godl.increase.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Base2
 * @date 2023-05-04 7:22
 * @description
 */
public class Base2 {

	public static void main(String[] args) {
		MyData myData = new MyData();
		ExecutorService pool = Executors.newFixedThreadPool(3);
		try {
			for(int i = 0;i < 10;i++){
				pool.submit(()->{
					try {
						Integer before = myData.threadLocal.get();
						myData.add();
						Integer after = myData.threadLocal.get();
						//线程复用的情况，数据混乱了。需要再finally中进行remove。
						System.out.println(before + "=====" + after);
					}catch (Exception e){

					}finally {
						myData.threadLocal.remove();
					}
				});
			}
		}catch (Exception e){

		}finally {
			pool.shutdown();
		}
	}



}
class MyData{
	ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
	public void add(){
		threadLocal.set(threadLocal.get() + 1);
	}
}