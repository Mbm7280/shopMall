package com.mall.service;


/*
 * @Author maiBangMin
 * @Description [Redis接口业务层]
 * @Date 7:41 下午 2020/11/28
 * @Version 1.0
 **/
public interface RedisService {


    /*
     * @Author maiBangMin
     * @Description [存储数据]
     * @Date 7:43 下午
     * @Version 1.0
     **/
    void set(String key, String value);

    /*
     * @Author maiBangMin
     * @Description [获取数据]
     * @Date 7:43 下午
     * @Version 1.0
     **/
    String get(String key);

    /*
     * @Author maiBangMin
     * @Description [设置超期时间]
     * @Date 7:43 下午
     * @Version 1.0
     **/
    boolean expire(String key, long expire);

    /*
     * @Author maiBangMin
     * @Description [删除数据]
     * @Date 7:44 下午
     * @Version 1.0
     **/
    void remove(String key);

    /*
     * @Author maiBangMin
     * @Description [自增操作]
     * @Date 7:44 下午
     * @Version 1.0
     **/
    Long increment(String key, long delta);

}
