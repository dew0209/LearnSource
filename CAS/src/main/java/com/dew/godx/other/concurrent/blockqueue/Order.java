package com.dew.godx.other.concurrent.blockqueue;

/**
 *
 *
 * @author LvLu
 * @className Order
 * @date 2022-11-01 20:38
 * @description
 */
public class Order {
	private final String orderNo;//订单的编号
	private final double orderMoney;//订单的金额

	public Order(String orderNo, double orderMoney) {
		this.orderNo = orderNo;
		this.orderMoney = orderMoney;
	}

	/**
	 * @return orderMoney
	 */
	public double getOrderMoney() {
		return orderMoney;
	}

	/**
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
}
