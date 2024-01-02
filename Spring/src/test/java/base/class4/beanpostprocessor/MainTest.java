package base.class4.beanpostprocessor;

import base.class4.beanpostprocessor.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-02 14:17
 * @description
 */
public class MainTest {

	@Test
	public void beanPostprocessorTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器创建完成===");
		app.close();
	}

}
