package base.class3.beanlife2;

import base.class3.beanlife2.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-06 20:32
 * @description
 */
public class MainTest {

	@Test
	public void disposableBeanInitializingBeanTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器创建完成===");
		app.close();
	}

}
