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
