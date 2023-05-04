package com.dew.godl.increase.threadlocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 内存泄露和弱引用
 *   		内存泄露：不再会被使用的对象或者变量占用的内存不能被回收，就是内存泄露
 *   		四种引用：
 *   			finalize
 *   			强：当内存不足的时候，jvm开始垃圾回收，对于强引用的对象，就算出现了OOM也不会对该对象进行回收，死都不收。
 *   			软：相对强引用弱化了一些的引用，需要用java.lang.red.SoftReference类来实现，可以让对象豁免一些垃圾收集
 *     					当系统内存充足时，不会被回收
 *     					当系统内存不足时，会被回收
 *    					比如高速缓存等等
 *   			弱：弱引用需要用java.lang.ref.WeakReference类来实现，它比软引用的生存期更短
 *   					对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管jvm的内存空间是否足够，都会回收该对象占用的内存。
 *   			虚：虚引用必须和引用队列联合使用
 *   				虚拟引用需要java.lang.ref.PhantomReference类来实现，虚引用不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
 *   				它不能单独使用也不能通过它访问对象，虚引用必须和引用队列联合使用
 *   				get方法总是返回null
 *   					虚引用的主要作用是跟踪对象被垃圾回收的状态。仅仅是提供了一种确保对象被finalize以后，做某些事情的通知机制。
 *   				处理监控通知使用
 *   					设置虚引用关联对象的唯一目的，就是在这个对象被收集器回收的时候收到一个系统通知或者或后续添加进一步的处理，用来实现比finalize机制更加灵活的回收操作
 *
 *
 *   为什么ThreadLocal使用弱引用：
 *   		减少内存泄露的问题，使用弱引用，就可以使ThreadLocal对象在方法执行完毕之后顺利被回收且Entry的key引用指向为null
 *   		寻找脏Entry，即key=null的Entry，然后进行删除，多余数据，entry为：k：null value：实际值。ThreadLocal中的expungeStaleEntry()可以解决，另外。每次用完了，需要手动调用reomve()确保不会出现多余数据
 *	结论：
 *		手动remove
 *
 *		1.ThreadLocal并不解决线程间共享数据的问题
 *		2.ThreadLocal适用于变量在线程间隔离且在方法间共享的场景
 *		3.ThreadLocal通过隐式的在不同线程内创建独立实例副本避免了实例线程安全的问题
 *		4.每个线程持有一个只属于自己的专属Map并维护了ThreadLocal对象与具体实例的映射，该map由于只被持有它的线程访问，故不存在线程安全以及锁的问题
 *		5.ThreadLocalMap的Entry对ThreadLocal的引用为弱引用，避免了ThreadLocal对象无法被回收的问题，都会通过expungeStaleEntry,cleanSomeSlots,replaceStaleEntry这三个方法回收键为null的entry对象的值以及Entry对象本身从而防止内存泄露，属于安全加固的方法
 *
 *
 */
public class Base4 {
	public static void main(String[] args) {
		//虚
		MyObject myObject = new MyObject();
		ReferenceQueue<MyObject> queue = new ReferenceQueue<>();
		PhantomReference<MyObject> phantomReference = new PhantomReference<>(new MyObject(),queue);
		System.out.println(phantomReference.get());

		List<byte[]> list = new ArrayList<>();
		new Thread(()->{
			while(true){
				list.add(new byte[1 * 1024 * 1024]);
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				}catch (Exception e){

				}finally {

				}
				System.out.println(phantomReference.get() + "\t" + "list add ok");
			}
		},"t1").start();
		new Thread(()->{
			while (true){
				Reference<? extends MyObject> poll = queue.poll();
				if(poll != null){
					System.out.println("有虚对象回收加入了队列");
					break;
				}else {
					System.out.println("11111");
				}
			}
		},"t2").start();
	}

	public void m3(){
		//弱
		WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
		System.out.println("----gc 前 内存够用：" + weakReference.get());
		System.gc();
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch (Exception e){

		}finally {

		}
		System.out.println("----gc 后 内存够用：" + weakReference.get());
	}

	public void m2(){
		//软
		SoftReference<MyObject> reference = new SoftReference<>(new MyObject());
		System.out.println("-------" + reference.get());
		System.gc();
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch (Exception e){

		}finally {

		}
		System.out.println("-------" + reference.get());
		//需要设置运行参数 -Xms10m -Xmx10m
		byte[] bytes = new byte[20 * 1024 * 1024];
		//执行finalize
		System.out.println("-------" + reference.get());
	}

	public void m1(){
		//强
		MyObject myObject = new MyObject();
		System.out.println("gc before " + myObject);
		myObject = null;
		System.gc();//人工开启gc，一般不用。gc也是一个单独的线程进行干活
		System.out.println("gc after " + myObject);
	}

}
class MyObject{



	@Override
	protected void finalize() throws Throwable {
		System.out.println("---- invoke");
	}
}