package com.kiramie.redis;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author yangbin
 * @since 2022/11/25
 **/
public interface DistributedLockerRedisSon {

    RLock lock(String lockKey);

    RLock lock(String lockKey, long timeout);

    RLock lock(String lockKey, TimeUnit unit, long timeout);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}
