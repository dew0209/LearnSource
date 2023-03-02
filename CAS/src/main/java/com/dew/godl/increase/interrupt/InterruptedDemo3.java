package com.dew.godl.increase.interrupt;


import java.util.concurrent.TimeUnit;

public class InterruptedDemo3 {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			while (true){
				if(Thread.currentThread().isInterrupted()){
					System.out.println(Thread.currentThread().getName() + "\t 中断标志位：" + Thread.currentThread().isInterrupted());
					break;
				}
				try {
					Thread.sleep(2);
				}catch (Exception e){
					//抛出InterruptedException会将中断标志位重置为false，需要手动interrupt一下
					Thread.currentThread().interrupt();
				}
				System.out.println("hello------ ");
			}
		}, "t1");
		t1.start();
		try{ TimeUnit.MILLISECONDS.sleep(2); }catch (Exception e){}
		new Thread(()->{t1.interrupt();},"t2").start();
	}
}
