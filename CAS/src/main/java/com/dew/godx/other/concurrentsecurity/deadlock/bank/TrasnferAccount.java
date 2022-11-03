package com.dew.godx.other.concurrentsecurity.deadlock.bank;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className TrasnferAccount
 * @date 2022-11-03 20:37
 * @description
 */
public class TrasnferAccount implements ITransfer{
	@Override
	public void transFer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
		synchronized (from){//先锁转出
			System.out.println(Thread.currentThread().getName() + " get " + from.getName());
			Thread.sleep(1000);
			synchronized (to) {//再锁转入
				System.out.println(Thread.currentThread().getName() + " get " + to.getName());
				from.flyMoney(amount);
				to.addMoney(amount);
			}
		}
	}
}
