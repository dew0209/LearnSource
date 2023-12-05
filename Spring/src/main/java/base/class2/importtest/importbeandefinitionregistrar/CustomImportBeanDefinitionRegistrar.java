package base.class2.importtest.importbeandefinitionregistrar;

import base.class1.entity.Pig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className CustomImportBeanDefinitionRegistrar
 * @date 2023-12-05 20:15
 * @description
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * @Title: registerBeanDefinitions
	 * @Description:
	 * @Author: LvLu
	 * @Date: 2023-12-05 20:16
	 * @param importingClassMetadata 当前类的注解信息
	 * @param registry BeanDefinition注册类，把所有需要添加到容器中的bean加入
	 * @return: void
	 * @throws:
	 **/
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean f1 = registry.containsBeanDefinition("base.class1.entity.Cat");
		boolean f2 = registry.containsBeanDefinition("base.class1.entity.Person");
		//同时存在cat和person就注册一个pig
		if(f1 && f2){
			//对于我们新注册的bean，要对bean进行封装
			RootBeanDefinition beanDefinition = new RootBeanDefinition(Pig.class);
			registry.registerBeanDefinition("pig",beanDefinition);
		}
	}
}
