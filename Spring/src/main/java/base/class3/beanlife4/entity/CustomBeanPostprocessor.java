package base.class3.beanlife4.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author LvLu
 * @className CustomBeanPostprocessor
 * @date 2023-12-06 20:57
 * @description
 */
@Component
public class CustomBeanPostprocessor implements BeanPostProcessor {

	//在init-method之后
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("===CustomBeanPostprocessor postProcessAfterInitialization===" + beanName + "===" + bean);
		return bean;
	}

	//在init-method之前
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("===CustomBeanPostprocessor postProcessBeforeInitialization===" + beanName + "===" + bean);
		return bean;
	}
}
