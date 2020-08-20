package com.mood.eshop.inventory.config;

import com.mood.eshop.inventory.listener.InitListener;
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
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }

}