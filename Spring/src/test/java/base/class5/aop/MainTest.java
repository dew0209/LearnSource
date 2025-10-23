package base.class5.aop;

import base.class5.aop.config.MainConfig;
import base.class5.aop.service.CalcService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

/**
 *
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
		int div = bean.div(40, 0);
		System.out.println(div);
	}

}
