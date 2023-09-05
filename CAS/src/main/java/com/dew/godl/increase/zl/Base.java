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
 *	各种锁优缺点，synchronized锁升级和实现原理
 *						优点								缺点								适用场景
 *			偏向锁：	加锁和解锁不需要额外的消耗和执行		如果线程间存在竞争，					适用于只有一个线程访问同步块的场景
 *						非同步方法相比仅存在纳秒级的差距		会带来额外的锁撤销的消耗
 *			轻量级锁	竞争的线程不会阻塞，提高了线程的		如果始终得不到锁竞争的线程			追求响应速度，同步块执行速度非常快
 *						响应速度							使用自旋会消耗cpu
 *			重量级锁	线程竞争不使用自旋，不会消耗cpu		线程阻塞，响应时间缓慢				追求吞吐量，同步块执行速度较长
 *
 *	总结：
 *		synchronized锁升级就是：先自旋，不行再阻塞
 *
 *	JIT编译器对锁的优化：
 *		JIT：即时编译器
 *		锁消除：锁并没有被共用扩散到其它线程使用，极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
 *		锁粗化：前后相邻的都是同一个锁对象，那JIT编译器就会把这几个synchronized块合并成一个大块，避免次次的申请和释放锁，提升了性能
 *
 */
public class Base {

	static Object objectLock = new Object();

	public void m3(){
		//锁消除问题，JIT编译器会无视它，synchronized (o)，每次new出来，不存在了，非正常的
		//这个锁并没有被共用扩散到其它线程使用，极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
		Object o = new Object();
		synchronized (o){
			System.out.println("-----------hello LockClearUPDemo---" + "\t" + o.hashCode() + "\t" + objectLock.hashCode());
		}
	}

	public static void main(String[] args) {
		Base base = new Base();
		for(int i = 0;i < 10;i++){
			new Thread(()->{
				/**
				 * 锁的粗化
				 * synchronized (objectLock){
				 * 		System.out.println("11111");
				 * 		System.out.println("22222");
				 * 		System.out.println("33333");
				 * 		System.out.println("44444");
				 * 		System.out.println("55555");
				 *  }
				 **/
				synchronized (objectLock){
					System.out.println("11111");
				}
				synchronized (objectLock){
					System.out.println("22222");
				}
				synchronized (objectLock){
					System.out.println("33333");
				}
				synchronized (objectLock){
					System.out.println("44444");
				}
				synchronized (objectLock){
					System.out.println("55555");
				}
			},String.valueOf(i)).start();
		}
	}

	public static void m2(){
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
