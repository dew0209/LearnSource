package base.class8.transaction;

import base.class8.transaction.config.MainConfig;
import base.class8.transaction.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-02-20 16:38
 * @description
 */
public class MainTest {

	@Test
	public void transaction(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		OrderService bean = app.getBean(OrderService.class);
		bean.insert();
		app.close();
	}

}