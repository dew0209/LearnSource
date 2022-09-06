package com.dew.godx.other.concurrent.concurrenthashmap;

/**
 * 演示位运算
 */
public class Bit {
	public static void main(String[] args) {
		int data = 4;
		//打印出二进制数值
		System.out.println(Integer.toBinaryString(data));
		System.out.println(Integer.toBinaryString(6));
		System.out.println(4 << 1);//8
		System.out.println(4 >> 1);//2
		System.out.println(-4 >>> 1);//2147483646
		System.out.println(4 & 6);//4
		System.out.println(4 | 6);
		System.out.println(~4);//~n = - n - 1
		System.out.println(4 ^ 6);//2

		//tips a:取模操作 a % (2 ^ n) 等价于 a & (2 ^ n - 1)
		System.out.println(345 % 16);
		System.out.println(345 & 15);

	}
}
