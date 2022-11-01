package com.dew.godx.other.concurrent.blockqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className PutOrder
 * @date 2022-11-01 20:39
 * @description
 */
public class PutOrder implements Runnable{

	private DelayQueue<ItemVo<Order>> queue;

	public PutOrder(DelayQueue<ItemVo<Order>> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		//5s到期
		Order order = new Order("TB111", 11);
		ItemVo<Order> itemVo = new ItemVo<Order>(5000L,order);
		queue.offer(itemVo);
		System.out.println("订单5s后到期：" + order.getOrderNo());

		//8s到期
		Order orderJD = new Order("JD222", 11);
		ItemVo<Order> itemVoJD = new ItemVo<Order>(8000L,order);
		queue.offer(itemVoJD);
		System.out.println("订单8s后到期：" + orderJD.getOrderNo());
	}
}
