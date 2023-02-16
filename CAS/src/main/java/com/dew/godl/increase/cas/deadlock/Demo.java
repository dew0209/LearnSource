package com.dew.godl.increase.cas.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Demo
 * @date 2023-02-16 21:01
 * @description
 */
public class Demo {
	public static void main(String[] args) {
		final Object objectA = new Object();
		final Object objectB = new Object();
		new Thread(()->{
			synchronized (objectA){
				System.out.println(Thread.currentThread().getName()+"\t 持有锁A，希望获取锁B");
				try{ TimeUnit.SECONDS.sleep(1);}catch (Exception e){}
				synchronized (objectB){
					System.out.println(Thread.currentThread().getName()+"\t 持有锁A，已经获取锁B");
				}
			}
		},"A").start();

		new Thread(()->{
			synchronized (objectB){
	 			System.out.println(Thread.currentThread().getName()+"\t 持有锁B，希望获取锁A");
				try{ TimeUnit.SECONDS.sleep(1);}catch (Exception e){}
				synchronized (objectA){
					System.out.println(Thread.currentThread().getName()+"\t 持有锁B，已经获取锁A");
				}
			}
		},"B").start();

	}
}
