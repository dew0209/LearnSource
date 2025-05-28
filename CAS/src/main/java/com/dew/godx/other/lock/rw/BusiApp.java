package com.dew.godx.other.lock.rw;

import java.util.Random;

/**
 *
 *
 * @author LvLu
 * @className BusiApp
 * @date 2022-09-01 22:55
 * @description
 */
public class BusiApp {
	static final int readWriteRatio = 10;//读写线程的比例
	static final int minthreadCount = 3;//最小线程数
	//读操作
	private static class GetThread implements Runnable {
		private GoodsService goodsService;

		public GetThread(GoodsService goodsService) {
			this.goodsService = goodsService;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			for(int i = 0;i < 1000;i++){
				goodsService.getNum();
			}
			System.out.println(Thread.currentThread().getName() + " 读取数据耗时：" + (System.currentTimeMillis() - start) + "ms");
		}
	}
	//写操作
	private static class SetThread implements Runnable {
		private GoodsService goodsService;

		public SetThread(GoodsService goodsService) {
			this.goodsService = goodsService;
		}

		@Override
		public void run() {
			Random random = new Random();
			long start = System.currentTimeMillis();
			for(int i = 0;i < 10;i++){
				try {
					Thread.currentThread().sleep(50);
				}catch (Exception e){

				}
				goodsService.setNum(random.nextInt(10));
			}
			System.out.println(Thread.currentThread().getName() + " 写取数据耗时：" + (System.currentTimeMillis() - start) + "ms");
		}
	}

	public static void main(String[] args) {
		GoodsInfo goodsInfo = new GoodsInfo("cup",100000,10000);
		//GoodsService goodsService = new UseSyn(goodsInfo);
		GoodsService goodsService = new UseRwLock(goodsInfo);
		for(int i = 0;i < minthreadCount;i++){
			Thread setT = new Thread(new SetThread(goodsService));
			for(int j = 0;j < readWriteRatio;j++){
				Thread getT = new Thread(new GetThread(goodsService));
				getT.start();
			}
			try {
				Thread.currentThread().sleep(100);
			}catch (Exception e){

			}
			setT.start();
		}
	}
}
