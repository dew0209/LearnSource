package base.class9.processor.config;

import base.class3.beanlife2.entity.Train;
import base.class3.beanlife3.entity.Jeep;
import base.class5.autowired.entity.Moon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2024-02-22 15:00
 * @description
 */
@Configuration
@ComponentScan("base.class9.processor.processor")
public class MainConfig {

	@Bean
	public Jeep jeep(){
		return new Jeep();
	}

	@Bean(initMethod = "init",destroyMethod = "destroy")
	public Moon moon(){
		return new Moon();
	}

	@Bean
	public Train train(){
		return new Train();
	}




}
