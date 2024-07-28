package com.hmall.api.client.fallback;


import com.hmall.api.client.TradeClient;
import com.hmall.api.client.UserClient;
import com.hmall.common.exception.BizIllegalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class UserClientFallback implements FallbackFactory<UserClient> {


    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public void deductMoney(String pw, Integer amount) {
                log.error("远程调用UserClient#deductMoney方法出现异常，参数：{}", pw, cause);
            }
        };
    }
}