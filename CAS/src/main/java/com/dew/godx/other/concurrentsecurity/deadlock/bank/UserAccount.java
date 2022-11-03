package com.dew.godx.other.concurrentsecurity.deadlock.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className UserAccount
 * @date 2022-11-03 20:30
 * @description 模拟银行转账
 */
public class UserAccount {
	private final String name;
	private int money;

	private final Lock lock = new ReentrantLock();

	public UserAccount(String name, int money) {
		this.name = name;
		this.money = money;
	}

	/**
	 * @return money
	 */
	public int getAmount() {
		return money;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return lock
	 */
	public Lock getLock() {
		return lock;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserAccount{");
		sb.append("name='").append(name).append('\'');
		sb.append(", money=").append(money);
		sb.append('}');
		return sb.toString();
	}
	//转入资金
	public void addMoney(int amount){
		money = money + amount;
	}
	//转出资金
	public void flyMoney(int amount){
		money = money - amount;
	}
}
