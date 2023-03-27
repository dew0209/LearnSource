package com.dew.godl.increase.cas.atom;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组类型原子类：
 *		AtomicIntegerArray
 *		AtomicLongArray
 *		AtomicReferenceArray
 */
public class Base1 {
	public static void main(String[] args) {
		AtomicIntegerArray arr = new AtomicIntegerArray(new int[]{1,2,3,4,5});
		for(int i = 0;i < arr.length();i++){
			System.out.println(arr.get(i));
		}
		System.out.println();
		int tmpInt = 0;
		tmpInt = arr.getAndSet(0,1122);
		System.out.println(tmpInt + "\t" + arr.get(0));
		tmpInt = arr.getAndIncrement(0);
		System.out.println(tmpInt + "\t" + arr.get(0));
	}
}
