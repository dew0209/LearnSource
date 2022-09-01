package com.dew.godx.other.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ExpressCond {
	public final static String CITY = "ShangHai";
	private int km;//快递运输里程数
	private String site;//快递到达地点
	//一个lock可以有多个condition的
	private Lock kmLock = new ReentrantLock();
	private Lock siteLock = new ReentrantLock();
	private Condition kmCondition = kmLock.newCondition();
	private Condition siteCondition = siteLock.newCondition();

	public ExpressCond(){

	}

	public ExpressCond(int km, String site) {
		this.km = km;
		this.site = site;
	}
	//变化公里数，然后通知处于wait状态并且需要处理公里数的线程进行业务处理
	public void changeKm(){
		kmLock.lock();
		try {
			this.km = 101;
			kmCondition.signal();
		}finally {
			kmLock.unlock();
		}
	}
	//变化地点，然后通知处于wait状态并且需要处理地点的线程进行业务处理
	public void changeSite(){
		siteLock.lock();
		try {
			this.site = "北京";
			siteCondition.signal();
		}finally {
			siteLock.unlock();
		}
	}
	public void waitKm(){
		kmLock.lock();
		try {
			while(this.km <= 100){
				try {
					kmCondition.await();
					System.out.println("check km thread[" + Thread.currentThread().getId() + "] is be notified");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("the km is " + this.km + ", I will change db");
		}finally {
			kmLock.unlock();
		}
	}

	public void waitSite(){
		siteLock.lock();
		try {
			while(CITY.equals(this.site)){
				try {
					siteCondition.await();
					System.out.println("check site thread[" + Thread.currentThread().getId() + "] is be notified");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("the site is " + this.site + ", I will call user");
		}finally {
			siteLock.unlock();
		}
	}
}
