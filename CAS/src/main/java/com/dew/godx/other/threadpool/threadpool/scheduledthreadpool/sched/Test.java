package com.dew.godx.other.threadpool.threadpool.scheduledthreadpool.sched;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author LvLu
 * @className Test
 * @date 2022-11-02 21:00
 * @description
 */
public class Test {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor ste = new ScheduledThreadPoolExecutor(1);
		//Work.HashException跑一次就不跑了
		ste.scheduleAtFixedRate(new Work(Work.HashException),1000,3000, TimeUnit.MILLISECONDS);
		//Work.Normal正常走
		ste.scheduleAtFixedRate(new Work(Work.Normal),1000,3000, TimeUnit.MILLISECONDS);
		//Work.ProcessException正常走   结论：写代码时候，最好把整个run catch住
		ste.scheduleAtFixedRate(new Work(Work.ProcessException),1000,3000, TimeUnit.MILLISECONDS);
	}
}
