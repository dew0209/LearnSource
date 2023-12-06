package base.class2.factorybean;

import base.class2.factorybean.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-05 20:31
 * @description
 */
public class MainTest {

	@Test
	public void factoryBeanTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("容器创建完成");
		for (String beanDefinitionName : app.getBeanDefinitionNames()) {
			System.out.println("===>" + beanDefinitionName);
		}
		//注意类型是base.class1.entity.Monkey
		Object bean1 = app.getBean("customFactoryBean");
		Object bean2 = app.getBean("customFactoryBean");
		Object bean3 = app.getBean("&customFactoryBean");
		System.out.println(bean1.getClass());
		System.out.println(bean3.getClass());
		System.out.println(bean1 == bean2);
	}

}
