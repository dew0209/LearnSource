package base.class4.value.config;

import base.class4.value.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-01-02 15:07
 * @description
 */
@Configuration
@PropertySource(value = {"classpath:/test.properties"})
public class MainConfig {

	@Bean
	public Bird bird(){
		return new Bird();
	}

}
