package com.dew.godl.increase.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * 无原子性
 * 	私有内存和主内存公共内存中变量不同步
 * 	volatile通常用于保存某个状态的boolean值或者int值
 */
public class Demo2 {
	public static void main(String[] args) {
		Number number = new Number();
		for(int i = 0;i < 10;i++){
			new Thread(()->{
				for(int j = 0;j < 1000;j++){
					number.addplus();
				}
			},String.valueOf(i)).start();
		}
		try{
			TimeUnit.SECONDS.sleep(2);
		}catch (Exception e){}
		//结果一般上是小于10000的。volatile不具备原子性
		System.out.println(number.number);
	}
}
class Number{
	volatile int number;
	public  void addplus(){
		number++;
	}
}