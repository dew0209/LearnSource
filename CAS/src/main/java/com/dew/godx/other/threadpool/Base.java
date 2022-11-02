package com.dew.godx.other.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池：
 * 		保存线程的，降低资源的消耗，重复利用已经创建的线程，降低线程创建和销毁的资源消耗
 * 		提高响应速度：线程的创建时间为T1，执行时间为T2，销毁时间为T3，免去T1和T3的时间
 * 		提高线程的可管理性。
 * 实现自己的线程池：
 * 		1.线程必须在池子已经创建好了，并且可以保持住（重复利用），要有容器保存多个线程
 * 		2.线程还要能接收外部的任务，运行这个任务
 * 		3.
 * 		4.缺点，当任务超出队列长度时，一直等待，没有超时机制等等
 * ThreadPoolExecutor：最基本的
 * 		构造函数参数：
 * 			int corePoolSize：线程池中的核心线程数，< corePoolSize创建新的线程，= corePoolSize 这个任务就会保存到 BlockingQueue。
 * 							  也可以手动调用prestartAllCoreThreads()会一次性启动corePoolSize个线程
 *          int maximumPoolSize：允许的最大线程数，BlockingQueue也满了，< maximumPoolSize的时候，就会再次创建新的线程
 *          long keepAliveTime：线程空闲下来的时候，存活的时间，这个参数只有在 > corePoolSize的时候才有用
 *          TimeUnit unit：存活时间单位值
 *          BlockingQueue<Runnable> workQueue：保存任务的阻塞队列
 *          ThreadFactory threadFactory：创建线程的工厂，给新建的线程赋予名字
 *          RejectedExecutionHandler handler：maximumPoolSize workQueue 都满了之后，饱和策略
 *          	四种饱和策略：
 *          		AbortPolicy：直接抛出异常，默认
 *          		CallerRunsPolicy；用调用者所在的线程来执行任务
 *          		DiscardOldestPolicy：丢弃阻塞队列里面最老的任务，也就是队列里面最靠前的任务
 *          		DiscardPolicy：简单的丢弃，把当前任务直接丢弃
 *      提交任务
 *			execute(Runnable command)：无返回值
 *			submit(Callable<T> task)：有返回值
 * 		关闭线程池
 * 			shutdown()：设置线程状态，只会中断还没有执行任务的线程，线程正在执行任务不会中断的
 * 			shutdownNow()：设置线程池的状态（表示关闭），还会尝试停止正在运行或者暂定任务的线程
 * 		合理配置线程池：
 * 			根据任务的性质来：
 * 				计算密集型（CPU）：只利用cpu和内存。加密，大数分解，正则.......，线程数量适当小一点，建议为cpu核心数+1，为什么+1，防止页缺失。获得核心数：Runtime.getRuntime().availableProcessors()
 * 				IO密集型：读取文件，数据库链接，网络通讯......，线程数适量大一点，推荐核心数 * 2
 * 				混合型：尽量拆分，IO密集型 >> 计算密集型 拆分意义不大，IO密集型 ≈ 计算密集型 非常nice
 * 			队列的选择上，应该使用有界。无界的，会导致内存溢出（任务把内存塞爆炸了）
 */
public class Base {
	public static void main(String[] args) {

	}
}
