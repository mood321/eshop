package com.mood.eshop.cache.service.impl;

import com.mood.eshop.cache.model.ProductInfo;
import com.mood.eshop.cache.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author mood321
 * @date 2020/9/21 21:22
 * @email 371428187@qq.com
 */
@Service
public class LocahostCacheService implements CacheService {
    public static  final  String CACHE_NAME="local";

    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_'+#id")
    public ProductInfo getLocalhost(Long id){
        return null;
    }
    @Override
    @CachePut(value = CACHE_NAME, key = "'key_'+#productInfo.getId()")
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    public ProductInfo saveRedisCache(ProductInfo productInfo) {
        return null;
    }

    @Override
    public ProductInfo getRedis(Long id) {
        return null;
    }
}