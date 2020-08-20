package com.mood.eshop.inventory.service;

import com.mood.eshop.inventory.request.Request;

/**
 * @author mood321
 * @date 2020/8/6 0:17
 * @email 371428187@qq.com
 * @Des 异步处理request的service
 */
public interface RequestAsyncService {
    void process(Request request);
}