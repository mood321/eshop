package com.mood.eshop.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mood321
 * @date 2020/8/5 23:18
 * @email 371428187@qq.com
 * @Des 库存数量类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {
    /**
     *  简化的参数
     *   商品ID
     */
    private Integer productId;

    /**
     *  库存数量
     */
    private  Long productInventory;
}