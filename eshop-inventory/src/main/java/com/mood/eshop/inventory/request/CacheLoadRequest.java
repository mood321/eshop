package com.mood.eshop.inventory.request;

import com.mood.eshop.inventory.model.ProductInventory;
import com.mood.eshop.inventory.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mood321
 * @date 2020/8/5 23:39
 * @email 371428187@qq.com
 *
 * @Des 重新加载库存缓存
 *
 * 1 .  查询数据库中最新库存
 *
 * 2. 更新redis 缓存
 */
@Slf4j
public class CacheLoadRequest  implements  Request{
    private ProductInventory productInventory;
    private ProductInventoryService productInventoryService;

    private  boolean forceRefresh ;
    public CacheLoadRequest(ProductInventory productInventory, ProductInventoryService productInventoryService
                        ,boolean forceRefresh) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
        this.forceRefresh=forceRefresh;
    }

    @Override
    public void process() {
        //
       

        // 找数据库 库存
        ProductInventory productInventory = productInventoryService.findProductInventory(this.productInventory);

        log.info(">>>>>>>>>>>>request  日志>>>>>  读取 商品ID = "+productInventory.getProductId() +"  库存  "+ productInventory.getProductInventory());

        // 刷新redis
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }

    @Override
    public boolean isForceFresh() {
        return forceRefresh;
    }
}