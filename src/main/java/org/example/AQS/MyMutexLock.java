package org.example.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author buyu_6911
 * @version 2024/10/10 21:07
 * note:
 */
public class MyMutexLock {
    private final Sync sync = new Sync();
    public void lock() {
        sync.acquire(1);
    }
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    public void unlock() {
        sync.release(1);
    }
    public Condition newCondition() {
        return sync.newCondition();
    }
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        // 此处重写tryAcquire后，使用AQS提供的final方法会调用我们重写的tryAcquire
        @Override
        protected boolean tryAcquire(int arg) {
            // 如果想要实现公平锁，只需检查当前的锁队列是否为空
            // 如果不为空，则不尝试获取锁，直接进入队列
            int state = getState();
            final Thread current = Thread.currentThread();
            if (state == 0) {
                // 实现公平锁需要判断队列是否为空，此时新来的线程不去试图抢锁而是直接加入队列
                if (hasQueuedPredecessors()) {
                    return false;
                }
                if (compareAndSetState(0, arg)) {// AQS内部通过Unsafe类的CAS方法完成占据锁的操作
                    setExclusiveOwnerThread(current);
                    return true;
                }// 注意，重入机制通过判断当前线程是否为锁的持有线程
            } else if (current == getExclusiveOwnerThread()) { // state != 0意味着锁已经被占用，判断是否重入
                setState(getState() + arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            int state = getState() - arg;
            if (state == 0) {
                setState(0);
                setExclusiveOwnerThread(null);
                return true;
            }
            setState(state);
            return false;
        }

        Condition newCondition() {
            // AQS中有一个condition内部类，返回它的一个对象
            return new ConditionObject();
        }

    }
}


