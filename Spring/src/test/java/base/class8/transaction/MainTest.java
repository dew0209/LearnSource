package base.class8.transaction;

import base.class8.transaction.config.MainConfig;
import base.class8.transaction.service.OrderService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-02-20 16:38
 * @description
 */
public class MainTest {

	@Test
	public void transaction(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		OrderService bean = app.getBean(OrderService.class);
		bean.insert();
	}

}
