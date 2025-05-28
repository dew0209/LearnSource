package base.class3.beanlife.config;

import base.class1.entity.Dog;
import base.class3.entity.Bike;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-06 19:42
 * @description bean的生命周期
 */
@Configuration
public class MainConfig {


	@Bean
	public Dog dog(){
		return new Dog("xiaoZi",25);
	}

	/**
	 * initMethod：初始化方法
	 * destroyMethod：销毁方法
	 * 注意：
	 *      对于单实例bean，可以正常调用初始化和销毁方法
	 *      对于多实例bean，容器只负责初始化，但不会管理bean，容器关闭时不会调用销毁方法
	 **/
	@Bean(initMethod = "init",destroyMethod = "destory")
	//@Scope("prototype")
	public Bike bike(){
		return new Bike();
	}

}
