package base.class5.aware;

import base.class5.aware.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-08 19:01
 * @description
 */
public class MainTest {

	@Test
	public void awareTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println(app);
	}

}
