package com.dew.godl.increase.cas.unfairandfair;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-15 17:14
 * @description
 */
public class Main {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(()->{for(int i = 0;i < 55;i++)ticket.sale();},"a").start();
		new Thread(()->{for(int i = 0;i < 55;i++)ticket.sale();},"b").start();
		new Thread(()->{for(int i = 0;i < 55;i++)ticket.sale();},"c").start();
	}
}

class Ticket{
	private int number = 50;
	ReentrantLock lock = new ReentrantLock(true);//true设置为公平锁，非公平可能会造成某个线程一直获不到锁。推荐使用非公平：更加充分利用cpu资源，节省上下文切换等等
	public void sale(){
		lock.lock();
		try {
			if(number > 0){
				System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
			}
		}catch (Exception e){

		}finally {
			lock.unlock();
		}
	}
}