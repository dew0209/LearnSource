package base.class1;

/**
 * spring基础组件及其使用
 * 		Spring是一款开源轻量级框架，是为了解决企业应用程序开发复杂性而创建的
 * 		Spring致力于解决JavaEE的各层解决方案，而不仅仅于某一层的方案
 * Spring--->Ioc--->DI:
 *		老的方式（new 对象）：
 *			Dog dog = new Dog();
 *		新的方式（IOC管理）：
 *			application.xml---> <bean id="dog" class="xxx.xx.Dog"></bean>
 *		新的方式（注解）：
 * 			{@code @Bean}等等
 *	Spring的目标：
 *		1.让现有的技术更容易使用
 *		2.促进良好的编程习惯
 *	Spring体系结构：
 *		1.Spring Core：Spring核心，它是框架最基础的部分，提供IOC和依赖注入特性
 *		2.Spring Context：Spring上下文容器，它是BeanFactory功能加强的一个子接口
 *		3.Spring Web：它提供Web应用开发的支持
 *		4.Spring MVC：它针对Web中MVC思想的实现
 *		5.Spring DAO：提供对JDBC抽象层，简化了JDBC编码，同时，编码更具有健壮性
 *		6.Spring ORM：它支持用于流行的ORM框架的整合，比如：Spring + MyBatis，Spring + JDO的整合等等
 *		7.Spring AOP：面向切面编程，它提供了与AOP联盟兼容的编程实现
 *	pom坐标：
 * 		{@code
 * 			<dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-context</artifactId>
 *             <version>5.3.26</version>
 *          </dependency>
 * 		}
 *
 */
public class SpringHelloWorld {

}
