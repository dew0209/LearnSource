package com.dew.godl.increase.cas.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 模拟cas
 *	实现一个自旋锁
 *	自旋锁好处：循环比较获取没有类似wait的阻塞
 *
 *	通过cas操作完成自旋锁，a线程先进来调用myLock方法持有锁5秒
 *	当前有线程持有锁，只能通过自旋等待，直到A释放后B随后抢到
 */
public class Demo {
	AtomicReference<Thread> atomicReference = new AtomicReference<>();

	public void lock(){
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
		while(!atomicReference.compareAndSet(null,thread)){

		}
		System.out.println(Thread.currentThread().getName() + "\t" + "----锁定");
	}

	public void unlock(){
		Thread thread = Thread.currentThread();
		atomicReference.compareAndSet(thread,null);
		System.out.println(Thread.currentThread().getName() + "\t" + "task over");
	}

	public static void main(String[] args) {
		Demo demo = new Demo();
		new Thread(()->{
			demo.lock();
			try {
				TimeUnit.SECONDS.sleep(5);
			}catch (Exception e){

			}
			demo.unlock();
		},"A").start();
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		}catch (Exception e){

		}
		new Thread(()->{
			demo.lock();
			try {
				TimeUnit.SECONDS.sleep(5);
			}catch (Exception e){

			}
			demo.unlock();
		},"B").start();
	}
}
