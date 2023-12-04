package base.class1.config;

import base.class1.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-04 19:38
 * @description 配置类注册bean
 */
//告诉Spring这是一个配置类
@Configuration
public class MainConfig {

	/**
	 * @Title: dog
	 * @Description: 注册一个名为dog的bean，不指定id的话，默认为方法名
	 * @Author: LvLu
	 * @Date: 2023-12-04 19:40
	 * @return: base.class1.entity.Dog
	 * @throws:
	 **/
	@Bean(value = "dog01")
	public Dog dog(){
		return new Dog("xiaoBai",20);
	}


}
