package com.dew.godl.increase.synchronizeddemo;

import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized与锁升级
 * 		无锁-偏向锁-轻量级锁-重量级锁
 * 			由对象头中mark word根据锁标志位的不同而被复用及锁升级策略
 *
 * java5之前：
 * 		monitor可以理解为一种同步工具，也可以理解为一种同步机制，常常被描述为一个java对象。java对象是天生的monitor，每一个java对象都有成为monitor的潜质
 * 		因为在java的设计中，每一个java对象自带了一个看不见的锁，它叫做内部锁或者monitor锁
 *
 * 		monitor的本质是依赖于底层操作系统的mutex lock实现，操作系统实现线程之间的切换需要从用户态到内核态的转换，成本非常高
 * java6之后：
 * 		为了减少获得锁和释放锁所带来的性能消耗，引入了轻量级锁和偏向锁
 * 		需要有个逐步升级的过程，别一开始就捅到重量级锁
 * 锁升级：
 * 		多线程访问情况：三种
 * 			只有一个线程访问
 * 			有多个线程（2个线程A，B交替访问）
 * 			竞争激烈，更多个线程来访问
 * 		升级流程：
 * 			synchronized用的锁是存在java对象头里的mark word中，锁升级功能主要依赖mark word中锁标志位和释放偏向锁标志位
 * 		偏向锁：mark word存储的是偏向的线程ID
 * 		轻量锁：mark word存储的是指向线程栈中Lock Record的指针
 * 		重量锁：mark word存储的是指向堆中的monitor对象的指针
 *
 * 		无锁：不会产生竞争。
 *
 * 		偏向锁：当线程A第一次竞争到锁时，通过修改mark word中的偏向线程id，偏向模式。如果不存在其他线程竞争，那么持有偏向锁的线程将永远不需要进行同步。
 * 			当一段同步代码一直被同一个线程多次访问，由于只有一个线程那么该线程在后续访问时便会自动获得锁
 * 			注意：偏向锁只有遇到其他线程尝试竞争偏向锁时，持有偏向锁的线程才会释放，线程是不会主动释放偏向锁的。
 * */
public class Base {
	public static void main(String[] args) {
		Object obj = new Object();
		System.out.println(obj.hashCode());
		System.out.println(Integer.toHexString(obj.hashCode()));
		System.out.println(Integer.toBinaryString(obj.hashCode()));
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
		//10011010111111011101010100100
		//00010011010111111011101010100100 去掉前面三个0
	}
}
