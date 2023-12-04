package com.dew.godx.other.concurrent.blockqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Title:
 * @Description: 存放到队列的元素
 * @Author: LvLu
 * @Date: 2022-11-01 20:17
 * @param null
 * @return: 
 * @throws: 
 **/
public class ItemVo<T> implements Delayed {

	private Long activeTime;//到期时间，毫秒
	private T date;//实际的数据
	//activeTime是一个过期时长，使用纳秒
	public ItemVo(Long activeTime, T date) {
		//将传入的时长转换为超时的时长
		this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime,TimeUnit.MILLISECONDS) + System.nanoTime();
		this.date = date;
	}
	//返回元素的剩余时间
	@Override
	public long getDelay(TimeUnit unit) {
		long d = unit.convert(this.activeTime - System.nanoTime(),TimeUnit.NANOSECONDS);
		return d;
	}
	//按照剩余时间进行排序
	@Override
	public int compareTo(Delayed o) {
		long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if(d == 0)return 0;
		return d > 0 ? 1 : -1;
	}

	/**
	 * @return date
	 */
	public T getDate() {
		return date;
	}



	public static String handleInterfaceDate(String invoiceDate) {
		String invoiceStr = null;

		//过滤字符串中的汉字
		invoiceDate = invoiceDate.replaceAll("[\\u4e00-\\u9fa5]", "");
		if(invoiceDate.contains("-")){
			invoiceDate = invoiceDate.replace("-","");
		}
		if(invoiceDate.length()>8){
			invoiceStr = invoiceDate.substring(0,8);
		}else{
			invoiceStr = invoiceDate;
		}
		return invoiceStr;
	}

	public static void main(String[] args) {
		System.out.println(handleInterfaceDate("2023年07月20日"));
	}
}
