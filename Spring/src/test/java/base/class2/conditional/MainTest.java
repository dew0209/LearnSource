package base.class2.conditional;

import base.class2.conditional.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-05 19:18
 * @description
 */
public class MainTest {

	/**
	 * @Title: conditionalTest
	 * @Description: conditional有选择性的注册bean。如果操作系统是windows 就让xiaoQing注册，如果是linux，就让xiaoLv入库
	 * @Author: LvLu
	 * @Date: 2023-12-05 19:18
	 * @return: void
	 * @throws: 
	 **/
	@Test
	public void conditionalTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器创建完成===");

	}
}
