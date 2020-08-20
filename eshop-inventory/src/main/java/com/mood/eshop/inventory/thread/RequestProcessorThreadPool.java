package com.mood.eshop.inventory.thread;

import com.mood.eshop.inventory.request.Request;
import com.mood.eshop.inventory.request.RequestQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mood321
 * @date 2020/8/5 22:29
 * @email 371428187@qq.com
 * @Des 请求线程池单例
 */
public class RequestProcessorThreadPool {
    /**
     * 线程池   处理器核心数
     */
    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    private RequestProcessorThreadPool() {

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            ArrayBlockingQueue queue = new ArrayBlockingQueue<Request>(100);
            RequestQueue.getInstance().addQueue(queue);
            service.submit(new RequestProcessorThread(queue));
        }
    }

    /**
     *  静态类  单例
     */
    private static class singleton {
        private static RequestProcessorThreadPool instance;
        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getRequestProcessorThreadPool() {
            return instance;
        }
    }

    /**
     * 用jvm 加载机制 实现单例
     *
     * @return
     */
    public static RequestProcessorThreadPool getInstance() {
        return singleton.getRequestProcessorThreadPool();
    }

    /**
     *  封装边界方法
     */
    public static void init(){
        getInstance();
    }
}