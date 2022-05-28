package my.springboot.client.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLockUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    //获取锁重试超时时间(秒)
    static Long GET_TIME_OUT = Long.valueOf(2);
    //锁持有时间(秒)
    static Long LOCK_TIME_OUT = Long.valueOf(30);
    //锁前缀
    static String LOCK_PREFIX = "DCS_LOCK_";

    /**
     * 设置分布式锁
     *
     * @param key         锁标识
     * @param value       内容
     * @param lockTimeOut 锁有效时间（秒）
     * @return
     */
    public Boolean lock(String key, String value, Long lockTimeOut) {
        // "SET" "${key}" "\"${value}\"" "EX" "${lockTimeOut}" "NX"
        // setIfAbsent 对应redis命令如上，通过set命令的参数NX，完成排斥锁和原子性
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(LOCK_PREFIX + key, value, lockTimeOut, TimeUnit.SECONDS);
        return aBoolean;
    }

    /**
     * 尝试获取分布式锁
     *
     * @param key         锁标识
     * @param value       内容
     * @param lockTimeOut 锁有效时间（秒）
     * @param getTimeOut  获取锁重试超时时间
     * @return
     */
    public Boolean tryLock(String key, String value, Long lockTimeOut, Long getTimeOut) {
        Boolean lock;
        Boolean flag = true;
        long begin = System.currentTimeMillis();

        do {
            lock = this.lock(key, value, lockTimeOut);
            if (lock == false) {
                try {
                    //休眠0.1秒后重试，直到重试超时getTimeOut
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                if (endTime - begin > (getTimeOut * 1000)) {
                    System.out.println("获取锁超时，锁标识：" + key);
                    flag = false;//退出循环
                }
            } else {
                flag = false;//退出循环
            }
        } while (flag);

        return lock;
    }

    /**
     * 尝试获取锁
     *
     * @param key 锁标识
     * @return
     */
    public Boolean tryLock(String key) {
        return this.tryLock(key, key, LOCK_TIME_OUT, GET_TIME_OUT);
    }

    /**
     * 尝试获取锁
     *
     * @param key        锁标识
     * @param getTimeOut 获取锁重试超时时间
     * @return
     */
    public Boolean tryLock(String key, Long getTimeOut) {
        return this.tryLock(key, key, LOCK_TIME_OUT, getTimeOut);
    }

    /**
     * 尝试获取锁
     *
     * @param key        锁标识
     * @param value      内容
     * @param getTimeOut 获取锁重试超时时间
     * @return
     */
    public Boolean tryLock(String key, String value, Long getTimeOut) {
        return this.tryLock(key, value, LOCK_TIME_OUT, getTimeOut);
    }

    /**
     * 尝试获取锁
     *
     * @param key   锁标识
     * @param value 内容
     * @return
     */
    public Boolean tryLock(String key, String value) {
        return this.tryLock(key, value, LOCK_TIME_OUT, GET_TIME_OUT);
    }

    /**
     * 释放锁
     *
     * @param key 锁标识
     * @return
     */
    public Boolean unLock(String key) {
        Boolean delete = redisTemplate.delete(LOCK_PREFIX + key);
        return delete;
    }

    /**
     * 释放锁
     *
     * @param keys 锁标识
     * @return
     */
    public Boolean unLocks(List<String> keys) {
        //删除的锁，去重
        Set<String> deleteKeys = new HashSet<>();
        for (String key : keys) {
            String delKey = LOCK_PREFIX + key;
            deleteKeys.add(delKey);
        }

        Long delete = redisTemplate.delete(deleteKeys);
        if (deleteKeys.size() > delete) {
            //多个一起删除存在失败，重试单个删除
            for (String key : deleteKeys) {
                redisTemplate.delete(key);
            }
        }
        return true;
    }
}

