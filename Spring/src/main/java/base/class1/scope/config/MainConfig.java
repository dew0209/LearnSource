package base.class1.scope.config;

import base.class1.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-04 21:00
 * @description
 */
@Configuration
public class MainConfig {

	//默认是单实例
	@Bean
	public Dog dog(){
		return new Dog("xiaoHong",22);
	}

	/**
	 * prototype:多实例，IOC容器启动的时候，IOC容器启动并不会去调用方法创建对象，而是每次获取的时候才会调用方法创建对象
	 * singleton:单实例（默认），IOC启动的时候会调用方法创建对象并放到IOC容器中，以后每次获取的就是直接从容器中拿的一个bean
	 * request:主要针对web应用，递交一次请求创建一次实例
	 * session:同一个session创建了一个实例
	 **/
	@Scope("prototype")
	@Bean
	public Dog dog01(){
		return new Dog("xiaoHong",22);
	}
}
