package com.dew.godl.increase.cas.atom;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 50个线程，每个线程100w次，总点赞数出来
 *
 * LongAdder为什么快：
 * 		继承Striped64
 * 			base：类似于AtomicLong中全局的value值。在没有竞争情况下数据直接累加到base上，或者cells扩容时，也需要将数据写到base上
 * 			collide：表示扩容意向，false，一定不会扩容，true可能会扩容
 * 			casCellsBusy()：通过cas修改cellsBusy的值，cas成功代表获取锁，返回true
 * 			NCPU：当前计算机cpu数量，cell数组扩容时会使用到
 * 			getProbe()：获取当前线程的hash值
 * 			advanceProbe()：重置当前线程的hash值
 * 		Striped64里面有个cell
 *
 * 		AtomicLong  CAS	自旋，空转率增加，cpu负担加大
 * 		LongAdder	cas分散窗口 多窗口 res = base + cell数组 上述两者全部为计算结果
 * 		原理：
 * 			LongAdder的基本思路就是分散热点，将value值分散到一个cell数组，不同线程会命中到数组的不同槽中，各个线程只对自己槽中的那个值进行cas操作，
 * 			这样热点就被分散了，冲突的概率就减小很多了，如果要获取真正的long值，只要将各个槽中的变量值累加返回。
 *
 * 		步骤：
 * 			1.最初无竞争时只更新base
 * 			2.如果更新base失败后，首次新建一个cell[]数组
 * 			3.当多个线程竞争同一个cell比较激烈的时候，可能就要对cell进行扩容了
 *
 */
public class Base6 {

	public static final int _1W = 10000;
	public static final int THREAD_SUM = 50;

	public static void main(String[] args) throws InterruptedException {
		ClickNumber clickNumber = new ClickNumber();
		long startTime;
		long endTime;

		CountDownLatch latch1 = new CountDownLatch(50);
		CountDownLatch latch2 = new CountDownLatch(50);
		CountDownLatch latch3 = new CountDownLatch(50);
		CountDownLatch latch4 = new CountDownLatch(50);
		startTime = System.currentTimeMillis();
		for(int i = 0;i < THREAD_SUM;i++){
			new Thread(()->{
				try {
					for(int j = 0;j < 100 * _1W;j++){
						clickNumber.clickBySynchronized();
					}
				}finally {
					latch1.countDown();
				}
			}).start();
		}
		latch1.await();
		endTime = System.currentTimeMillis();
		System.out.println("---cost time: " + (endTime - startTime) + " Synchronized：" + clickNumber.number);

		startTime = System.currentTimeMillis();
		for(int i = 0;i < THREAD_SUM;i++){
			new Thread(()->{
				try {
					for(int j = 0;j < 100 * _1W;j++){
						clickNumber.clickByAtomicLong();
					}
				}finally {
					latch2.countDown();
				}
			}).start();
		}
		latch2.await();
		endTime = System.currentTimeMillis();
		System.out.println("---cost time: " + (endTime - startTime) + " AtomicLong：" + clickNumber.atomicLong.get());

		startTime = System.currentTimeMillis();
		for(int i = 0;i < THREAD_SUM;i++){
			new Thread(()->{
				try {
					for(int j = 0;j < 100 * _1W;j++){
						clickNumber.clickByLongAdder();
					}
				}finally {
					latch3.countDown();
				}
			}).start();
		}
		latch3.await();
		endTime = System.currentTimeMillis();
		System.out.println("---cost time: " + (endTime - startTime) + " LongAdder：" + clickNumber.longAdder.sum());

		startTime = System.currentTimeMillis();
		for(int i = 0;i < THREAD_SUM;i++){
			new Thread(()->{
				try {
					for(int j = 0;j < 100 * _1W;j++){
						clickNumber.clickByLongAccumulator();
					}
				}finally {
					latch4.countDown();
				}
			}).start();
		}
		latch4.await();
		endTime = System.currentTimeMillis();
		System.out.println("---cost time: " + (endTime - startTime) + " LongAccumulator：" + clickNumber.longAccumulator.get());
 		/**
		 * 结果
		 * ---cost time: 1646 Synchronized：50000000
		 * ---cost time: 834 AtomicLong：50000000
		 * ---cost time: 190 LongAdder：50000000
		 * ---cost time: 99 LongAccumulator：50000000
		 **/

	}
}

class ClickNumber{
	int number = 0;
	public synchronized void clickBySynchronized(){
		number++;
	}
	AtomicLong atomicLong = new AtomicLong(0);
	public void clickByAtomicLong(){
		atomicLong.getAndIncrement();
	}

	LongAdder longAdder = new LongAdder();
	public void clickByLongAdder(){
		longAdder.increment();
	}

	LongAccumulator longAccumulator = new LongAccumulator((x,y)-> x + y,0);

	public void clickByLongAccumulator(){
		longAccumulator.accumulate(1);
	}


}