package com.mood.eshop.cache.listenter;

import com.mood.eshop.cache.kafka.KafkaConsumer;
import com.mood.eshop.cache.spring.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author mood321
 * @date 2020/9/21 22:02
 * @email 371428187@qq.com
 * <p>
 * 系统初始化的监听器
 */
@Slf4j
public class InitListenter implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
        SpringContext.setApplicationContext(context);
        log.info("启动kafka cache-message");
        new Thread(new KafkaConsumer("cache-message")).start();
    }
}