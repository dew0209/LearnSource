package com.dew.godx.other.lock.condition;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Test
 * @date 2022-09-01 23:35
 * @description
 */
public class Test {
	private static ExpressCond express = new ExpressCond(0,ExpressCond.CITY);
	private static class CheckKm extends Thread{
		@Override
		public void run() {
			express.waitKm();
		}
	}
	private static class CheckSite extends Thread{
		@Override
		public void run() {
			express.waitSite();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0;i < 3;i++){
			new CheckSite().start();
		}
		for(int i = 0;i < 3;i++){
			new CheckKm().start();
		}
		Thread.currentThread().sleep(100);
		express.changeKm();
	}
}
