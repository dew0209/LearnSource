package com.dew.godx.increase.forkjoin;

import sun.rmi.runtime.Log;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork/join：分治。将一个大问题拆分成小问题。
 * 		一般的处理流程：
 * 				规模为N的问题，N < 阈值，直接处理，N > 阈值，将N分解为k个小规则子问题，子问题互相独立，与原问题形式相同。
 * 				将子问题的结合并得到原问题的解。
 * 		工作觅取：这个线程任务结束的早，会去其他线程拿任务做。把任务做完，再把结果返回过去。减少大任务的执行时间。---> 执行压榨cpu资源
 *
 *
 * 统计数组的和
 */
public class ForkJoinTest {
	//RecursiveTask有返回值
	private static class SumTask extends RecursiveTask<Integer> {
		//阈值
		private static final int THRESHOLED = MakeArray.ARRAY_LENGTH / 10;
		private int[] src;
		private int fromIndex;
		private int toIndex;

		public SumTask(int[] src, int fromIndex, int toIndex) {
			this.src = src;
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
		}

		@Override
		protected Integer compute() {
			if(toIndex - fromIndex < THRESHOLED){
				int count = 0;
				for(int i = fromIndex;i <= toIndex;i++){
					try {
						//模拟业务
						//Thread.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					count += src[i];
				}
				return count;
			}else {
				int mid = (toIndex + fromIndex) / 2;
				SumTask left = new SumTask(src,fromIndex,mid);
				SumTask right = new SumTask(src,mid + 1,toIndex);
				invokeAll(left,right);
				return left.join() + right.join();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		int src[] = MakeArray.makeArray();
		SumTask innerFind = new SumTask(src,0,src.length - 1);
		long start = System.currentTimeMillis();
		pool.invoke(innerFind);
		System.out.println("Task is running......");
		long end = System.currentTimeMillis();
		//fork/join不一定比传统快，因为上下文切换需要时间。但是数据量越大，fork/join的优势越明显。小数据量的时候，传统往往更加占据优势。
		System.out.println("The count is " + innerFind.join() + "  spend time: " + (end - start));
		Thread.sleep(5000);
		//传统
		start = System.currentTimeMillis();
		int count = 0;
		for(int i = 0;i < src.length;i++){
			//模拟业务
			//Thread.sleep(1);
			count += src[i];
		}
		end = System.currentTimeMillis();
		System.out.println("The count is " + count + " spend time：" + (end - start));
	}
}
