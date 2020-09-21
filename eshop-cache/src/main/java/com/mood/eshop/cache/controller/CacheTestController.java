package com.mood.eshop.cache.controller;

import com.mood.eshop.cache.model.ProductInfo;
import com.mood.eshop.cache.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author mood321
 * @date 2020/9/21 21:26
 * @email 371428187@qq.com
 */

@Controller
public class CacheTestController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/testPutCache")
    @ResponseBody
    public void testPutCache(ProductInfo productInfo) {
        System.out.println(productInfo.getId() + ":" + productInfo.getName());
        cacheService.saveLocalCache(productInfo);
    }

    @RequestMapping("/testGetCache")
    @ResponseBody
    public ProductInfo testGetCache(Long id) {
        ProductInfo productInfo = cacheService.getLocalhost(id);
        System.out.println(productInfo.getId() + ":" + productInfo.getName());
        return productInfo;
    }

}