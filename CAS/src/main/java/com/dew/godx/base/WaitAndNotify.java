package com.dew.godx.base;

import org.junit.Test;

/**
 * wait()：对象上的方法
 * notify()/notifyAll()：对象上的方法
 * 等待和通知的标准范式：
 * 		等待方：
 * 			1.获取对象里面的锁
 * 			2.循环判断条件是否满足，不满足调用wait方法。即使被唤醒，也要看相应的条件是否被满足
 * 			3.条件满足执行业务逻辑
 * 		通知方：
 * 			1.获得对象的锁
 * 			2.改变条件
 * 			3.通知所有等待在该对象的线程
 * notify/notifyAll的区别：
 * 		notify随机唤醒一个
 * 		notifyAll唤醒所有
 * 		尽量使用notifyAll，使用notify有可能会发生信号丢失的情况
 * 		不能一直等待，使用等待超时模式实现一个连接池
 * 			假设等待时长为T，now + T 以后超时
 * 			long overtime = now + T;
 * 			long remain = T;//等待的持续时间
 * 			while(result不满足条件 && remain  > 0){
 * 			 	wait(remain);
 * 			 	remain = overtime - now;//等待剩下的时间
 * 			}
 * 			return result;
 *
 **/
//测试wait/notify/notifyAll
public class WaitAndNotify {
	private static Express express = new Express(0,Express.CITY);
	/* 检查里程数变化的线程，不满足条件，线程一直等待 */
	private static class CheckKm extends Thread{
		@Override
		public void run() {
			express.waitKm();
		}
	}
	/* 检查地点变化的线程，不满足条件，线程一直等待 */
	private static class CheckSite extends Thread{
		@Override
		public void run() {
			express.waitSite();
		}
	}
	@Test
	public void testWaitAndNotify() throws InterruptedException {
		for(int i = 0;i < 3;i++){
			new CheckSite().start();
		}
		for(int i = 0;i < 3;i++){
			new CheckKm().start();
		}
		Thread.sleep(1000);
		express.changeKm();
	}
}

class Express {
	public final static String CITY = "ShangHai";
	private int km;/* 快递运输里程 */
	private String site;/* 快递到达地点 */

	public Express(){
	}

	public Express(int km, String site) {
		this.km = km;
		this.site = site;
	}
	/* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理 */
	public synchronized void changeKm(){
		this.km = 101;
		notifyAll();//换成notify可能会唤醒不成功（唤醒了地点变化等待的线程，导致错误）
	}
	/* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理 */
	public synchronized void changeSite(){
		this.site = "beijing";
		notifyAll();//换成notify可能会唤醒不成功（唤醒了里程变化等待的线程，导致错误）
	}

	public synchronized void waitKm(){
		while(this.km <= 100){
			try {
				wait();
				System.out.println("check km thread[" + Thread.currentThread().getId() + "] is be notified");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("the km is " + this.km + ", I will change db");
	}

	public synchronized void waitSite(){
		while(CITY.equals(this.site)){
			try {
				wait();
				System.out.println("check site thread[" + Thread.currentThread().getId() + "] is be notified");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("the site is " + this.site + ", I will call user");
	}

}
