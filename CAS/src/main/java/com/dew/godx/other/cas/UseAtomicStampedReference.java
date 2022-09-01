package com.dew.godx.other.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 */
public class UseAtomicStampedReference {
	static AtomicStampedReference<String> asr = new AtomicStampedReference<>("Mark",0);
	public static void main(String[] args) throws InterruptedException {
		final int oldStamp = asr.getStamp();//初始的版本号
		final String oldReference = asr.getReference();
		System.out.println(oldReference + "======" + oldStamp);
		Thread rightThread = new Thread(()->{
			System.out.println(Thread.currentThread().getName() + "当前变量值：" + oldReference + ",当前版本戳：" + oldStamp + "-" +
					asr.compareAndSet(oldReference,oldReference + "Java",oldStamp,oldStamp + 1));
		});
		rightThread.start();
		rightThread.join();
		Thread wrongThread = new Thread(()->{
			String reference = asr.getReference();
			System.out.println(Thread.currentThread().getName() + "当前变量值：" + reference + ",当前版本戳：" + asr.getStamp() + "-" +
					asr.compareAndSet(reference,reference + "C",oldStamp,oldStamp + 1));
		});
		wrongThread.start();
		wrongThread.join();
		System.out.println(asr.getReference() + "====" + asr.getStamp());
	}
}
