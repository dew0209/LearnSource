package base.class1.componentscan;

import base.class1.componentscan.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-04 20:02
 * @description
 */
public class MainTest {

	/**
	 * @Title: componentscanTest
	 * @Description: 测试@componentScan注解
	 * @Author: LvLu
	 * @Date: 2023-12-04 20:03
	 * @return: void
	 * @throws:
	 **/
	@Test
	public void componentscanTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] baneNames = app.getBeanDefinitionNames();
		for (String baneName : baneNames) {
			System.out.println(baneName);
		}
	}

}
