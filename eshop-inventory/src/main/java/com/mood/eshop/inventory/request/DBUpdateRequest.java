package com.mood.eshop.inventory.request;

import com.mood.eshop.inventory.model.ProductInventory;
import com.mood.eshop.inventory.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mood321
 * @date 2020/8/5 23:04
 * @email 371428187@qq.com
 * @Des 比如一个商品 发生交易   要修改库存
 * <p>
 * 此时一个请求过来  要求更新库存
 * 1 删除缓存
 * 2 更新数据库
 */
@Slf4j
public class DBUpdateRequest implements Request {

    private ProductInventory productInventory;
    private ProductInventoryService productInventoryService;

    public DBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }


    public void process() {
      /*  try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        log.info(">>>>>>>>>>>>request  日志>>>>>  更新 商品ID = "+productInventory.getProductId() +"  库存  "+ productInventory.getProductInventory());

        // 1 删除缓存
        productInventoryService.removeCacheProductInventory(productInventory);
        // 2 更新数据库
        productInventoryService.updateProductInventory(productInventory);

    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}