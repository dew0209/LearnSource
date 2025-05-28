package base.class4.di;

import base.class4.di.config.MainConfig;
import base.class4.di.dao.TestDao;
import base.class4.di.service.TestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-02 16:42
 * @description
 */
public class MainTest {
	@Test
	public void diTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		TestService testService = (TestService)app.getBean(TestService.class);
		TestDao testDao = (TestDao)app.getBean("testDao");
		 System.out.println(testDao);
		testService.println();//地址是一样的，是同一个testDao对象
	}
}
