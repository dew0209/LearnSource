package com.dew.godx.increase.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *
 */
public class DBPoolSemaphore {
	private final static int POOL_SIZE = 10;
	//存放数据库连接的容器
	private static LinkedList<Connection> pool = new LinkedList<>();
	private final Semaphore useful,useless;//useful表示可用的数据库连接，useless表示已用的数据库连接
	public DBPoolSemaphore(){
		this.useful = new Semaphore(10);
		this.useless = new Semaphore(0);
	}
	//初始化池子
	static {
		for(int i = 0;i < POOL_SIZE;i++){
			pool.add(SqlConnectImpl.fetchConnection());
		}
	}
	/* 归还连接 */
	public void returnConnect(Connection connection) throws InterruptedException {
		if(connection == null){
			useless.acquire();
			synchronized (pool){
				pool.addLast(connection);
			}
			useful.release();
			System.out.println("当前有 " + useful.getQueueLength() + "等待数据库连接！！！" + "可用连接数：" + useful.availablePermits());
		}
	}
	/* 从池子拿连接 */
	public Connection takeConnect() throws InterruptedException {
		useful.acquire();//拿到许可证，拿不到就阻塞
		Connection conn;
		synchronized (pool){
			conn = pool.removeFirst();
		}
		useless.release();//
		return null;
	}
}
