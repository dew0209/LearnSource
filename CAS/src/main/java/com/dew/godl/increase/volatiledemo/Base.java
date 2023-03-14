package com.dew.godl.increase.volatiledemo;

/**
 * volatile：
 * 		被volatile修改：
 * 			1.可见性
 * 			2.有序性：排序要求，禁止重排
 *		内存语义：
 *			1.当写一个volatile变量时，jmm会把该线程对应的本地内存中的共享变量值立即刷新回主内存中
 *			2.当读一个volatile变量时，jmm会把该线程对应的本地内存设置为无效，重新回到主内存中读取最新共享变量
 *			3.所以volatile的写内存语义是直接刷新到主内存中，读的内存语义是直接从主内存中读取
 *		如何保证：
 *			内存屏障：
 *				也称内存栅栏，屏障指令等，是一类同步屏障指令，是cpu或编译器在对内存随机访问的操作节点中的一个同步点
 *				使得此点之前的所有读写操作都执行后才可以开始执行此点之后的操作，避免代码重排序。内存屏障其实就是一种jvm指令，
 *				java内存模型的重排规则会要求java编译器在生成jvm指令时插入特定的内存屏障指令，通过这些内存屏障指令，volatile
 *				实现了java内存模型中的可见性和有序性。但是volatile无法保证原子性
 *			内存屏障之前的所有写操作都要回写到主内存
 *			内存屏障之后的所有读操作都能获得内存屏障之前的所有写操作的最新结果（实现了可见性）
 *			写屏障(store)：告诉处理器在写屏障之前将所有存储在缓存中的数据同步到主内存，也就是说当看到store屏障指令，就必须把该指令之前所有写入指令执行完毕才能继续往下执行
 *			读屏障(load)：处理器在读屏障之后的读操作，都在读操作之后执行，也就是说在load屏障指令之后就能够保证后面的读取数据指令一定能够读取到最新的数据
 *		因此重排序时，不允许把内存屏障之后的指令重排序到内存屏障之前。一句话说：对一个volatile变量的写，先行发生于任意后续对这个volatile变量的读，也叫做写后读
 *		内存屏障分类：
 *			粗分：
 *				读屏障：在读指令之前插入读屏障，让工作区或者cpu高速缓存当中的缓存数据失效，重新回到主内存中获取最新的数据
 *				写屏障：在写指令之后插入写屏障，强制把写缓冲区的数据刷回到主内存中
 *				public native void loadFence();读
 *     			public native void storeFence();写
 *     			public native void fullFence();	读写混合
 *     		细分：
 *     			loadload：load1 loadload load2 保证load1的读取操作在load2及后续读取操作之前执行
 *     			storestore：stroe1 sotrestore store2 在store2及其后的写操作执行前，保证store1的写操作已刷新到主内存中
 *     			loadsotre：load1 loadstroe store2	在store2及其后的写操作执行前，保证load1的读操作已读取结束
 *     			storeload：stroe1 stroeload load2	保证store1的写操作已刷新到主内存之后，load2及其后的读操作才能执行
 * 	  当第一个操作为volatile读时，不论第二个操作是什么（这里指的是普通读写和volatile读写），都不能重排，这个操作保证了volatile读之后的操作不会被重排到volatile读之前
 * 	  当第二个操作为volatile写时，不论第一个操作是什么，都不能重排，这个操作保证了volatile写之前的操作不会被重排到volatile写之后
 * 	  当第一个操作为volatile写时，第二个操作为volatile读时，不能重排
 * 	  读写屏障插入策略：
 *		读屏障：
 *			volatile读 loadload loadstore 普通读 普通写
 *			在每个volatile读操作的后面插入一个loadload屏障：禁止处理器把上面的volatile读与下面的普通读重排序
 *			在每个volatile读操作的后面插入一个loadstore屏障：禁止处理器把上面的volatile读与下面的普通写重排序
 *		写屏障：
 *			普通读 普通写 storestore volatile写 storeload
 *			在每个volatile写操作的前面插入一个storestore屏障：可以保证在volatile写之前，其前面的所有普通写操作都已经刷新到主内存中
 *			在每个volatile写操作的后面插入一个storeload屏障：作用是避免volatile写与后面可能有的volatile读/写操作重排序
 **/
public class Base {

}
