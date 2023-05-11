package com.dew.godl.increase.memorydesign;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 *
 * jol
 *
 */
public class Base1 {
	public static void main(String[] args) {
		//vm的细节详情情况
		//System.out.println(VM.current().details());
		//所有对象分配的字节都是8的整数倍
		//System.out.println(VM.current().objectAlignment());
		Object o = new Object();
		System.out.println(ClassLayout.parseInstance(o).toPrintable());
		/*
			OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
			0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
			4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
			8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
			12     4        (loss due to the next object alignment)

			OFFSET：偏移量，也就是到这个字段位置所占用的byte数
			SIZE：后面类型的字节大小
			TYPE：是Class中定义的类型
			DESCRIPTION：DESCRIPTION是类型的描述
			VALUE：VALUE是TYPE在内存中的值


		*/
		System.out.println(ClassLayout.parseInstance(new CustomerBase()).toPrintable());
		/*
			OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
			0     4           (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
			4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
			8     4           (object header)                           d2 cb 00 f8 (11010010 11001011 00000000 11111000) (-134165550)
			12     4       int CustomerBase.id                           0
			16     1   boolean CustomerBase.flag                         false
			17     7           (loss due to the next object alignment)
			Instance size: 24 bytes
		*/

	}
}

class CustomerBase{
	int id;
	boolean flag = false;
}