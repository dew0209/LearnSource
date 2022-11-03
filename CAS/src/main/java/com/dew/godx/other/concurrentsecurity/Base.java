package com.dew.godx.other.concurrentsecurity;

import java.util.concurrent.CompletionService;

/**
 * 类的线程安全定义：
 * 		如果在多线程情况下使用这个类，不管多线程如何使用和调度这个类，这个类总是表示出正确的行为。这个类就是线程安全的
 * 	操作的原子性，内存的可见性
 * 	会在多个线程之间共享状态的时候，就会出现线程不安全
 * 		1.栈封闭：所以的变量都是在方法内部声明的，这些变量都处于栈封闭状态
 * 		2.无状态：没有任何成员变量的类，就叫做无状态类
 * 		3.让类不可变：
 * 			a.让状态不可变，final修饰，但是注意，final修饰一个引用，引用内部或许是可以变化的，final Person p = new Person();p.setXXX(xxx);是可以的
 * 						简单来说，就是所有的成员变量应该是私有的，同样的，只要有可能，所有的成员变量应该加上final关键字
 * 			b.根本就不提供任何可供修改成员变量的地方，同时成员变量也不做为方法的返回值
 * 		4.volatile
 * 			保证类的可见性，最适合一个线程写，多个线程读的场景
 * 	 	5.加锁和cas
 * 	 	6.安全的发布：尽量返回深拷贝副本或者基本数据类型
 * 	 	7.ThreadLocal
 * Servlet：不是线程安全的类，在需求上，很少有共享的需求。另外，接收到了请求，返回应答的时候，都是由一个线程负责
 *
 * 线程不安全引发的问题：
 * 		死锁：
 * 			是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，他们都将无法推进下去。此时系统处于死锁状态或系统产生了死锁。（获取锁的顺序不一致）
 * 			竞争资源一定是多余1个，同时小于等于竞争的线程数。资源只有一个，只会产生激烈的竞争，不会产生死锁。
 *
 * 使用jps -m 和 jstack xxxx可以分析死锁
 *
 *
 *
 */
public class Base {

	private final int a;
	private final int b;
	private final User user;//这里就不能保证线程安全，修改方案：将引用内部也用final修饰。★

	/**
	 * @return a
	 */
	public int getA() {
		return a;
	}

	/**
	 * @return b
	 */
	public int getB() {
		return b;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	public Base(int a, int b, User user) {
		this.a = a;
		this.b = b;
		this.user = user;
	}

	public static void main(String[] args) {
		User u = new User();
		Base base = new Base(1, 2, u);
		u.setName("张三");
		System.out.println(base.getUser().getName());//张三
	}
}

class User {
	private String name;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}