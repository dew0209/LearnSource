package com.dew.godx.other.concurrentsecurity.deadlock.bank;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className PayCompany
 * @date 2022-11-03 20:33
 * @description 动态的死锁
 */
public class PayCompany {
	private static class TransFerThread extends Thread {
		private String name;//线程名字
		private UserAccount from;//转出人
		private UserAccount to;//收入人
		private int amount;//金额
		private ITransfer transfer;//转出实现接口

		public TransFerThread(String name, UserAccount from, UserAccount to, int amount, ITransfer transfer) {
			this.name = name;
			this.from = from;
			this.to = to;
			this.amount = amount;
			this.transfer = transfer;
		}

		@Override
		public void run() {
			Thread.currentThread().setName(name);
			try {
				transfer.transFer(from,to,amount);
			}catch (Exception e){

			}
		}
	}

	public static void main(String[] args) {
		ITransfer t = new SafeOperateTwo();
		UserAccount u1 = new UserAccount("张三", 20000);
		UserAccount u2 = new UserAccount("李四", 20000);
		//你以为你是固定顺序的加锁，结果调用的不一样，产生了死锁，是动态的。如何解决呢？可以将两个锁变成一个锁
		TransFerThread zhangsangTolisi = new TransFerThread("zhangsangTolisi", u1, u2, 20, t);
		TransFerThread lisiTozhangsan = new TransFerThread("lisiTozhangsan", u2, u1, 20, t);
		zhangsangTolisi.start();
		lisiTozhangsan.start();
	}
}
