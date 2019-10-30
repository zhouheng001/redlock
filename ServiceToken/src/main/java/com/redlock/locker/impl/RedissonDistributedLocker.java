package com.redlock.locker.impl;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import com.redlock.locker.DistributedLocker;

import java.util.concurrent.TimeUnit;

/**
 * Lock接口实现类
 * @author ko
 *
 */
public class RedissonDistributedLocker implements DistributedLocker {
    
    private RedissonClient redissonClient;

    @Override
    public void lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    @Override
    public void lock(String lockKey, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
    }
    
    @Override
    public void lock(String lockKey, TimeUnit unit ,int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        /**
         * / ** *获取锁。 *
         * * <p>如果该锁不可用，则出于线程调度目的，当前线程将被禁用
         * *，并且在获取该锁之前，该线程处于休眠状态。
         * **如果获得了锁，则该锁将一直保留，直到调用<code> unlock </ code>为止，或者*自授予该锁以来，直到通过leaseTime毫秒为止-以先到者为准。
         *  * @param leaseTime授予锁之后最长时间（如果尚未通过调用<code> unlock </ code>释放），则在自动释放之前。 *如果leaseTime为-1，请按住该锁直到明确将其解锁。 * @param unit {@code leaseTime}参数的时间单位* * /
         */
        lock.lock(timeout, unit);
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
}
