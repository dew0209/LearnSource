package base.class5.autowired.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Sun
 * @date 2024-01-08 18:41
 * @description
 */
@Component
@Data
public class Sun {

	private Moon moon;


	public Sun(@Autowired Moon moon){
		this.moon = moon;
		System.out.println("constructor...");
	}

	/**
	 * @param moon
	 */
	@Autowired
	public void setMoon(Moon moon) {
		this.moon = moon;
	}
}
