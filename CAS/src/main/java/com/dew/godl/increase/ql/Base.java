package com.dew.godl.increase.ql;

import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁：
 * 		多线程竞争，但是任意时刻最多只有一个线程竞争，即不存在锁竞争太过激烈的情况，也就没有线程的阻塞
 * 		本质就是自旋锁cas
 * 		主要目的：
 * 			在没有多线程竞争的前提下，通过cas减少重量级锁使用操作系统互斥量产生的性能消耗，说白了先自旋，不行升级阻塞
 * 			升级时机：当关闭偏向锁功能或多线程竞争偏向锁会导致偏向锁升级为轻量级锁
 *
 * 		当自旋到达了一定次数和程度（轻量锁升级为重量锁）：
 * 			jdk6之前：
 * 					1.默认10  -XX:PreBlockSpin=10来修改
 * 					2.自旋线程数超过cpu核数一半
 * 			jdk6之后：
 * 					线程如果自旋成功了，那下次自旋的最大次数会增加，因为jvm认为既然上次成功了，那么这一次成功的概率会很大
 * 					反之，如果很少会自旋成功，那么下次会减少自旋的次数甚至不自旋，避免cpu空转
 * 				自适应意味着自旋的次数不是固定不变的。
 * 				根据：
 * 					同一个锁上一次自旋的时间
 * 					拥有锁线程的状态来决定
 * 	轻量锁和自适应锁的区别：
 * 		争夺轻量级锁失败时，自旋锁尝试抢占锁
 * 		轻量级锁每次退出同步块都需要释放锁，而偏向锁是在竞争发生时才释放锁
 */
public class Base {


	public static void main(String[] args) {
		Object o = new Object();
		new Thread(()->{
			synchronized (o){
				System.out.println(ClassLayout.parseInstance(o).toPrintable());
			}
		},"t1").start();
	}

}
