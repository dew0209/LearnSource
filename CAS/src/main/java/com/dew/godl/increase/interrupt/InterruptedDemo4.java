package com.dew.godl.increase.interrupt;


public class InterruptedDemo4 {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "\t 中断标志位：" + Thread.interrupted());//false
		System.out.println(Thread.currentThread().getName() + "\t 中断标志位：" + Thread.interrupted());//false
		System.out.println("1");
		Thread.currentThread().interrupt();
		System.out.println("2");
		System.out.println(Thread.currentThread().getName() + "\t 中断标志位：" + Thread.interrupted());//true
		System.out.println(Thread.currentThread().getName() + "\t 中断标志位：" + Thread.interrupted());//false
	}
}
