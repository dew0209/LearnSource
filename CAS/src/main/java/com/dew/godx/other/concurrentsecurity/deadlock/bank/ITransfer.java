package com.dew.godx.other.concurrentsecurity.deadlock.bank;

/**
 *
 *
 * @author LvLu
 * @className ITransfer
 * @date 2022-11-03 20:35
 * @description
 */
public interface ITransfer {
	void transFer(UserAccount from,UserAccount to,int amount) throws InterruptedException;
}
