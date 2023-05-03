package com.dew.godl.increase.threadlocal;

/**
 * 内存泄露和弱引用
 *   		内存泄露：不再会被使用的对象或者变量占用的内存不能被回收，就是内存泄露
 *   		四种引用：
 *   			finalize
 *   			强：当内存不足的时候，jvm开始垃圾回收，对于强引用的对象，就算出现了OOM也不会对该对象进行回收，死都不收。
 *   			弱：
 *   			软：
 *   			虚：
 */
public class Base4 {
	public static void main(String[] args) {
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