package base.class3.beanlife3;

import base.class3.beanlife3.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-06 20:41
 * @description
 */
public class MainTest {
	@Test
	public void jsrBeanLife(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器创建完成===");
		app.close();
	}
}
