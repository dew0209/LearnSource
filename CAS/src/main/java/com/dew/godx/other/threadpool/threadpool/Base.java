package com.dew.godx.other.threadpool.threadpool;

import java.util.concurrent.Executors;

/**
 * FixedThreadPool：
 * 		创建固定线程数量的，适用于负载较重的服务器，使用了无界队列(Integer.MAX_VALUE)
 * SingleThreadExecutor：
 * 		创建单个线程，适用于需要顺序保证任务执行，不会有多个线程活动，使用了无界队列(Integer.MAX_VALUE)
 * CachedThreadPool：
 * 		会根据需要来创建新线程，执行很多短期异步任务的程序，使用了SynchronousQueue，每一个put都需要等待一个take操作
 * WorkStealingPool：
 * 		ForkJoinPool
 *
 */
public class Base {
	public static void main(String[] args) {

	}
}
