package base.class4.beanpostprocessor.config;


import base.class4.beanpostprocessor.Plane;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-06 19:42
 * @description bean的生命周期
 */
@Configuration
public class MainConfig {


	@Bean
	public Plane plane(){
		return new Plane();
	}

}
