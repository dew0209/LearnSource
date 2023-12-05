package base.class2.importtest;

import base.class2.importtest.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-05 19:37
 * @description
 */
public class MainTest {

	@Test
	public void importTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("容器创建完成");
		String[] names = app.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println("===>" + name);
		}
	}

}
