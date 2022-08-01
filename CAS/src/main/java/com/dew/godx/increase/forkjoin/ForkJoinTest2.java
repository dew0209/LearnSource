package com.dew.godx.increase.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * fork/join：分治。将一个大问题拆分成小问题。
 * 		一般的处理流程：
 * 				规模为N的问题，N < 阈值，直接处理，N > 阈值，将N分解为k个小规则子问题，子问题互相独立，与原问题形式相同。
 * 				将子问题的结合并得到原问题的解。
 * 		工作觅取：这个线程任务结束的早，会去其他线程拿任务做。把任务做完，再把结果返回过去。减少大任务的执行时间。---> 执行压榨cpu资源
 *
 * 计算数组和，同步需要返回值
 * 遍历寻找指定的文件，异步不需要返回值
 */
public class ForkJoinTest2 {
	//RecursiveAction无返回值
	private static class FileTask extends RecursiveAction {

		private File path;

		public FileTask(File path) {
			this.path = path;
		}

		@Override
		protected void compute() {
			List<FileTask> subTasks = new ArrayList<>();
			File[] files = path.listFiles();
			if(files != null){
				for(File file : files){
					if(file.isDirectory()){
						subTasks.add(new FileTask(file));
					}else {
						if(file.getAbsolutePath().endsWith("txt")){
							System.out.println("文件：" + file.getAbsolutePath());
						}
					}
				}
				if(subTasks.size() != 0){
					for(FileTask file : invokeAll(subTasks)){
						//file.join();//阻塞，等待子线程完成,感觉不加也行，毕竟子任务和当前任务没啥关系
					}
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		FileTask fileTask = new FileTask(new File("D:/"));
		//异步
		pool.execute(fileTask);
		System.out.println("Task is running......");
		Thread.sleep(1);
		int otherWork = 0;
		for(int i = 0;i < 100;i++){
			otherWork += i;
		}
		System.out.println("Main Thread done sth......,otherWork = " + otherWork);
		fileTask.join();//阻塞
		System.out.println("Task end!!!");
	}
}
