package base.class9.processor.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className JamesBeanFactoryProcessor
 * @date 2024-02-22 15:01
 * @description
 */
@Component
public class JamesBeanFactoryProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("---JamesBeanFactoryProcessor---postProcessBeanFactory()");
		//拿到所有的bean定义信息，已经加载到beanFactory，但是bean实例还没创建
		int count = beanFactory.getBeanDefinitionCount();
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		System.out.println("当前BeanFactory中有 " + count + " 个bean");
		System.out.println(Arrays.toString(beanNames));
	}
}
