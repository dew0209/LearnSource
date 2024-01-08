package base.class5.autowired;

import base.class5.autowired.config.MainConfig;
import base.class5.autowired.entity.Moon;
import base.class5.autowired.entity.Sun;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-08 18:39
 * @description
 */
public class MainTest {

	@Test
	public void autowired(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器构建完成===");
		Moon moon = app.getBean(Moon.class);
		Sun sun = app.getBean(Sun.class);
		System.out.println(moon);
		System.out.println(sun.getMoon());
	}

}
