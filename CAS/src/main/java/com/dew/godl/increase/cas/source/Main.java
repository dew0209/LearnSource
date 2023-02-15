package com.dew.godl.increase.cas.source;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-15 16:27
 * @description
 */
public class Main {
	Object object = new Object();
	//javap -c
//	public void m1(){
//		//使用的是monitorexit和monitorenter指令
//		//一般下是一个monitorenter配合两个monitorexit。一个正常退出，一个异常退出
//		//极端情况下存在一个monitorenter一个monitorexit
//		synchronized (object){
//			System.out.println("----hello synchronized code block----");
//			throw new RuntimeException();//这种情况就只有一个monitorexit
//		}
//	}
	//javap -v
//	public synchronized void m2(){
//		System.out.println("---hello synchronized m2");
//	}
	//javap -v
	public static synchronized void m3(){
		System.out.println("---hello synchronized m3");
	}
	public static void main(String[] args) {

	}
}
