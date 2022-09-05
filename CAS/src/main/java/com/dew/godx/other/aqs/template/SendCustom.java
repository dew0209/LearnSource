package com.dew.godx.other.aqs.template;

import java.util.Date;

/**
 *	发送邮件给用户的基类
 */
public abstract class SendCustom {

	public abstract void to();

	public abstract void from();

	public abstract void content();

	public void date(){
		System.out.println(new Date());
	}

	public abstract void send();

	//模板方法
	public void sendMessage(){
		to();
		from();
		content();
		date();
		send();
	}

}
