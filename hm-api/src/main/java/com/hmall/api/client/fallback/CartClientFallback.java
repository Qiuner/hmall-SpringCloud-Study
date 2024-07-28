package com.hmall.api.client.fallback;

import com.hmall.api.client.CartClient;
import com.hmall.common.exception.BizIllegalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collection;

@Slf4j
public class CartClientFallback implements FallbackFactory<CartClient> {
    @Override
    public CartClient create(Throwable cause) {
        return new CartClient(){

            @Override
            public void deleteCartItemByIds(Collection<Long> ids) {
                log.error("远程调用CartClientt#deleteCartItemByIds方法出现异常，参数：{}", ids, cause);
                // 批量删除失败，进行事务回滚111
                throw new BizIllegalException(cause);
            }
        };
    }
}
