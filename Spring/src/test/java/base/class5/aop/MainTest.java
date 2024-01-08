package base.class5.aop;

import base.class5.aop.config.MainConfig;
import base.class5.aop.service.CalcService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-08 19:38
 * @description
 */
public class MainTest {

	@Test
	public void div(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		CalcService bean = app.getBean(CalcService.class);
		System.out.println(bean.div(10,2));;
	}

}
