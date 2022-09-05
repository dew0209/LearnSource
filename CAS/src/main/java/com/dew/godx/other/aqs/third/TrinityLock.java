package com.dew.godx.other.aqs.third;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *	三元共享同步类
 */
public class TrinityLock implements Lock {
	//为3表示允许3个线程同时获得
	private final Sync sync = new Sync(3);

	private static final class Sync extends AbstractQueuedSynchronizer {
		Sync(int count){
			if(count <= 0){
				throw new IllegalArgumentException("count must large than zero...");
			}
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int arg) {
			for(;;){
				int current = getState();
				int newCount = current - arg;
				if(newCount < 0 || compareAndSetState(current,newCount)){
					return newCount;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			for(;;){
				int current = getState();
				int newCount = current + arg;
				if(compareAndSetState(current,newCount)){
					return true;
				}
			}
		}
		final ConditionObject newCondition(){
			return new ConditionObject();
		}
	}

	public void unlock(){
		sync.releaseShared(1);
	}

	public void lock(){
		sync.acquireShared(1);
	}

	public void lockInterruptibly() throws InterruptedException{
		sync.acquireSharedInterruptibly(1);
	}

	public boolean tryLock(){
		return sync.tryAcquireShared(1) >= 0;
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
	}

	public Condition newCondition(){
		return sync.newCondition();
	}

}
