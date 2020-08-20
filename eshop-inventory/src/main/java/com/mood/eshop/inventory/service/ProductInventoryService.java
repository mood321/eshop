package com.mood.eshop.inventory.service;

import com.mood.eshop.inventory.model.ProductInventory;

/**
 *  商品库存 接口
 */
public interface ProductInventoryService {

    /**
     *  更新商品库存
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);
    /**
     *  删除redis中的商品库存
     * @param productInventory
     */
    void removeCacheProductInventory(ProductInventory productInventory);
    /**
     *  根据商品id 找库存
     * @param productInventory
     */
    ProductInventory findProductInventory(ProductInventory productInventory);

    /**
     *  设置商品库存的 redis 缓存
     * @param productInventory
     */
    void setProductInventoryCache(ProductInventory productInventory);

    /**
     *  通过id  获取库存
     * @param productId
     * @return
     */
    ProductInventory getProductInventory(Integer productId);
}
