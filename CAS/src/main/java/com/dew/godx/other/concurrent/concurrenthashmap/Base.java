package com.dew.godx.other.concurrent.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Hash
 * 	散列，哈希：把任意长度的输入通过一种算法（散列），变换成为固定长度的输出，这个输出值就是散列值
 * 				容易产生哈希冲突
 * 			md5 md4 sha 都是hash算法，一般不可逆，推荐加盐，防止彩虹表碰撞
 * 	解决哈希冲突：
 * 		1.开发寻址法
 * 		2.再散列
 * 		3.链地址法
 *  putIfAbsent:没有的话就放，有的话就不放了
 *
 * HashMap：
 * 	put操作会引起死循环，会让HashMap里面Entry链表产生环形数据结构
 *
 * ConcurrentHashMap：
 * 	jdk1.7以及之前：
 * 		Segment数组，每个segment有一个table 每个table里面存了一个hashEntry
 * 		get():
 * 			定位segment：key的hashCode进行再散列，散列值的高位进行取模
 * 			定位table：key的hashCode进行再散列，散列值的高位进行取模
 * 			依次扫描链表，要么找到元素，要么返回null
 * 			get时候不需要加锁，因为hashEntry里面使用了volatile进行修饰
 * 		put():
 * 			加锁
 * 			定位segment
 * 			定位table
 * 			cas插入，自动扩容和重新散列
 * 			segment不扩容，下面的table是扩容的
 * 			key相同的情况？
 * 		size()：两次统计，比较两次结果，一致返回，进行两次不加锁的统计，结果一致返回，不一致进行加锁。
 * 		弱一致性
 * 	jdk1.8和以后：
 *
 */
public class Base {

	public static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

	public static void main(String[] args) {
		Object o = concurrentHashMap.putIfAbsent("1", "2");
		System.out.println(o);//null
		Object o1 = concurrentHashMap.putIfAbsent("1", "111");
		System.out.println(o1);//2
		System.out.println(concurrentHashMap.get("1"));//1
	}
}
