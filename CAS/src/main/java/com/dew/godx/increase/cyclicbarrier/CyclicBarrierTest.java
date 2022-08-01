package com.dew.godx.increase.cyclicbarrier;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：让一组线程到达一个屏障，被阻塞，一直到组内最后一个线程达到屏障时，屏障开放，所有被阻塞的线程会继续运行
 * CyclicBarrier(int parties, Runnable barrierAction):屏障开放的时候，会执行barrierAction里面定义的任务
 * CyclicBarrier(int parties):线程组的个数
 * 和CountDownLatch的区别：
 * 		CyclicBarrier可以有结束时候的执行任务（barrierAction）
 * 	    CountDownLatch可以在一次线程里面多次减一，两者的粒度不同
 * 	    CountDownLatch由CountDownLatch决定是否放行，但是CyclicBarrier是由线程组决定的，因为一个线程可以多次countDown，但是只能一次CyclicBarrier.await
 * 	    CountDownLatch决定因素更加偏向于外部线程决定是否执行，CyclicBarrier是线程组内部自己决定
 */
public class CyclicBarrierTest {
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new CollectThread());
	private static ConcurrentHashMap<String,Long> resultMap = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		for(int i = 0;i < 5;i++){
			Thread thread = new Thread(new SubThread());
			thread.start();
		}
	}
	//负责屏障开放之后的事情
	private static class CollectThread implements Runnable{
		@Override
		public void run() {
			StringBuffer result = new StringBuffer();
			for (Map.Entry s : resultMap.entrySet()) {
				result.append("[" + s.getValue() + "]");
			}
			System.out.println("result = " + result);
			System.out.println("do other business...");
		}
	}
	//工作线程
	private static class SubThread implements Runnable{
		@Override
		public void run() {
			long id = Thread.currentThread().getId();
			resultMap.put(Thread.currentThread().getId() + "",id);
			Random r = new Random();
			try {
				if(r.nextBoolean()){
					Thread.currentThread().sleep(1000 + id);
					System.out.println("Thread_" + id + " ... do something");
				}
				System.out.println(id + "... is await");
				cyclicBarrier.await();
				Thread.currentThread().sleep(1000 + id);
				System.out.println("Thread_" + id + " ... do its business");
			}catch (Exception e){

			}

		}
	}
}
