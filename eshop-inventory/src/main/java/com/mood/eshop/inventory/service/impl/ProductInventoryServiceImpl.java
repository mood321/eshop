package com.mood.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mood.eshop.inventory.dao.RedisDAO;
import com.mood.eshop.inventory.mapper.ProductInventoryMapper;
import com.mood.eshop.inventory.model.ProductInventory;
import com.mood.eshop.inventory.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author mood321
 * @date 2020/8/5 23:28
 * @email 371428187@qq.com
 * @Des 库存的实现类
 */

@Service
@Slf4j
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    ProductInventoryMapper productInventoryMapper;

    @Resource
    private RedisDAO redisDAO;

    
    @Override
    public void updateProductInventory(ProductInventory productInventory) {

        productInventoryMapper.updateInventoryCnt(productInventory);
        log.info(">>>>>>>>>>>>service  日志>>>>>  更新 mapper 商品ID = "+productInventory.getProductId() +"  库存  "+ productInventory.getProductInventory());
    }

    @Override
    public void removeCacheProductInventory(ProductInventory productInventory) {
        log.info(">>>>>>>>>>>>service  日志>>>>>  删除 redis 商品ID = "+productInventory.getProductId() +"  库存  "+ productInventory.getProductInventory());

        String key="product:inventory:"+productInventory.getProductId();
        Long delete = redisDAO.delete(key);
       log.info("===========redis delete=========="+ delete.toString());
    }

    @Override
    public ProductInventory findProductInventory(ProductInventory productInventory) {
        log.info(">>>>>>>>>>>>service  日志>>>>>  查找 mapper 商品ID = "+productInventory.getProductId() );

        return productInventoryMapper.findProductInventory(productInventory.getProductId());
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key="product:inventory:"+productInventory.getProductId();
        log.info(">>>>>>>>>>>>service  日志>>>>>  更新 redis 商品ID = "+productInventory.getProductId() +"  库存  "+ productInventory.getProductInventory());

        redisDAO.set(key,productInventory.getProductInventory().toString());
    }

    @Override
    public ProductInventory getProductInventory(Integer productId) {
        //Long inventoryCnt=0L;
        log.info(">>>>>>>>>>>>service  日志>>>>>  查找 redis 商品ID = "+productId);

        ProductInventory productInventory=null;
        String key="product:inventory:"+String.valueOf(productId);
        String result = redisDAO.get(key);
        if(!StringUtils.isEmpty(result))  {
            try {
                productInventory=new ProductInventory(productId,Long.valueOf(result));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productInventory;
    }

}