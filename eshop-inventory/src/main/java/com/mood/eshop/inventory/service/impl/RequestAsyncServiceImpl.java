package com.mood.eshop.inventory.service.impl;

import com.mood.eshop.inventory.request.Request;
import com.mood.eshop.inventory.request.RequestQueue;
import com.mood.eshop.inventory.service.RequestAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author mood321
 * @date 2020/8/6 0:20
 * @email 371428187@qq.com
 * @Des  请求异步处理的实现类
 */

@Service
@Slf4j
public class RequestAsyncServiceImpl implements RequestAsyncService {
    @Override
    public void process(Request request) {


        try {

         
            // 根据请求id 路由到对应的队列中去
            ArrayBlockingQueue<Request> queue = route(request.getProductId());
            log.info(">>>>>>>>>>>>日志>>>>>  入参 商品ID = "+request.getProductId() +"    路由 ");

            // 在对应队列 中添加请求
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  根据请求id 路由到对应的队列中去
     * @param productId
     * @return
     */
    private ArrayBlockingQueue<Request> route(Integer productId){
        RequestQueue queues = RequestQueue.getInstance();
        String key=String.valueOf(productId);
        // 计算hash
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        // 计算下标
        int index = (queues.getQueueSize() - 1) & hash;
        log.info(">>>>>>>>>>>>日志>>>>>  路由 队列ID  = "+index);

        // 获取队列
         return queues.getQueue(index);
    }

}