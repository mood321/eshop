package com.mood.eshop.cache.config;

import com.mood.eshop.cache.listenter.InitListenter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mood321
 * @date 2020/8/5 22:21
 * @email 371428187@qq.com
 */
@Configuration
public class WebConfig {
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean =
                new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListenter());
        return servletListenerRegistrationBean;
    }

}