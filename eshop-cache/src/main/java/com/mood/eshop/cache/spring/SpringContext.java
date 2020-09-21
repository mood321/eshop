package com.mood.eshop.cache.spring;

import lombok.Data;
import org.springframework.context.ApplicationContext;

/**
 * @author mood321
 * @date 2020/9/21 22:36
 * @email 371428187@qq.com
 */

public class SpringContext {
   private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}