package base.class9.processor.processor;

import base.class5.autowired.entity.Moon;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author LvLu
 * @className JamesBeanDefinitionRegistryPostProcessor
 * @date 2024-02-22 16:25
 * @description
 */
@Component
public class JamesBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("JamesBeanDefinitionRegistryPostProcessor---postProcessBeanDefinitionRegistry---bean的数量：" + registry.getBeanDefinitionCount());
		RootBeanDefinition rdb = new RootBeanDefinition(Moon.class);
		registry.registerBeanDefinition("rdb",rdb);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("JamesBeanDefinitionRegistryPostProcessor---postProcessBeanFactory---bean的数量：" + beanFactory.getBeanDefinitionCount());
	}
}
