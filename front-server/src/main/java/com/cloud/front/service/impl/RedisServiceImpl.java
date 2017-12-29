package com.cloud.front.service.impl;

import com.cloud.front.service.IRedisService;
import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author xiaosa
 */
@Service
public class RedisServiceImpl implements IRedisService {

    /**
     * redisTemplate.opsForValue(); //操作字符串
     * redisTemplate.opsForHash(); //操作hash
     * redisTemplate.opsForList(); //操作list
     * redisTemplate.opsForSet(); //操作set
     * redisTemplate.opsForZSet(); //操作有序set
     */
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    /**
     * 删除key
     * @param key
     * @return
     */
    @Override
    public Long delKey(final String key) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.del(serializer.serialize(key));
            }
        });
    }


    /**
     * 往set添加元素
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long setAdd(final String key, final String value) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.sAdd(serializer.serialize(key), serializer.serialize(value));
            }
        });
    }



    /**
     * 返回指定key的成员数
     * @param key
     * @return
     */
    @Override
    public Long getSetNumber(final String key) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.sCard(serializer.serialize(key));
            }
        });
    }


    /**
     * 设置单个hash的值
     * @param key
     * @param value
     * @return
     */
    @Override
    public Boolean setHash(final String key, final String field, final String value){
        return  stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.hSet(serializer.serialize(key),serializer.serialize(field), serializer.serialize(value));
            }
        });
    }

    /**
     * 根据key和field从hash里面取出value
     * @param key
     * @param field
     * @return
     */
    @Override
    public String getHash(final String key, final String field) {
        return stringRedisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                byte[] key1 = serializer.serialize(key);
                //判断key是否存在
                if(redisConnection.exists(key1)){
                    byte[] value = redisConnection.hGet(key1, serializer.serialize(field));
                    if(value !=null){
                        return  serializer.deserialize(value);
                    }
                }
                return null;
            }
        });
    }



    /**
     * 批量设置hash的值
     * @param key
     * @param hashes
     */
    @Override
    public void setMHash(final String key, final Map<String, String> hashes) {
          stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                final Map<byte[], byte[]> bhash = new HashMap<byte[], byte[]>(hashes.size());
                for (final Entry<String, String> entry : hashes.entrySet()) {
                    bhash.put(serializer.serialize(entry.getKey()), serializer.serialize(entry.getValue()));
                }
                redisConnection.hMSet(serializer.serialize(key),bhash);
                return null;
            }
        });
    }


    /**
     * 设置 set 值
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long setSetValue(final String key, final String value) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.sAdd(serializer.serialize(key), serializer.serialize(value));
            }
        });
    }


    /**
     * 获取set
     * @param key
     * @return
     */
    @Override
    public String getSetValue(final String key) {
        Set set = stringRedisTemplate.execute(new RedisCallback<Set>() {
            @Override
            public Set doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                Set<byte[]> set = redisConnection.sMembers(serializer.serialize(key));
                return set;
            }
        });
         return set.toString();
    }



    /*
	 * 删除指定set的指定value
	 */
    @Override
    public Long delSetValue(final String key, final String value) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.sRem(serializer.serialize(key), serializer.serialize(value));
            }
        });
    }


    /**
     * 删除hash
     * @param key
     * @param field
     * @return
     */
    @Override
    public Long delHash(final String key, final String field) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                return redisConnection.hDel(serializer.serialize(key), serializer.serialize(field));
            }
        });
    }


    /**
     * 保存实体的值
     * @param key
     * @param object
     * @param liveTime 过期时间
     */
    @Override
    public void setObject(final String key, final Object object, final Long liveTime) {
         stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                MessagePack msgpack = new MessagePack();
                try {
                    byte[] _object = msgpack.write(object);
                    connection.set(serializer.serialize(key), _object);
                    if (liveTime > 0) {
                        //设置过期时间
                        stringRedisTemplate.expire(key,liveTime, TimeUnit.MILLISECONDS);
                        connection.pExpire(serializer.serialize(key), liveTime);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    /**
     * 获取实体的值，要储存redis的实体要加上@Message注解
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T getObject(final String key, final Class<T> clazz) {
        return stringRedisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                byte[] keybytes = stringRedisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(keybytes)) {
                    byte[] valuebytes = connection.get(keybytes);
                    MessagePack msgpack = new MessagePack();
                    try {
                        return msgpack.read(valuebytes, clazz);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }


    /**
     * 原子操作自增
     * @param key
     * @param liveTime
     * @return
     */
    @Override
    public synchronized Long increment(final String key, final long liveTime) {
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                ValueOperations<String, String> valueOper = stringRedisTemplate.opsForValue();
                long count = valueOper.increment(key, 1);
                byte[] keybytes = stringRedisTemplate.getStringSerializer().serialize(key);
                Boolean flag = liveTime > 0 && (!connection.exists(keybytes) || count == 1);
                if (flag) {
                    // 第一次来设置超时时间
                    stringRedisTemplate.expire(key, liveTime, TimeUnit.MILLISECONDS);
                }
                return count;
            }
        });
    }


    /**
     * 用hash存一个对象
     * @param key
     * @param field
     * @param object
     */
    @Override
    public void saveObject(final String key, final String field, final Object object) {
        stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                MessagePack msgpack = new MessagePack();
                try {
                    byte[] _object = msgpack.write(object);
                    connection.hSet(serializer.serialize(key),serializer.serialize(field), _object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    /**
     * 取出hash 里的对象
     * @param key
     * @param field
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T getObject(final String key,final String field,final Class<T> clazz) {
        return  stringRedisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T  doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                byte[] keybytes = serializer.serialize(key);
                byte[] valuebytes = connection.hGet(keybytes, serializer.serialize(field));
                if (valuebytes != null) {
                    MessagePack msgpack = new MessagePack();
                    try {
                        T t = msgpack.read(valuebytes, clazz);
                        return t;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
