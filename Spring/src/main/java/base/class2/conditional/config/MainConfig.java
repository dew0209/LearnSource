package base.class2.conditional.config;

import base.class1.entity.Dog;
import base.class2.conditional.conditional.LinuxConditional;
import base.class2.conditional.conditional.WindowsConditional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-05 19:15
 * @description
 */
@Configuration
public class MainConfig {

	@Bean("xiaoQing")
	@Conditional(WindowsConditional.class)
	public Dog xiaoQing(){
		System.out.println("添加xiaoQing");
		return new Dog("xiaoQing",23);
	}

	@Bean("xiaoLv")
	@Conditional(LinuxConditional.class)
	public Dog xiaoLv(){
		System.out.println("添加xiaoLv");
		return new Dog("xiaoLv",24);
	}

	@Bean("xiaoChen")
	public Dog xiaoChen(){
		System.out.println("添加xiaoChen");
		return new Dog("xiaoChen",25);
	}

}
