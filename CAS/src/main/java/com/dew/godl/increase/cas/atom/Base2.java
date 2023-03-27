package com.dew.godl.increase.cas.atom;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 引用类型原子类
 *		AtomicReference
 *		AtomicStampedReference 版本号
 *		AtomicMarkableReference 一次性的解决aba问题 动过就不能在动了
 */
public class Base2 {
	static AtomicMarkableReference markableReference = new AtomicMarkableReference(100,false);
	public static void main(String[] args) {
		new Thread(()->{
			boolean res = markableReference.isMarked();
			System.out.println(Thread.currentThread().getName() + "\t" + "默认标识：" + res);
			boolean b = markableReference.compareAndSet(100, 1000, res, !res);
			System.out.println(Thread.currentThread().getName() + "\t" + "t1线程casResult：" + b);
			System.out.println(Thread.currentThread().getName() + "\t" + markableReference.isMarked());
			System.out.println(Thread.currentThread().getName() + "\t" + markableReference.getReference());
		},"t1").start();
		new Thread(()->{
			boolean res = markableReference.isMarked();
			System.out.println(Thread.currentThread().getName() + "\t" + "默认标识：" + res);
			boolean b = markableReference.compareAndSet(100, 2000, res, !res);
			System.out.println(Thread.currentThread().getName() + "\t" + "t2线程casResult：" + b);
			System.out.println(Thread.currentThread().getName() + "\t" + markableReference.isMarked());
			System.out.println(Thread.currentThread().getName() + "\t" + markableReference.getReference());
		},"t2").start();
	}
}
