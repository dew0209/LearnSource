package com.dew.godx.other.concurrent.blockqueue;

import java.util.concurrent.DelayQueue;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className FetchOrder
 * @date 2022-11-01 20:43
 * @description
 */
public class FetchOrder implements Runnable{

	private DelayQueue<ItemVo<Order>> queue;

	public FetchOrder(DelayQueue<ItemVo<Order>> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ItemVo<Order> res = queue.take();
				Order order = res.getDate();
				System.out.println("get from queue：" + order.getOrderNo());
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
