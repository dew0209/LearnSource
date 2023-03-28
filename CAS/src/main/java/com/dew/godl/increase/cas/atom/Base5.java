package com.dew.godl.increase.cas.atom;


import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 原子操作增强类：
 * 		DoubleAccumulator
 * 		DoubleAdder
 * 		LongAccumulator
 * 		LongAdder
 *
 */
public class Base5 {
	public static void main(String[] args) {
		//只能用来计算加法，且从0开始计算
		LongAdder longAdder = new LongAdder();
		longAdder.increment();
		longAdder.increment();
		longAdder.increment();
		System.out.println(longAdder.sum());
		//提供了自定义的函数操作
		LongAccumulator longAccumulator = new LongAccumulator((x,y)->x + y,0);
		longAccumulator.accumulate(1);
		longAccumulator.accumulate(3);
		System.out.println(longAccumulator.get());
	}
}
