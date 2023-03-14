package com.dew.godl.increase.volatiledemo;

/**
 * 指令禁重排
 * 	存在数据依赖关系，禁止重排
 * 		数据依赖性：若两个操作访问同一个变量，且这两个操作中有一个为写操作，此时两操作间就存在数据依赖性。但是不同处理器和不同线程之间的数据依赖性不会被编译器和处理器考虑
 * 	不存在数据依赖关系，可以重排
 * 	但重排后的指令不能改变原有的串行语义
 * 	写后写 写后读 读后写，这三种重排序，程序的执行结果就会被改变
 *
 * 	使用场景：
 * 		1.单一赋值可以，但是符合运算赋值不可以
 * 			volatile int a = 0;
 * 			volatile boolean flag = false;
 * 		2.状态标志，判断业务是否结束
 * 		3.开销较低的读，写锁策略
 * 		4.DCL双端锁的发布
 *
 */
public class Demo3 {

	int i = 0;
	volatile boolean flag = false;
	public void write(){
		i = 2;
		flag = true;
	}

	public void read(){
		if(flag){
			System.out.println("---i = " + i);
		}
	}

	public static void main(String[] args) {

	}


	//dcl双端volatile优化
	private volatile static Object object;

	public static Object getInstance(){
		if(object == null){
			synchronized (Object.class){
				if(object == null){
					//因为new对象是三步，1.分配空间 2.初始化对象 3.地址赋值给object 但是由于23可能会重排，导致出现部分null指针
					object = new Object();
				}
			}
		}
		return object;
	}


}
