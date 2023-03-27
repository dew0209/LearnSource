package com.dew.godl.increase.cas.atom;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 以一种线程安全的方式操作非线程安全对象内的某些字段
 * 对象的属性修改原子类
 * 		AtomicIntegerFieldUpdater
 * 		AtomicLongFieldUpdater
 *		AtomicReferenceFieldUpdater
 */
public class Base3 {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(10);
		BankAccount bankAccount = new BankAccount();
		for(int i = 0;i < 10;i++){
			new Thread(()->{
				try {
					for(int j = 0;j < 10000;j++){
						//bankAccount.add();
						bankAccount.transMoney(bankAccount);
					}
				}finally {
					latch.countDown();
				}
			},String.valueOf(i)).start();
		}
		latch.await();
		System.out.println(bankAccount.money);

	}
}


class BankAccount{
	String name = "aaa";

	AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");
	public void transMoney(BankAccount bankAccount){
		fieldUpdater.getAndIncrement(bankAccount);
	}

	//1.更新的对象属性必须使用public volatile修饰符
	//2.因为对象的属性修改类型原子类都是抽象类，所以每次都必须使用静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性
	public volatile int money = 0;
	public void add(){
		money++;
	}
}