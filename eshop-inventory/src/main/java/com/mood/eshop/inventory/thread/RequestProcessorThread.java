package com.mood.eshop.inventory.thread;

import com.mood.eshop.inventory.request.CacheLoadRequest;
import com.mood.eshop.inventory.request.DBUpdateRequest;
import com.mood.eshop.inventory.request.Request;
import com.mood.eshop.inventory.request.RequestQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author mood321
 * @date 2020/8/5 22:44
 * @email 371428187@qq.com
 */
@Slf4j
public class RequestProcessorThread implements Callable {
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Object call() throws Exception {

        while (true) {
            try {
                Request request = queue.take();


                if (!request.isForceFresh()) {  // 强制刷新缓存  不去重

                    /**
                     *  去重逻辑
                     */
                    RequestQueue requestQueue = RequestQueue.getInstance();
                    Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();

                    // 如果是更新操作
                    if (request instanceof DBUpdateRequest) {

                        // 表示  设为true
                        flagMap.put(request.getProductId(), true);
                    } else if (request instanceof CacheLoadRequest) { // 如果是读取缓存操作
                        Boolean flag = flagMap.get(request.getProductId());

                        // 不为null  为ture  是有一个写请求
                        if (flag != null && flag == true) {
                            flagMap.put(request.getProductId(), false);
                        }
                        //   等于false   说明有一个读请求了
                        if (flag != null && flag == false) {
                            log.info(">>>>>>>>>>>>>执行日志>>>>>>>>>>  商品ID = " + request.getProductId() + " >>  已去重");
                            continue;
                        }

                        // 如果是null
                        if (flag == null) {
                            flagMap.put(request.getProductId(), false);
                        }
                    }
                }
                log.info(">>>>>>>>>>>>>执行日志>>>>>>>>>>  商品ID = " + request.getProductId());
                // 阻塞队列
                // 满了 或空  会阻塞
                request.process();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    
    }
}