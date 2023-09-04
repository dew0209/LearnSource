package com.dew.godl.increase.zl;

import org.openjdk.jol.info.ClassLayout;

/**
 * 有大量的线程参与锁的竞争，冲突性很高
 * 锁标志位：指向互斥量（重量级锁）的指针
 *
 *
 * 总结：
 * 		锁升级（升级成轻量级锁和重量级锁）后，hashCode去哪里了：
 * 			偏向锁：markWord存储的是偏向的线程id
 * 			轻量锁：markWord存储的是指向线程栈中Lock Record的指针
 * 			重量锁：markWord存储的是指向堆中的monitor对象的指针
 *
 * 			在无锁的状态下：markWord可以存储对象的hashCode值，当对象的hashCode方法第一次被调用时，jvm会生成对应的hashCode值并且存储到markWord中
 * 			对于偏向锁：在线程获取偏向锁时，会用Thread ID和epoch覆盖hashCode值，如果一个对象的hashCode方法已经被调用过一次之后，这个对象不能被设置偏向锁。
 * 						因为如果可以的话，那么markWord中的hashCode会被线程id覆盖，造成一个对象前后两次调用hashCode方法得到的结果不一致
 * 			升级为轻量级锁：jvm会在当前线程的栈帧中创建一个锁记录（Lock Record）空间，用于存储锁对象的markWord拷贝，该拷贝中可以包含hashCode，所以轻量锁可以
 * 							和hashCode共存，哈希码和GC年龄自然保存在此，释放锁后会将这些信息写会到对象头
 * 			升级为重量级锁后，markWord保存的重量级锁指针，代表重量级锁的ObjectMonitor类里有字段记录非加锁状态下的markWord，锁释放后也会将信息写回到对象头
 *
 */
public class Base {
	public static void main(String[] args) {
		try {
			Thread.sleep(5000L);
		}catch (Exception e){

		}
		Object o = new Object();
		synchronized (o){
			o.hashCode();
			System.out.println("本应是偏向锁-碰撞为重量锁");
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
		}
	}
	public static void m1(){
		try {
			Thread.sleep(5000L);
		}catch (Exception e){

		}
		Object o = new Object();
		System.out.println("本应是偏向锁");
		System.out.println(ClassLayout.parseInstance(o).toPrintable());
		o.hashCode();
		synchronized (o){
			System.out.println("本应是偏向锁-升级为轻量级别锁");
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
		}
	}
}
