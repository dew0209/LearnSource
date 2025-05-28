package com.dew.godl.base.daemon;

import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author LvLu
 * @className DaemonTest
 * @date 2023-02-01 20:21
 * @description
 */
public class DaemonTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t 开始运行，" +
					(Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
			while (true){
				System.out.print(111);
			}
		}, "t1");
		//1.设置守护线程，当主线程结束，该线程也结束了
		//2.必须在start之前设置，反之会产生 IllegalThreadStateException 异常是主线程接受的！
		t1.setDaemon(true);
		t1.start();
		try {TimeUnit.SECONDS.sleep(1);}catch (Exception e){}finally {}
		System.out.println(Thread.currentThread().getName() + "\t ---- end 主线程");
	}
}
