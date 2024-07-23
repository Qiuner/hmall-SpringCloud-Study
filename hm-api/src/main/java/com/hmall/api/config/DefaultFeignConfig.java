package com.hmall.api.config;

import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        System.out.println("我是DefaultFeignConfig拦截器");
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 获取登录用户
                Long userId = UserContext.getUser();
                if (userId == null) {
                    // 如果为空则直接跳过
                    System.out.println("userId为空，不传递user-info");
                    return;
                }
                // 如果不为空则放入请求头中，传递给下游微服务
                template.header("user-info", userId.toString());
            }
        };
    }
}
