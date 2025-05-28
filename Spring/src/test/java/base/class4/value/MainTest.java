package base.class4.value;

import base.class4.value.config.MainConfig;
import base.class4.value.entity.Bird;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-02 15:10
 * @description
 */
public class MainTest {

	@Test
	public void value(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		for (String name : app.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		Bird bird = (Bird)app.getBean("bird");
		System.out.println(bird);
		System.out.println("容器创建完成");
		System.out.println(app.getEnvironment().getProperty("bird.color"));
		app.close();
	}

}
