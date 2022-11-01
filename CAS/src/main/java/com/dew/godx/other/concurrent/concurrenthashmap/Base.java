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
 * 		put：
 *			取消segment，直接用一个table来保存数据[node数组]，减小锁的粒度以便于减少并发冲突，增加红黑树，链表+红黑树的数据结构，某一个链表挂的元素超过8个的时候，链表就会转化为红黑树
 *			使用Node存储
 *			sizeCtl：负数表示进行初始化或者扩容  -1正在初始化 -N表示有N-1个线程在扩容 0表示table还未被初始化，正数表示初始化或者是下一次进行扩容的阈值
 *			TreeNode用在红黑树下面 TreeBin是实际放在红黑树下面的，代表了这个树的根
 *			初始化：
 *				只是给成员变量复制，put时进行实际数组的填充
 *		get：
 *			1.table数组中这个元素是不是就是我要的
 *			2.在树中寻找
 *			3.在链表中寻找
 *		扩容操作：
 *			transFer进行实际的扩容操作，table的大小也是翻倍的形式，有一个并发扩容的机制
 *		size：
 *			估计的大概数量
 *		弱一致性
 *		由红黑树转链表 6个
 *		由链表转红黑树 8个
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
