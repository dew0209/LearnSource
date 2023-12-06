package base.class3.beanlife;

import base.class3.beanlife.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-06 19:48
 * @description
 */
public class MainTest {

	@Test
	public void beanLife(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器创建完成===");
		app.close();
	}

}
