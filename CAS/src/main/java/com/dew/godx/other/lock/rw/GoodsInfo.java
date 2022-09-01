package com.dew.godx.other.lock.rw;

/**
 *
 */
public class GoodsInfo {
	private String name;
	private double totalMoney;//总销售额
	private int storeNumber;//库存数

	public GoodsInfo(String name, double totalMoney, int storeNumber) {
		this.name = name;
		this.totalMoney = totalMoney;
		this.storeNumber = storeNumber;
	}

	/**
	 * @return totalMoney
	 */
	public double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @return storeNumber
	 */
	public int getStoreNumber() {
		return storeNumber;
	}
	public void changeNumber(int sellNumber){
		this.totalMoney += sellNumber * 25;
		this.storeNumber -= sellNumber;
	}
}
