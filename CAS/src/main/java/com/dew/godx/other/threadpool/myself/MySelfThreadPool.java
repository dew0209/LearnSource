package com.dew.godx.other.threadpool.myself;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MySelfThreadPool
 * @date 2022-11-02 18:44
 * @description
 */
public class MySelfThreadPool {
	//线程池中默认为5个
	private static int WORK_NUM = 5;
	//队列默认任务个数为100
	private static int TASK_COUNT = 100;
	//工作线程
	private WorkThread[] workThreads;

	//任务队列，作为一个缓冲
	private final BlockingQueue<Runnable> taskQueue;
	private final int worker_num;
	//创建具有默认线程个数的线程池
	public MySelfThreadPool(){
		this(WORK_NUM,TASK_COUNT);
	}

	public MySelfThreadPool(int worker_num, int task_count) {
		if(worker_num <= 0)worker_num = WORK_NUM;
		if(task_count <= 0)task_count = TASK_COUNT;
		this.worker_num = worker_num;
		taskQueue = new ArrayBlockingQueue<>(task_count);
		workThreads = new WorkThread[worker_num];
		for(int i = 0;i < worker_num;i++){
			workThreads[i] = new WorkThread();
			workThreads[i].start();
		}
	}
	//执行任务，其实就是把任务加入任务队列，什么时候执行又线程管理器决定
	public void execute(Runnable task){
		try {
			taskQueue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//销毁线程池，该方法保证在所有任务都完成的情况下才销毁所有线程，否则等待任务完成才销毁
	public void destroy(){
		System.out.println("ready close pool~~");
		for(int i = 0;i < worker_num;i++){
			workThreads[i].stopWorker();
			workThreads[i] = null;//help GC
		}
	}
	/**
	 * 内部工作线程
	 **/
	private class WorkThread extends Thread{
		@Override
		public void run() {
			Runnable r = null;
			try {
				while (!isInterrupted()){
					r = taskQueue.take();
					if(r != null){
						System.out.println(getId() + "------>" + r);
						r.run();
					}
					r = null;//help GC
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public void stopWorker(){
			interrupt();
		}
	}
}
