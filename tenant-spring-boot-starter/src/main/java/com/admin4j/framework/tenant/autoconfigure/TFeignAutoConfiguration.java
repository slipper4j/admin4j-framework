package com.admin4j.framework.tenant.autoconfigure;

import com.admin4j.common.service.IUserContextHolder;
import com.admin4j.framework.tenant.openfeign.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * @author andanyang
 * @since 2023/9/20 9:42
 */
@AutoConfigureOrder(Integer.MAX_VALUE)
@ConditionalOnBean(value = IUserContextHolder.class, name = "feignAutoConfiguration")
public class TFeignAutoConfiguration {

    @Bean
    @ConditionalOnBean(IUserContextHolder.class)
    public FeignRequestInterceptor feignRequestInterceptor(IUserContextHolder userContextHolder) {
        return new FeignRequestInterceptor(userContextHolder);
    }
}
