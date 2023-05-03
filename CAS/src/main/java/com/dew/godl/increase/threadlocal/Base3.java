package com.dew.godl.increase.threadlocal;

/**
 * ThreadLocal从字面上就可以看出这是一个保存ThreadLocal对象的map(其实就是以ThreadLocal为key)，不过是经过了两层包装的ThreadLocal对象。
 * JVM内部维护了一个线程版的Map<ThreadLocal,Value>(通过ThreadLocal里面的set方法，结果把ThreadLocal对象自己当做key，放进了ThreadLocalMap中)，每个线程要用到这个T的时候，
 * 		用当前的线程去map里面获取，通过这样让每个线程都拥有了自己独立的变量，人手一份，竞争条件被彻底消除，在并发模式下是绝对安全的变量。
 *
 *
 *
 */
public class Base3 {

	public static void main(String[] args) {

	}

}
