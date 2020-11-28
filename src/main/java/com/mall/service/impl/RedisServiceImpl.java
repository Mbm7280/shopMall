package com.mall.service.impl;


import com.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/*
 * @Author maiBangMin
 * @Description [Redis接口实现类]
 * @Date 7:44 下午 2020/11/28
 * @Version 1.0
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
     * @Author maiBangMin
     * @Description 存储数据
     * @Date 7:48 下午 2020/11/28
     * @Param [key, value]
     * @return void
     **/
    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /*
     * @Author maiBangMin
     * @Description 获取数据
     * @Date 7:48 下午 2020/11/28
     * @Param [key]
     * @return java.lang.String
     **/
    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /*
     * @Author maiBangMin
     * @Description 设置超期时间
     * @Date 7:48 下午 2020/11/28
     * @Param [key, expire]
     * @return boolean
     **/
    @Override
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /*
     * @Author maiBangMin
     * @Description 删除数据
     * @Date 7:48 下午 2020/11/28
     * @Param [key]
     * @return void
     **/
    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /*
     * @Author maiBangMin
     * @Description 自增操作
     * @Date 7:49 下午 2020/11/28
     * @Param [key, delta]
     * @return java.lang.Long
     **/
    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }
}
