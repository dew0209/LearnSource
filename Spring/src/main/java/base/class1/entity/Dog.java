package base.class1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Dog
 * @date 2023-12-04 19:23
 * @description
 */
@Data
@NoArgsConstructor
@ToString
public class Dog {

	/**
	 * 小狗的名字
	 **/
	private String name;

	/**
	 * 小狗的年龄
	 **/
	private Integer age;

	public Dog(String name,Integer age){
		System.out.println("---开始创建对象---");
		this.name = name;
		this.age = age;
	}

}
