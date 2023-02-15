package com.dew.godl.increase.cas.lock;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className EightLock
 * @date 2023-02-15 15:45
 * @description
 */
public class EightLock {
	/**
	 * 1.标准访问有ab两个线程，请问先打印邮件还是短信
	 * 2.sendEmail方法中加入暂停三秒钟，请问先打印邮件还是短信
	 * 3.添加一个普通的hello方法，请问先打印邮件还是hello
	 * 4.有两部手机，请问先打印邮件还是短信
	 * 5.有两个静态同步方法，有一部手机，请问先打印邮件还是短信
	 * 6.有两个静态同步方法，有两部手机，请问先打印邮件还是短信
	 * 7.有一个静态同步方法，有一个普通同步方法，有一部手机，请问先打印邮件还是短信
	 * 8.有一个静态同步方法，有一个普通同步方法，有两部手机，请问先打印邮件还是短信
	 *
	 * 类加载是一个将class字节码对象文件实例化成class对象并进行相关初始化的过程
	 * 全小写的class是关键字用来定义类，首字母大写的Class它是所有class的类
	 *
	 * 结论：
	 * 	1.作用于实例方法，当前实例加锁，进入同步代码前提要获得当前实例的锁
	 * 	2.作用于代码块，对括号里配置的对象加锁
	 * 	3.作用于静态方法，当前类加锁，进去同步代码前要获得当前类对象的锁
	 **/
	public static void main(String[] args) {
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		new Thread(()->{
			phone.sendEmail();
		},"a").start();
		try{TimeUnit.MILLISECONDS.sleep(200);}catch(Exception e){}
		new Thread(()->{
			phone.sendSms();
			//phone.hello();
		},"b").start();

	}
}

class Phone{
	public synchronized void sendEmail(){
		try{TimeUnit.MILLISECONDS.sleep(3000);}catch(Exception e){}
		System.out.println("---sendEmail");
	}

	public synchronized void sendSms(){
		System.out.println("---sendSms");
	}

	public void hello(){
		System.out.println("hello!");
	}
}