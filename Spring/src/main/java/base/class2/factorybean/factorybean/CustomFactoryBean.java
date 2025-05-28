package base.class2.factorybean.factorybean;

import base.class1.entity.Monkey;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 *
 * @author LvLu
 * @className CustomFactoryBean
 * @date 2023-12-05 20:28
 * @description
 */
public class CustomFactoryBean implements FactoryBean<Monkey> {

	@Override
	public Monkey getObject() throws Exception {
		System.out.println("注册bean Monkey~~~");
		return new Monkey();
	}

	@Override
	public Class<?> getObjectType() {
		return Monkey.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
