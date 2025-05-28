package base.class1.scope;

import base.class1.entity.Dog;
import base.class1.scope.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-04 21:02
 * @description
 */
public class MainTest {
	/**
	 * @Title: scopeTest
	 * @Description:
	 * @Author: LvLu
	 * @Date: 2023-12-04 21:04
	 * @return: void
	 * @throws:
	 **/
	@Test
	public void scopeTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] baneNames = app.getBeanDefinitionNames();
		for (String baneName : baneNames) {
			System.out.println(baneName);
		}
		Dog dog1 = (Dog)app.getBean("dog");
		Dog dog2 = (Dog)app.getBean("dog");
		//@Bean默认是单实例
		System.out.println(dog1 == dog2);
	}

	/**
	 * @Title: scopeTest2
	 * @Description: 测试多实例
	 * @Author: LvLu
	 * @Date: 2023-12-04 21:20
	 * @return: void
	 * @throws:
	 **/
	@Test
	public void scopeTest2(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] baneNames = app.getBeanDefinitionNames();
		for (String baneName : baneNames) {
			System.out.println(baneName);
		}
		Dog dog1 = (Dog)app.getBean("dog01");
		Dog dog2 = (Dog)app.getBean("dog01");
		//多实例，false
		System.out.println(dog1 == dog2);
	}
}
