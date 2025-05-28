package com.dew.godx.other.threadpool.threadpool.scheduledthreadpool.sched;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author LvLu
 * @className Work
 * @date 2022-11-02 20:54
 * @description
 */
public class Work implements Runnable {

	public final static int Normal = 0;//普通任务类型
	public final static int HashException = -1;//会抛出异常的任务
	public final static int ProcessException = 1;//抛出异常但会捕捉的任务类型

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private int taskType;

	public Work(int taskType) {
		this.taskType = taskType;
	}

	@Override
	public void run() {
		if(taskType == HashException){
			System.out.println(format.format(new Date() + " Exception made ......"));
			throw new RuntimeException("Exception Happened");
		}else if(taskType == ProcessException){
			try {
				System.out.println(format.format(new Date() + " Exception made but catch ......"));
				throw new RuntimeException("Exception Happened");
			}catch (Exception e){
				System.out.println("Exception be catch");
			}
		}else {
			System.out.println("Normal " + format.format(new Date()));
		}
	}
}
