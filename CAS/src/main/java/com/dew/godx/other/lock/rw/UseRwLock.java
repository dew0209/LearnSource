package com.dew.godx.other.lock.rw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class UseRwLock implements GoodsService{
	private GoodsInfo goodsInfo;

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private final Lock getLock = lock.readLock();
	private final Lock setLock = lock.writeLock();

	public UseRwLock(GoodsInfo goodsInfo){
		this.goodsInfo = goodsInfo;
	}

	@Override
	public GoodsInfo getNum() {
		getLock.lock();
		try {
			Thread.currentThread().sleep(5);
			return goodsInfo;
		}catch (Exception e){

		}finally {
			getLock.unlock();
		}
		return null;
	}

	@Override
	public void setNum(int number) {
		setLock.lock();
		try {
			Thread.currentThread().sleep(5);
			goodsInfo.changeNumber(number);
		}catch (Exception e){
		}finally {
			setLock.unlock();
		}
	}
}
