package com.cloud.front.service;

import java.util.Map;

/**
 * @author xiaosa
 */
public interface IRedisService {

    /**
     * 删除key
     * @param key
     * @return
     */
    Long delKey(String key);


    /**
     * 往set添加元素
     * @param key
     * @param value
     * @return
     */
    Long setAdd(String key, String value);


    /**
     * set 返回指定key的成员数
     * @param
     * @return
     */
    Long getSetNumber(String key);


    /**
     * 设置 set 值
     * @param key
     * @param value
     * @return
     */
    Long setSetValue(String key, String value);


    /**
     * 获取set
     * @param key
     * @return
     */
    String getSetValue(String key);


    /**
     * 删除指定set的指定value
     * @param key
     * @param value
     * @return
     */
    Long delSetValue(String key, String value);

    /**
     * 设置单个hash的值
     * @param key
     * @param field
     * @param value
     * @return
     */
    Boolean setHash(String key, String field, String value);


    /**
     * 根据key和field从hash里面取出value
     * @param key
     * @param field
     * @return
     */
    String getHash(String key, String field);


    /**
     * 批量设置hash的值
     * @param key
     * @param hashes
     */
    void setMHash(String key, Map <String, String> hashes);


    /**
     * 删除hash
     * @param key
     * @param
     * @return
     */
    Long delHash(String key, String field);


    /**
     * 保存实体的值
     * @param key
     * @param object
     */
    void setObject(String key, Object object, Long liveTime);


    /**
     * 获取实体的值
     * @param key
     * @param clazz
     * @return
     */
    <T> T getObject(String key, Class<T> clazz);


    /**
     * 原子操作自增
     * @param key
     * @param liveTime
     * @return
     */
    Long increment(String key, long liveTime);


    /**
     * 用hash存一个对象
     * @param key
     * @param field
     * @param object
     */
    void saveObject(String key,String field, Object object);


    /**
     * 取出hash 里的对象
     * @param key
     * @param field
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getObject(String key,String field,Class<T> clazz);

}
