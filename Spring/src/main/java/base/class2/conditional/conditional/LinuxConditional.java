package base.class2.conditional.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 *
 *
 * @author LvLu
 * @className LinuxConditional
 * @date 2023-12-05 19:22
 * @description
 */
public class LinuxConditional implements Condition {

	/**
	 * @Title: matches
	 * @Description:
	 * @Author: LvLu
	 * @Date: 2023-12-05 19:23
	 * @param context 判断条件可以使用的上下文条件
	 * @param metadata 注解的信息
	 * @return: boolean
	 * @throws:
	 **/
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//获得bean工厂
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//获得环境变量
		Environment environment = context.getEnvironment();
		String osName = environment.getProperty("os.name");
		System.out.println("osName===>" + osName);
		if(!StringUtils.isEmpty(osName) && osName.contains("Lin")){
			return true;
		}
		return false;
	}
}
