package com.dew.godx.other.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 */
public class UserAtomicArray {
	static int[] value = new int[]{1,2,3};
	static AtomicIntegerArray ai = new AtomicIntegerArray(value);//value.clone();

	public static void main(String[] args) {
		System.out.println(ai.getAndSet(0,3));
		System.out.println(ai.get(0));
		System.out.println(value[0]);//value本身不会改变
	}
}
