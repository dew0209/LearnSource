package com.dew.godx.other.concurrentsecurity.deadlock.bank;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className ITransfer
 * @date 2022-11-03 20:35
 * @description
 */
public interface ITransfer {
	void transFer(UserAccount from,UserAccount to,int amount) throws InterruptedException;
}
