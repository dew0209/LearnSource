package base.class1;

import base.class1.config.MainConfig;
import base.class1.entity.Dog;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className SpringHelloWorld
 * @date 2023-12-04 19:31
 * @description
 */
public class SpringHelloWorld {

	/**
	 * @Title: xmlTest
	 * @Description:  测试xml导入bean的方式
	 * @Author: LvLu
	 * @Date: 2023-12-04 19:35
	 * @return: void
	 * @throws:
	 **/
	@Test
	public void xmlTest(){
		//加载配置文件，注册bean
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		//从IOC容器拿，IOC容器类似一个很大的map
		Dog dog = (Dog)app.getBean("dog");
		System.out.println(dog);
	}

	/**
	 * @Title: configTest
	 * @Description: 配置类注入bean的方式
	 * @Author: LvLu
	 * @Date: 2023-12-04 19:42
	 * @return: void
	 * @throws:
	 **/
	@Test
	public void configTest(){
		//配置文件注册bean
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		//从IOC容器拿
		Dog dog = (Dog)app.getBean("dog01");
		System.out.println(dog);
		//获取类型为Dog的所有的bean的id集合
		String[] beanNamesForType = app.getBeanNamesForType(Dog.class);
		for (String s : beanNamesForType) {
			System.out.println(s);
		}
	}

}
