package com.mood.eshop.cache.service;

import com.mood.eshop.cache.model.ProductInfo;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

/**
 *  缓存接口
 */
public interface CacheService {

    /**
     *  保存到本地缓存
     * @param productInfo
     * @return
     */
    public ProductInfo saveLocalCache(ProductInfo productInfo);

    /**
     *  获取商品信息
     * @param id
     * @return
     */
    public ProductInfo getLocalhost(Long id);
    /**
     *  保存到redis缓存
     * @param productInfo
     * @return
     */
    public ProductInfo saveRedisCache(ProductInfo productInfo);

    /**
     *  redis获取商品信息
     * @param id
     * @return
     */
    public ProductInfo getRedis(Long id);
}
