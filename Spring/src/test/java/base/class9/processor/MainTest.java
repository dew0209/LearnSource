package base.class9.processor;

import base.class9.processor.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainConfig
 * @date 2024-02-22 15:18
 * @description
 */
public class MainTest {

	@Test
	public void run(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		app.close();
	}

}
