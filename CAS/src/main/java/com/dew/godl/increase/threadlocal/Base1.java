package com.dew.godl.increase.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Base1
 * @date 2023-05-04 7:03
 * @description
 */
public class Base1 {

	public static void main(String[] args) {
		//5个销售随机卖房子，按照各自本事进行提成
		House house = new House();
		for(int i = 0;i <= 5;i++){
			new Thread(()->{
				try {
					int size = new Random().nextInt(5) + 1;
					for(int j = 0;j < size;j++){
						house.saleByThreadLocal();
						house.add();
					}
					System.out.println(Thread.currentThread().getName() + "====" + house.saleVolume.get());
				}catch (Exception e){

				}finally {
					house.saleVolume.remove();
				}
			},String.valueOf(i)).start();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		}catch (Exception e){

		}
		System.out.println(Thread.currentThread().getName() + "====" + house.count);

	}
	/**
	 * 5个销售买房子，集团高层只关心销售总量的准确统计数
	 **/
	public static void m1(){
		House house = new House();
		for(int i = 0;i <= 5;i++){
			new Thread(()->{
				int size = new Random().nextInt(5) + 1;
				for(int j = 0;j < size;j++){
					house.add();
				}
			},String.valueOf(i)).start();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		}catch (Exception e){

		}
		System.out.println(Thread.currentThread().getName() + "====" + house.count);
	}
}


class House{
	int count;
	public synchronized void add(){
		++count;
	}
	/*ThreadLocal<Integer> saleVolume = new ThreadLocal<>(){
		@Override
		protected Object initialValue() {
			return 0;
		}
	};
	*/
	ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(()-> 0);
	public void saleByThreadLocal(){
		saleVolume.set(saleVolume.get() + 1);
	}
}