package com.dew.godx.other.lock.rw;

/**
 *
 */
public class UseSyn implements GoodsService{

	private GoodsInfo goodsInfo;

	public UseSyn(GoodsInfo goodsInfo){
		this.goodsInfo = goodsInfo;
	}

	@Override
	public synchronized GoodsInfo getNum() {
		try {
			Thread.currentThread().sleep(5);
		}catch (Exception e){
		}
		return this.goodsInfo;
	}

	@Override
	public synchronized void setNum(int number) {
		try {
			Thread.currentThread().sleep(5);
		}catch (Exception e){
		}
		goodsInfo.changeNumber(number);
	}
}
