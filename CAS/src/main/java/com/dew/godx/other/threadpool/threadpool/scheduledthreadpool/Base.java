package com.dew.godx.other.threadpool.threadpool.scheduledthreadpool;

import java.util.concurrent.Executors;

/**
 * 需求定期执行周期任务
 *		SingleThreadScheduledExecutor：只包含一个线程，只需要单个线程周期执行任务，保证顺序的执行各个任务
 *		ScheduledThreadPool：可以包含多个线程，线程执行周期任务，适度控制后台线程数量的时候。
 *	方法：
 *		schedule：只执行一次，任务还可以延时执行
 *		scheduleAtFixedRate：提交固定时间间隔的任务。当前开始和下次开始间隔
 *			任务超时：规定60s一次，有任务执行了80s，下个任务马上开始执行
 *					第一个任务80s 第二个任务20s 第三个任务50s
 *					第一个任务0s开始，80s结束
 *					第二个任务80s开始，100s结束
 *					第三个任务120s开始，170s结束
 *		scheduleWithFixedDelay：提交固定延时间隔的任务。当前结束和下次开始间隔
 *			则没有这个任务超时的机制，毕竟是按照结束时间来计算的
 * 建议在提交给ThreadScheduledExecutor的任务要catch住,因为异常发生了，就会停住不执行
 * 线程池的类图：
 * 		从顶级Executor接口开始延申
 */
public class Base {
	public static void main(String[] args) {

	}
}
