package base.class4.value.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 *
 * @author LvLu
 * @className Bird
 * @date 2024-01-02 15:08
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bird {
	//使用@Value进行赋值：1.基本字符 2.spring el表达式 3.可以读取配置文件
	@Value("james")
	private String name;
	@Value("#{20-2}")
	private Integer age;
	@Value("${bird.color}")
	private String color;
}
