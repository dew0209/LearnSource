package com.dew.godx.base;

import org.junit.Test;

/**
 * 线程停止：
 *  1.自然秩序完
 *  2.抛出异常
 *  3.stop()强制释放,resume() suspend()线程来不及释放;  已废弃,资源可能来不及释放，导致资源一致被占用，导致死锁等等问题。
 * 如何手动停止：
 *  协作式管理：让线程有足够的时间去释放资源
 *  1.interrupt();中断当前线程，并不是强制关闭，只是通知该线程，和该线程说，你该中断了。会不会立即停止？由该线程自己处理。中断标志位为true
 *  2.isInterrupted();判断当前线程是否处于中断状态，也就是判断是否有人给我打招呼
 *  3.static interrupted();判断当前线程是否处于中断状态，和上面方法的区别，这个方法会把中断标志为改为false。
 *
 * 线程的生命周期：
 *  1.新建  new 出来一个线程，还未被start
 *  2.就绪  start()被调用  注意：start()被调用不代表运行了，时间片轮转机制
 *  3.运行  获得执行权
 *  4.阻塞
 *  5.死亡
 *  看导图CAS--->线程状态图
 * yield()和wait()的区别：
 *  yield()会释放，但是下一次仍然有机会获取到执行权
 *  wait()会释放，下一次不会获取到执行权，因为休眠了
 *
 *
 */
public class StatusThread {
    private class EndThread extends Thread{
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            //协作式，需要自己手动去管理
            while (!isInterrupted()){
                System.out.println(name + " is run!!!");
            }
            System.out.println(name + " interrupt flag is" + isInterrupted());
        }
    }
    private class EndRunnableThread implements Runnable{
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            //协作式，需要自己手动去管理
            while (!Thread.currentThread().isInterrupted()){
                System.out.println(name + " is run!!!");
            }
            System.out.println(name + " interrupt flag is" + Thread.currentThread().isInterrupted());
        }
    }
    //常见的
    @Test
    public void run01() throws InterruptedException {
        EndThread t1 = new EndThread();
        t1.start();
        Thread.sleep(20);
        t1.interrupt();
    }
    //如果抛出异常
    private class EndThreadExe extends Thread{
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            //协作式，需要自己手动去管理
            while (!isInterrupted()){
                try {
                    /**
                     * 不要使用自己搞一个变量去中断，阻塞状态是不会生效的，也就是说
                     *      使用boolean flag = false,flag = true;代替isInterrupted() + interrupt()
                     *      如果此时处于休眠状态，是不会被中断的，而使用isInterrupted机制是可以的。
                     *      使用此种情况，会导致不及时。
                     */
                    Thread.sleep(100);
                    //抛出InterruptedException会将中断标志位重置为false，需要手动interrupt一下
                } catch (InterruptedException e) {
                    System.out.println(name + " interrupt flag is  " + isInterrupted());
                    //这个需要自己手动调用，再次中断，别忘记了！！！
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(name + " is run!!!");
            }
            System.out.println(name);
        }
    }

    @Test
    public void run02() throws InterruptedException {
        EndThreadExe t1 = new EndThreadExe();
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }

    private class ThreadRun03 extends Thread{
        @Override
        public void run() {
            int i = 90;
            while (i > 0){
                try {
                    Thread.sleep(100);
                    System.out.println("I am " + Thread.currentThread().getName() + " and now the i = " + i--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * run和start的区别
     */
    @Test
    public void run03() throws InterruptedException {
        ThreadRun03 t1 = new ThreadRun03();
        t1.setName("BeCalled");
        //t1.run();//打印main 这只是一个普通的方法
        t1.start();//打印BeCalled  在调用start()才是一个真正的线程  会和os进行关联
        Thread.sleep(20000);
    }
    /**
     * 线程的优先级 1~10 却省为5 越高分配的时间片越多 setPriority();
     * 注意：在不同的os中，有所区别，有的os可能没有实现这个玩意
     */
    @Test
    public void run04(){
        ThreadRun03 t1 = new ThreadRun03();
        t1.setPriority(1);
    }

    private class ThreadRun05 extends Thread{

        @Override
        public void run() {
            try{
                while (!isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName() + "interrupt flag is " + isInterrupted());
            }finally {
                //不一定会被执行哦
                System.out.println("---------finally------");
            }
        }
    }


    /**
     * 守护线程  和主线程同生共死的，主线程指在那个线程里面start
     */
    @Test
    public void run05() throws InterruptedException {
        ThreadRun05 t1 = new ThreadRun05();
        t1.setDaemon(true);//在start方法之前
        t1.start();
    }
}
