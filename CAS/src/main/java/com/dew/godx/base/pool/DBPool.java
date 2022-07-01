package com.dew.godx.base.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 *
 */
public class DBPool {
	//池子
	private static LinkedList<Connection> pool = new LinkedList<>();

	public DBPool(int initalSize){
		if(initalSize > 0){
			for(int i = 0;i < initalSize;i++){
				pool.addLast(SqlConnectionImpl.fetchConnection());
			}
		}
	}
	//在mills时间范围内，还拿不到数据库连接，就应该返回一个null
	public Connection fetchConn(long mills) throws InterruptedException {
		synchronized (pool){
			//永不超时
			if(mills < 0){
				while (pool.isEmpty()){
					pool.wait();
				}
				return pool.removeFirst();
			}else {//等待超时机制
				long overtime = System.currentTimeMillis() + mills;
				long remain = mills;
				while (pool.isEmpty() && remain > 0){
					pool.wait(remain);
					remain = overtime - System.currentTimeMillis();
				}
				Connection result = null;
				if(!pool.isEmpty()){
					result = pool.removeFirst();
				}
				return result;
			}
		}
	}

	public void releaseConn(Connection conn){
		if(conn == null)return;
		synchronized (pool){
			pool.addLast(conn);
			pool.notifyAll();
		}
	}

}
