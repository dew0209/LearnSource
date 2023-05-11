package com.dew.godl.increase.memorydesign;

/**
 * 分代年龄
 */
public class Base2 {
	public static void main(String[] args) {
		//-XX:MaxTenuringThreshold=16
		//分代gc最大15
		/*
				Error: Could not create the Java Virtual Machine.
				Error: A fatal exception has occurred. Program will exit.
				MaxTenuringThreshold of 16 is invalid; must be between 0 and 15
			*/
	}
}
