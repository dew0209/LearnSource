package com.dew.godl.increase.support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
public class TraditionalDemo {
	public static volatile boolean isStop = false;
	public static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			while(true){
				if(Thread.currentThread().isInterrupted()){
					System.out.println(Thread.currentThread().getName() + "\t isInterrupted被修改为true，程序停止");
					break;
				}
				System.out.println("------hello isInterrupted");
			}
		},"t1");
		t1.start();
		try{TimeUnit.MILLISECONDS.sleep(20); }catch (Exception e){}
		new Thread(()->{
			t1.interrupt();
		},"t2").start();
	}

	public static void m2(){
		new Thread(()->{
			while(true){
				if(atomicBoolean.get()){
					System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
					break;
				}
				System.out.println("------hello atomicBoolean");
			}
		},"t1").start();
		try{TimeUnit.MILLISECONDS.sleep(20); }catch (Exception e){}
		new Thread(()->{
			atomicBoolean.compareAndSet(false,true);
		},"t2").start();
	}

	public static void m1(){
		new Thread(()->{
			while(true){
				if(isStop){
					System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
					break;
				}
				System.out.println("------hello volatile");
			}
		},"t1").start();
		try{TimeUnit.MILLISECONDS.sleep(20); }catch (Exception e){}
		new Thread(()->{
			isStop = true;
		},"t2").start();
	}
}
