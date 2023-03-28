package com.dew.godl.increase.cas.atom;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicReferenceFieldUpdater
 *
 */
public class Base4 {
	public static void main(String[] args) {
		MyVar myVar = new MyVar();
		for(int i = 0;i < 5;i++){
			new Thread(()->{
				myVar.init(myVar);
			},String.valueOf(i + 1)).start();
		}
	}
}


class MyVar {
	public volatile Boolean isInit = Boolean.FALSE;

	AtomicReferenceFieldUpdater<MyVar,Boolean> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInit");

	public void init(MyVar var){
		if(referenceFieldUpdater.compareAndSet(var,Boolean.FALSE,Boolean.TRUE)){
			System.out.println(Thread.currentThread().getName() + "\t" + "------ start init,need 3seconds");
			try {TimeUnit.SECONDS.sleep(new Random().nextInt(10));}catch (Exception e){}
			System.out.println(Thread.currentThread().getName() + "\t" + "------ over init");
		}else {
			System.out.println(Thread.currentThread().getName() + "\t" + "------ 已经有线程正在进行初始化工作。。。");
		}
	}

}