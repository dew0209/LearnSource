package base.class5.autowired.entity;

import org.springframework.stereotype.Component;

/**
 * ”“
 *
 * @author LvLu
 * @className Moon
 * @date 2024-01-08 18:40
 * @description
 */
@Component
public class Moon {
	public Moon(){
		System.out.println("Moon construct...");
	}
	public void init(){
		System.out.println("Moon...init...");
	}
	public void destroy(){
		System.out.println("Moon...destroy...");
	}
}
