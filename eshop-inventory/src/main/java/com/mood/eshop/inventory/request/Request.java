package com.mood.eshop.inventory.request;

/**
 * 请求接口的封装
 */
public interface Request {
    void process();

    Integer getProductId();
    default boolean isForceFresh( ){return  false;};
}
