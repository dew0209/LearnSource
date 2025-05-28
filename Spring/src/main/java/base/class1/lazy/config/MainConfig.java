package base.class1.lazy.config;

import base.class1.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-04 21:26
 * @description
 */
@Configuration
public class MainConfig {

	/**
	 * 非懒加载：针对单实例bean：默认在容器启动的时候创建对象
	 * 懒加载：容器启动的时候不创建对象，仅当第一次使用（获取）bean的时候才创建被初始化
	 **/
	@Lazy
	@Bean
	public Dog dog(){
		System.out.println("给容器中添加dog");
		return new Dog("xiaoZi",23);
	}

}
