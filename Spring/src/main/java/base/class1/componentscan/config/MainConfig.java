package base.class1.componentscan.config;

import base.class1.componentscan.controller.OrderController;
import base.class1.componentscan.service.OrderService;
import base.class1.componentscan.typefilter.CustomTypeFilter;
import base.class1.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2023-12-04 19:54
 * @description  includeFilters需要搭配useDefaultFilters = false使用
 * 				 excludeFilters要要搭配useDefaultFilters = true使用
 * 				 FilterType.CUSTOM需要搭配useDefaultFilters=false
 */
@Configuration
@ComponentScans({
		@ComponentScan(value = "base.class1.componentscan",excludeFilters = {
				@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {CustomTypeFilter.class})
		},useDefaultFilters = true)
})
public class MainConfig {
	@Bean
	public Dog dog(){
		return new Dog("xiaoHuang",21);
	}
}
