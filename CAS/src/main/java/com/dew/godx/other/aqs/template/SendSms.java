package com.dew.godx.other.aqs.template;

/**
 *
 */
public class SendSms extends SendCustom{
	@Override
	public void to() {
		System.out.println("to Mark");
	}

	@Override
	public void from() {
		System.out.println("from Bill");
	}

	@Override
	public void content() {
		System.out.println("Hello World");
	}

	@Override
	public void send() {
		System.out.println("send sms");
	}

	public static void main(String[] args) {
		SendCustom custom = new SendSms();
		custom.sendMessage();
	}
}
