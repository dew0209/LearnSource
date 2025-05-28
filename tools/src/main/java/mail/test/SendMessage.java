package mail.test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Properties;

/**
 *
 *
 * @author LvLu
 * @className SendMessage
 * @date 2024-08-09 9:24
 * @description
 */

@FunctionalInterface
interface InvokeBusinessFun<T> {

	/**
	 * @title invokeBusiness
	 * @description 执行业务
	 * @author chenhai
	 * @date 2023-08-23 15:14
	 * @param t 传入参数
	 * @return: R 返回结果
	 */
	String invokeBusiness(T t);

}


public class SendMessage {
	public static void main(String[] args) throws Exception {
		try {
			String test = test(1, res -> {
				int i = 1 / 0;
				return null;
			});
			System.out.println(test);
		}catch (Exception e){
			System.out.println("111111");
		}
	}

	public static String test(int a,InvokeBusinessFun<InterFaceTest> v){
		String test = "11";
		InterFaceTest test1 = new InterFaceTest();
		v.invokeBusiness(test1);
		return "111";
	}
}

class InterFaceTest {
	String aa21;
}