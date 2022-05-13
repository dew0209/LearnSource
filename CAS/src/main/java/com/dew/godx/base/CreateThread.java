package com.dew.godx.base;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThread {
    /**
     * java线程天生就是多线程的，启动一个单线程，往往jvm会启动其他监控的线程
     */
    @Test
    public void run01(){
        //虚拟机管理线程的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos = threadMXBean.dumpAllThreads(false, false);
        //会有多个线程被打印出来
        for (ThreadInfo info : infos) {
            System.out.println("线程id：" + info.getThreadId()+",名字"+info.getThreadName());
        }
    }

    private class UseThread extends Thread{
        @Override
        public void run() {
            System.out.println("继承Thread类");
        }
    }
    /**
     * 创建线程方式1：继承Thread类
     */
    @Test
    public void run02(){
        UseThread t1 = new UseThread();
        t1.start();
        System.out.println("----end----");
    }

    public class UseRun implements Runnable{
        @Override
        public void run() {
            System.out.println("实现runnable接口");
        }
    }
    /**
     * 创建线程方式2：实现Runnable接口
     */
    @Test
    public void run03() throws InterruptedException {
        Thread t1 = new Thread(new UseRun());
        t1.start();
        System.out.println("----end----");
    }
    private class UseCall implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("实现Callable接口，有返回值");
            return "callResult";
        }
    }

    /**
     * 线程创建方式3：实现Callable接口
     */
    @Test
    public void run04() throws ExecutionException, InterruptedException {
        //Callable接口需要FutureTask进行转换
        FutureTask<String> task = new FutureTask<>(new UseCall());
        Thread t1 = new Thread(task);
        t1.start();
        System.out.println("----end  1----");
        String res = task.get();//同步阻塞的，需要等待线程执行完毕
        System.out.println(res);
        System.out.println("----end  2----");

    }
}