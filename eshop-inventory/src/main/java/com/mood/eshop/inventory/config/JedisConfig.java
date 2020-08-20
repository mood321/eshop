package com.mood.eshop.inventory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mood321
 * @date 2020/8/5 21:45
 * @email 371428187@qq.com
 */
@Configuration
public class JedisConfig {
   /* @Bean
    public JedisCluster JedisClusterFactory() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.0.126", 6379));
       *//* jedisClusterNodes.add(new HostAndPort("192.168.31.19", 7004));
        jedisClusterNodes.add(new HostAndPort("192.168.31.227", 7006));*//*
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        return jedisCluster;
    }*/
 

    @Bean
    @ConditionalOnMissingBean(JedisPool.class)
    public JedisPool jedisPool() {

        JedisPool jedisPool = new JedisPool("192.168.0.126",6379);
        return jedisPool;
    }
}