package base.class5.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 *
 * @author LvLu
 * @className MainConfig
 * @date 2024-01-08 19:38
 * @description
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"base.class5.aop.service"})
public class MainConfig {
}
