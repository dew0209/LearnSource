package com.dew.godx.other.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *	自己实现一个类似ReentrantLock的锁
 */
public class SelfLock implements Lock {
	//state 表示获取到锁 1 获取到锁 0 表示没有线程拿到
	private static class Sync extends AbstractQueuedSynchronizer {
		//当前锁是否被占用了
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

		@Override
		protected boolean tryAcquire(int arg) {
			if(compareAndSetState(0,1)){
				//此时获取到了锁 设置当前获取到了锁的线程
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			if(getState() == 0){
				throw new UnsupportedOperationException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		Condition newCondition(){
			return new ConditionObject();
		}
	}

	private final Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1,unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
}
