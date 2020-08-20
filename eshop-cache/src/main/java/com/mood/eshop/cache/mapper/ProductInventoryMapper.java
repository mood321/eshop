package com.mood.eshop.cache.mapper;

import com.mood.eshop.cache.model.ProductInventory;

/**
 *  库存数量Mapper
 */
public interface ProductInventoryMapper {

    public void updateInventoryCnt(ProductInventory productInventory);

    /**
     *  根据商品id  找库存
     * @param productId
     */
    public ProductInventory findProductInventory(Integer productId);
}
