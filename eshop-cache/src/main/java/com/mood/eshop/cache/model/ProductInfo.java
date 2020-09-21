package com.mood.eshop.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mood321
 * @date 2020/8/30 21:48
 * @email 371428187@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Long id;
    private String name;
    private  Double price;
}