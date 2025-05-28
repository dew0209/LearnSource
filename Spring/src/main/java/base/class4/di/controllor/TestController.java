package base.class4.di.controllor;

import base.class4.di.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 *
 * @author LvLu
 * @className TestController
 * @date 2024-01-02 16:39
 * @description
 */
@Controller
public class TestController {

	@Autowired
	private TestService testService;
}
