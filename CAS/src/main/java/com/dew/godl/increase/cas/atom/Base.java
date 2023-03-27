package com.dew.godl.increase.cas.atom;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基本类型原子类：
 * 		AtomicInteger
 * 		AtomicLong
 * 		AtomicBoolean
 * 常用api:
 * 		final int get() 获取当前的值
 * 		final int getAndSet(int newValue) 获取当前的值，并设置新值
 * 		final int getAndIncrement() 获取当前值，并自增
 * 		final int getAndDecrement() 获取当前值，并自减
 * 		final int getAndAdd(int delta) 获取当前值，并加上预期的值
 * 		boolean compareAndSet(int expect,int update) 如果内存地址所存储的数值等于预期值，则以原子方式将该值设置为输入值update
 *
 */
public class Base {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch lo = new CountDownLatch(50);
		MyNumber myNumber = new MyNumber();
		for(int i = 0;i < 50;i++){
			new Thread(()->{
				try {
					for(int j = 0;j < 1000;j++){
						myNumber.addPlus();
					}
				}catch (Exception e){

				}finally {
					lo.countDown();
				}
			},String.valueOf(i)).start();
		}
		lo.await();
		System.out.println(Thread.currentThread().getName() + "\t" + myNumber.atomicInteger.get());
	}
}
class MyNumber{
	AtomicInteger atomicInteger = new AtomicInteger();
	public void addPlus(){
		atomicInteger.getAndIncrement();
	}
}