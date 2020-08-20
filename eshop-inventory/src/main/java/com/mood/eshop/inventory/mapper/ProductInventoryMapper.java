package com.mood.eshop.inventory.mapper;

import com.mood.eshop.inventory.model.ProductInventory;

/**
 *  库存数量Mapper
 */
public interface ProductInventoryMapper {

    public void updateInventoryCnt(ProductInventory  productInventory);

    /**
     *  根据商品id  找库存
     * @param productId
     */
    public ProductInventory findProductInventory(Integer  productId);
}
