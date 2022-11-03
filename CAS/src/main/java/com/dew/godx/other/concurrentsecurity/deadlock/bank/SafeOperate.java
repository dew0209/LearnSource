package com.dew.godx.other.concurrentsecurity.deadlock.bank;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className SafeOperate
 * @date 2022-11-03 20:48
 * @description
 */
public class SafeOperate implements ITransfer{

	private static Object tieLock = new Object();

	@Override
	public void transFer(UserAccount from, UserAccount to, int amount) throws InterruptedException {



		//动态的保证顺序
		int fromHash = System.identityHashCode(from);
		int toHash = System.identityHashCode(to);
		if(fromHash < toHash){
			synchronized (from){//先锁转出
				System.out.println(Thread.currentThread().getName() + " get " + from.getName());
				Thread.sleep(1000);
				synchronized (to) {//再锁转入
					System.out.println(Thread.currentThread().getName() + " get " + to.getName());
					from.flyMoney(amount);
					to.addMoney(amount);
				}
			}
		}else if(fromHash > toHash){
			synchronized (to){//先锁转出
				System.out.println(Thread.currentThread().getName() + " get " + to.getName());
				Thread.sleep(1000);
				synchronized (from) {//再锁转入
					System.out.println(Thread.currentThread().getName() + " get " + from.getName());
					from.flyMoney(amount);
					to.addMoney(amount);
				}
			}
		}else {
			//发送hash碰撞,加时。发生这个情况比较小，性能会损耗一点点（可以接收的代价）。
			synchronized (tieLock){
				synchronized (from){
					synchronized (to){
						from.flyMoney(amount);
						to.addMoney(amount);
					}
				}
			}
		}
	}
}
