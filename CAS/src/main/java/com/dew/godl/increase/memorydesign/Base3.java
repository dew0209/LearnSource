package com.dew.godl.increase.memorydesign;

import org.openjdk.jol.info.ClassLayout;

/**
 * 压缩指针的影响
 *
 */
public class Base3 {
	public static void main(String[] args) {
		//java -XX:+PrintCommandLineFlags -version 查看所有参数        -XX:+UseCompressedClassPointers 压缩  默认启动 （12 + 4） 4就是对齐填充
		//上述表示开启了类型指针的压缩，以节约空间，假如不压缩
		Object o = new Object();
		System.out.println(ClassLayout.parseInstance(o).toPrintable());//关闭压缩 -XX:-UseCompressedClassPointers

	}
}
