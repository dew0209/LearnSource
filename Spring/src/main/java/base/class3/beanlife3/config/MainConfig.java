package base.class3.beanlife3.config;

import base.class3.beanlife3.entity.Jeep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-06 20:41
 * @description
 */
@Configuration
public class MainConfig {
	@Bean
	public Jeep Jeep(){
		return new Jeep();
	}

}
