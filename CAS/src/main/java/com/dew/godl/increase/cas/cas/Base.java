package com.dew.godl.increase.cas.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS：compare and swap 比较交换
 * 		它包含三个数：内存位置，预期原值及更新值
 * 		执行cas操作时，将内存位置的值与预期原值比较
 * 			如果相匹配，那么处理器会自动将该位置更新为新值
 * 			如果不匹配，处理器不做任何操作，多个线程同时执行cas操作只会有一个成功
 * 		cas是一条cpu的原子指令（cmpxchg指令，原语的执行必须是连续的，在执行过程中不能被中断，不会造成数据不一样的问题，依赖于硬件），不会造成所谓的数据不一致问题，Unsafe提供的cas方法（如compareAndSwapXXX）底层实现即为cpu指令cmpxchg
 * 			执行cmpxchg指令的时候，会判断当前系统是否为多核系统，如果是就给总线加锁，只会有一个线程会对总线加锁成功，加锁成功之后会执行cas操作，也就是说cas的原子性
 * 			实际上是cpu实现独占的。比起synchronized,这里排他的时间要短很多，所以在多线程情况下性能比较好
 *		Unsafe中的：
 *			compareAndSwapInt(Object var1, long var2, int var4, int var5);
 *			var1：表示要操作的对象
 *			var2：表示要操作对象中属性地址的偏移量
 *			var4：表示需要修改数据的期望值
 *			var5：表示需要修改为的新值
 *		Unsafe：
 *          unsafe是cas的核心类，由于java方法无法直接访问底层系统，需要本地（native）方法来直接访问，unsafe相当于一个后面，基于该类可以直接操作特定内存的数据。
 *          	unsafe类存在于sum.misc包中，其内部方法操作可以像c的指针一样直接操作内存，因为java中cas操作的执行依赖于unsafe类的方法。
 *         	valueOffset
 * 				valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
 * 				valueOffset表示该变量值在内存中的偏移地址，因为unsafe就是根据内存偏移地址获取数据的
 * 			变量value用volatile修饰，保证了多线程之间的内存可见性
 *			总结：
 *				cas是靠硬件实现的从而在硬件层面提升效率，最底层还是交给硬件来保证原子性和可见性
 *				实现方式是基于硬件平台的汇编指令，在intel的cpu上（x86），使用的是cmpxchg指令
 *				核心思想就是：比较要更新的变量值v和预期值e，相等才会将v的值设为新值n，如果不相等自旋再来
 *
 *	cas带来的问题：
 *		1.长时间等待
 *		2.aba问题：版本号 AtomicStampedReference
 */
public class Base {
	public static void main(String[] args) {
		/*AtomicInteger num = new AtomicInteger(5);
		boolean b = num.compareAndSet(5, 2020);
		System.out.println(b);*/

		Book javaBook = new Book(1, "java");
		AtomicStampedReference<Book> ref = new AtomicStampedReference<>(javaBook, 1);
		System.out.println(ref.getReference() + "\t" + ref.getStamp());
		Book mysql = new Book(2,"mysql");
		boolean b = ref.compareAndSet(javaBook, mysql, ref.getStamp(), ref.getStamp() + 1);
		System.out.println(b);
		System.out.println(ref.getReference() + "\t" + ref.getStamp());
		boolean c = ref.compareAndSet(mysql, javaBook, ref.getStamp(), ref.getStamp() + 1);
		System.out.println(c);
		System.out.println(ref.getReference() + "\t" + ref.getStamp());


	}
}

class Book{
	private int id;
	private String bookName;

	public Book(int id, String bookName) {
		this.id = id;
		this.bookName = bookName;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Book{");
		sb.append("id=").append(id);
		sb.append(", bookName='").append(bookName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}