package com.mood.eshop.inventory.dao.impl;

import javax.annotation.Resource;

import com.mood.eshop.inventory.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;


@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

    /*	@Resource
        private JedisCluster jedisCluster;*/
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void set(String key, String value) {
        Jedis resource = jedisPool.getResource();
        resource.set(key, value);
        resource.close();
    }

    @Override
    public String get(String key) {
        Jedis resource = jedisPool.getResource();
        String s = resource.get(key);
        resource.close();
        return s;
    }

    @Override
    public Long delete(String key) {
        Jedis resource = jedisPool.getResource();
        Long del = resource.del(key);
        resource.close();
        return del;
    }

}
