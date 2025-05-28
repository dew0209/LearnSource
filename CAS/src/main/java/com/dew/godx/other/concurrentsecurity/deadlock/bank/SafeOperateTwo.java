package com.dew.godx.other.concurrentsecurity.deadlock.bank;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 *
 *
 * @author LvLu
 * @className SafeOperateTwo
 * @date 2022-11-03 20:57
 * @description tryLock 也可以解决死锁，tryLock拿不到不会阻塞
 */
public class SafeOperateTwo implements ITransfer{
	@Override
	public void transFer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
		Random r = new Random();
		while(true){
			if(from.getLock().tryLock()){
				try {
					System.out.println(Thread.currentThread().getName() + "--->" + from.getName());
					if(to.getLock().tryLock()){
						try {
							System.out.println(Thread.currentThread().getName() + "--->" + to.getName());
							//两把锁都拿到了
							from.flyMoney(amount);
							to.addMoney(amount);
							break;
						}finally {
							to.getLock().unlock();
						}
					}
				}finally {
					from.getLock().unlock();
				}
			}
			//防止产生活锁，防止两个线程不停的谦让，a拿了锁1，再去拿2，b拿了2，再去拿1，两者同时释放，此过程重复。加了sleep，可以有效减少这个过程
			//休眠的是一个随机数，大部分情况下两个线程休眠时间不一样，不会活锁，休眠时间一样的话，还是会产生活锁的。
			Thread.sleep(r.nextInt(10));
		}
	}
}
