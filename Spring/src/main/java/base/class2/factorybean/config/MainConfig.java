package base.class2.factorybean.config;

import base.class1.entity.Dog;
import base.class2.factorybean.factorybean.CustomFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-05 20:28
 * @description
 */
@Configuration
public class MainConfig {

	@Bean
	public Dog dog(){
		return new Dog();
	}

	@Bean
	public CustomFactoryBean customFactoryBean(){
		return new CustomFactoryBean();
	}

}
