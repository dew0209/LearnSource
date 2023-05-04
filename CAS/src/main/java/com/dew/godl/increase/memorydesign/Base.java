package com.dew.godl.increase.memorydesign;

/**
 * new Object();
 * 	对象的构成布局：
 * 		对象实例 = 对象头 + 实例数据 + 对齐填充
 *		对象头：
 *			对象标记（mark word） + 类元信息（类型指针）
 *			存储内容					标志位		状态
 *			对象哈希码，对象分代年龄		01			未锁定
 *			指向锁记录的指针				00			轻量级锁
 *			指向重量级锁的指针			10			膨胀（重量级锁定）
 *			空，不需要记录信息			11			GC标记
 *			偏向线程ID，偏向时间戳，对象分代年龄	01	可偏向
 *			64位中。mark word 占8字节 类型指针占8字节
 *			类型指针：对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是那个类的的实例
 *		实例数据：
 *			存放类的属性（field）数据信息，包括父类的属性信息
 *		对齐填充：
 *			虚拟机要求对对象起始地址必须是8字节的整数倍。填充数据不是必须存在的，仅仅是为了字节对齐，这部分内存按8字节补充对齐
 *
 *
 *
 */
public class Base {
	public static void main(String[] args) {
		Object o = new Object();//new 一个对象 占内存多少
		System.out.println(o.hashCode());//hashcode记录在那里
		synchronized (o){


		}
		System.gc();//手动收集垃圾数据，，，15次可以从新生代->养老区

		//类型指针
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		Customer c3 = new Customer();

	}
}

class Customer{//16 + 4 + 1 = 21 ---> 对齐填充到 24字节
	int id;
	boolean f = true;
}
