package com.mood.eshop.inventory.listener;

import com.mood.eshop.inventory.thread.RequestProcessorThreadPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author mood321
 * @date 2020/8/5 22:23
 * @email 371428187@qq.com
 */
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
         RequestProcessorThreadPool.init();
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("InitListener des..");
    }
}