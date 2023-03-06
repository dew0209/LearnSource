package com.dew.godl.increase.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockSupport
 * 	park：许可证默认没有不能放行，所以一开始调用park方法，当前线程就会阻塞，直到别的线程给当前线程发放permit，park方法才会被唤醒
 * 	unpark：调用unpark后，就会将Thread线程的许可证permit发放，会自动唤醒park线程，即之前阻塞中的LockSupport.park()方法会立即返回
 *
 * 	正常 + 无锁块要求
 * 	之前错误的先唤醒后等待，lockSupport照样支持
 *  许可证上限为1
 * 	总结：
 *		lockSupport是一个线程阻塞工具类，所有的方法都是静态方法，可以让线程在任意位置阻塞，阻塞之后也有对应的唤醒方法。依赖于Unsafe中的native方法
 */
public class Demo {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t ------");
			LockSupport.park();
			System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
		}, "t1");
		t1.start();
		//try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
		new Thread(()->{

			System.out.println(Thread.currentThread().getName() + "\t ------发出通知");
		},"t2").start();
	}
}