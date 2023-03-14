package com.dew.godl.increase.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * 不加volatile程序永远不会停止，保证可见性
 * 	read（读取）->load（加载）->use（使用）->assign（赋值）->store（存储）->write（写入）->lock（锁定）->unlock（解锁）
 * 	加锁后会清空工作内存变量的值在使用前必须要重新load或assign
 * 	read：作用于主内存，将变量的值从主内存传输到工作内存，主内存到工作内存
 * 	load：作用于工作内存，将read从主内存传输的变量放入工作内存变量副本中，即数据加载
 * 	use：作用于工作内存，将工作内存变量副本的值传递给执行引擎，每当jvm遇到该变量的字节码指令时会执行该操作
 * 	assign：作用于工作内存，将从执行引擎接收到的值赋值给工作内存变量，每当jvm遇到一个给变量赋值字节码指令时会执行该操作
 * 	store：作用于工作内存，将赋值完毕的工作变量的值写回到主内存
 * 	write：作用于主内存，将store传输过来的变量赋值给主内存中的变量
 * 	以上六条只能保证单条指令的原子性，针对多条指令的组合性原子保证，没有大面积加锁，所以，jvm提供了另外两个原子指令
 * 	lock：作用于主内存，将一个变量标记为一个线程独占的状态，只是写时候加锁，就只是锁了写变量的过程
 * 	unlock：作用于主内存，把一个处于锁定状态的变量释放，然后才能被其他线程占用
 */
public class Demo {
	static volatile boolean flag = true;
	public static void main(String[] args) {
		new Thread(()->{
			System.out.println(Thread.currentThread().getName() + "----come in");
			while(flag){

			}
			System.out.println(Thread.currentThread().getName() + "----flag 被设置为false，程序停止");
		},"t1").start();
		try{
			TimeUnit.SECONDS.sleep(2);
		}catch (Exception e){}
		flag = false;
		System.out.println(Thread.currentThread().getName() + "----修改完成");
	}
}
