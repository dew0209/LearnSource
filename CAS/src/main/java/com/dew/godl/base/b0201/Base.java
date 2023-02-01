package com.dew.godl.base.b0201;


/**
 *
 * juc即对 java.util.concurrent包下类的使用
 * 使用多线程的必要性：充分压榨cpu资源，充分利用多核处理器，cpu开始采用多核而不是主频。由此联想到并行或并发编程
 *
 *
 *
 **/
public class Base {
	public static void main(String[] args) {
		Thread t1 = new Thread(()->{

		},"t1");
		t1.start();
		t1.isDaemon();
	}
}
