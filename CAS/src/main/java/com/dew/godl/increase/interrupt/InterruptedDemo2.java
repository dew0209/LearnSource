package com.dew.godl.increase.interrupt;


import java.util.concurrent.TimeUnit;

public class InterruptedDemo2 {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for(int i = 0;i < 300;i++){
				System.out.println("--------：" + i);
			}
			System.out.println("t1调用interrupt后的中断标识位；" + Thread.currentThread().isInterrupted());
		},"t1");
		t1.start();
		System.out.println("t1线程默认的中断标志位：" + t1.isInterrupted());
		try{ TimeUnit.MILLISECONDS.sleep(2); }catch (Exception e){}
		t1.interrupt();
		System.out.println("t1线程调用interrupt()后的中断标志位：" + t1.isInterrupted());
		try{ TimeUnit.MILLISECONDS.sleep(2000); }catch (Exception e){}
		System.out.println("t1线程调用interrupt()后的中断标志位：" + t1.isInterrupted());//false 不活动的线程没啥影响

	}
}
