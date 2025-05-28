package base.class4.di.config;

import base.class4.di.dao.TestDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2024-01-02 16:05
 * @description
 */
@Configuration
@ComponentScan({"base.class4.di.controllor","base.class4.di.dao","base.class4.di.service"})
public class MainConfig {
	//首选项 默认首选的bean
	//@Primary
	@Bean("testDao")
	public TestDao testDao(){
		TestDao testDao = new TestDao();
		testDao.setFlag("2");
		return testDao;
	}
}
