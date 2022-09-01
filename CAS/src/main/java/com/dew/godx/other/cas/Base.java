package com.dew.godx.other.cas;

/**
 * cas（Compare And Swap）
 * 利用了现在处理器都支持的cas指令，循环这个指令，知道成功为止。
 *
 * sync是基于阻塞锁的机制，1.被阻塞的线程优先级很高怎么办，2.拿到锁的线程一直不释放锁怎么办，3.大量竞争，消耗cpu，同时带来死锁或者其他安全
 *
 * cas的原理：
 * 		cas指令是原子操作的，指令级别保证这是一个原子操作
 * 		三个运算符：
 * 			一个内存地址v，一个期望值a，一个新值b
 * 			基本思路：
 * 				如果内存v的值和a相同，那么将a替换为b
 * 				如果内存v的值和a相同，那么不做任何操作，自旋（循环）进行重复操作。
 * 		注意：
 * 			cas再语言方面不做任何处理，在底层指令做了处理
 * 		cas带来的问题：
 * 			1.aba问题：一个值开始是a，中间变成b，最后又变成a
 * 				线程1准备进行cas操作，a->b，但是这时候线程2将a改成b又改成了a，此时线程1继续运行。
 * 				解决方案：版本号
 * 			2.开销问题：cas操作长期不成功，cpu不断循环
 * 			3.只能保证一个共享变量的原子操作
 * 	jdk中原子操作类的使用：
 * 		更新基本类型类：AtomicBoolean,AtomicInteger,AtomicLong,...
 * 		更新数组类：AtomicIntegerArray,...
 * 		更新引用类型类：AtomicReference,AtomicMarkableReference(解决aba问题，有没有动过),AtomicStampedReference(解决aba问题，被动过几次)...
 * 		更新字段类：AtomicReferenceFieldUpdater,AtomicIntegerFieldUpdater,...
 */
public class Base {

}
