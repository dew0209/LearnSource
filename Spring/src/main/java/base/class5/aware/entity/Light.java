package base.class5.aware.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 *
 *
 * @author LvLu
 * @className Light
 * @date 2024-01-08 18:59
 * @description
 */
@Component
public class Light implements BeanNameAware, ApplicationContextAware, EmbeddedValueResolverAware {

	private ApplicationContext applicationContext;

	private String name;

	@Override
	public void setBeanName(String name) {
		System.out.println("get beanName：" + name);
		this.name = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("get ApplicationContext：" + applicationContext);
		this.applicationContext = applicationContext;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String str = "hello${os.name},计算#{3-2}";
		String s = resolver.resolveStringValue(str);
		System.out.println(s);

	}
}
