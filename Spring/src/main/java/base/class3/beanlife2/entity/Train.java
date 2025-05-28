package base.class3.beanlife2.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author LvLu
 * @className Train
 * @date 2023-12-06 20:31
 * @description
 */
@Component
public class Train implements InitializingBean, DisposableBean {

	public Train(){
		System.out.println("===创建Train对象===");
	}

	//当bean销毁时，会把单实例bean进行销毁
	@Override
	public void destroy() throws Exception {
		System.out.println("===train 销毁方法===");
	}

	//当beanFactory创建好对象，且把bean所有属性设置好之后，会调用这个方法，相当于初始化方法
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("===train 相当于初始化方法===");
	}
}
