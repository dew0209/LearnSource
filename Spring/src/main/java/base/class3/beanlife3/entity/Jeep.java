package base.class3.beanlife3.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 *
 * @author LvLu
 * @className Jeep
 * @date 2023-12-06 20:42
 * @description
 */
public class Jeep {
	public Jeep(){
		System.out.println("===Jeep constructor===");
	}
	//@PostConstruct：在Bean创建完成，且属于复制完成后进行初始化，属于jdk规范的注解
	@PostConstruct
	public void init(){
		System.out.println("===Jeep 创建完成 @PostConstruct===");
	}

	//@PreDestory：在bean被移除之前进行通知，在容器销毁之前进行清理工作
	@PreDestroy
	public void destroy(){
		System.out.println("===Jeep 销毁完成 @PreDestroy===");
	}
}
