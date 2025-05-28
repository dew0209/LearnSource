package base.class1.lazy;

import base.class1.entity.Dog;
import base.class1.lazy.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2023-12-04 21:28
 * @description
 */
public class MainTest {

	@Test
	public void lazyTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println("===容器创建完成===");
		Dog dog = (Dog)app.getBean("dog");
		System.out.println(dog);
	}

}
