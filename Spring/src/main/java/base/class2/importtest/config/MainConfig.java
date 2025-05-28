package base.class2.importtest.config;

import base.class1.entity.Cat;
import base.class1.entity.Dog;
import base.class1.entity.Person;
import base.class2.importtest.importbeandefinitionregistrar.CustomImportBeanDefinitionRegistrar;
import base.class2.importtest.importselector.CustomImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-05 19:36
 * @description
 */
@Configuration
//@Import(要导入到容器中的组件)：容器会自动注册这个组件，bean的id为全类名
//ImportSelector:是一个接口，返回需要导入到容器的组件的全类名数组
//ImportBeanDefinitionRegistrar：可以手动添加组件到IOC容器，所有bean的注册可以BeanDefinitionRegistry
@Import(value = {Person.class, Cat.class, CustomImportSelector.class, CustomImportBeanDefinitionRegistrar.class})
public class MainConfig {

	@Bean("xiaoWang")
	public Dog xiaoWang(){
		return new Dog("xiaoWang",25);
	}

}
