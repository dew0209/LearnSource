package base.class4.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 *
 * @author LvLu
 * @className Plane
 * @date 2024-01-02 14:15
 * @description
 */
public class Plane implements ApplicationContextAware {
	ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//applicationContext传进来
		this.applicationContext = applicationContext;
	}
}
