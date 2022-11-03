package com.dew.godx.other.completionservice;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className WorkTask
 * @date 2022-11-03 18:47
 * @description
 */
public class WorkTask implements Callable<Integer> {

	private String name;

	public WorkTask(String name) {
		this.name = name;
	}

	@Override
	public Integer call() throws Exception {
		int sleepTime = new Random().nextInt(100);
		try {
			Thread.sleep(sleepTime);
		}catch (Exception e){
			e.printStackTrace();
		}
		return sleepTime;
	}
}
