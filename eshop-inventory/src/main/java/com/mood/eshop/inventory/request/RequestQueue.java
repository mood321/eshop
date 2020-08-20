package com.mood.eshop.inventory.request;

import com.mood.eshop.inventory.thread.RequestProcessorThreadPool;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mood321
 * @date 2020/8/5 22:52
 * @email 371428187@qq.com
 * @Des 请求的内存队列
 */

public class RequestQueue {


    /**
     * 内存队列
     */
    private List<ArrayBlockingQueue<Request>> queueList = new ArrayList<>();

    /**
     *  map 表示 去重
     */
    private Map<Integer,Boolean> flagMap=new ConcurrentHashMap<>();

    /**
     *  添加一个内存队列
     * @param queue
     */
    public void addQueue(ArrayBlockingQueue<Request> queue) {
        queueList.add(queue);
    }

    private static class singleton {
        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getRequestProcessorThreadPool() {
            return instance;
        }
    }

    /**
     * 用jvm 加载机制 实现单例
     *
     * @return
     */
    public static RequestQueue getInstance() {
        return singleton.getRequestProcessorThreadPool();
    }

    /**
     *  内存队列的数量
     * @return
     */
    public   int getQueueSize(){
        return   queueList.size();
    }

    /**
     * 根据下标  获取内存队列
     * @param index
     * @return
     */
    public ArrayBlockingQueue getQueue(int index){
           return queueList.get(index);

    }

    /**
     *  获取去重map
     * @return
     */
    public Map getFlagMap(){
           return flagMap;

    }
}