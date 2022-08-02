package com.dew.godx.increase.exchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Exchanger：
 * 		两个线程间的数据交换
 */
public class ExchangerTest {
	private static final Exchanger<Set<String>> exchange =
			new Exchanger<>();

	public static void main(String[] args) {
		new Thread(()->{
			Set<String> setA = new HashSet<>();
			try {
				setA.add("aaa");
				setA = exchange.exchange(setA);
				System.out.println("setA = " + Arrays.toString(setA.toArray()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(()->{
			Set<String> setB = new HashSet<>();
			try {
				setB.add("AAA");
				setB = exchange.exchange(setB);
				System.out.println("setB = " + Arrays.toString(setB.toArray()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
