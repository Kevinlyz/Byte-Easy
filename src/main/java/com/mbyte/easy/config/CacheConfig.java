package com.mbyte.easy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: CacheConfig
 * @Description: 缓存配置
 * @Author: lxt
 * @Date: 2019-02-14 18:12
 * @Version 1.0
 **/
@Configuration
public class CacheConfig {
    private final static Logger logger = LoggerFactory.getLogger(CacheConfig.class);
    /**
     * @Title: classKey
     * @Description: 以【类名】为缓存的key值 一般用于【selectALL】查询
     * @Author: lxt
     * @Date: 2019-03-01 15:42
     * @return: org.springframework.cache.interceptor.KeyGenerator
     * @throws:
     */
    @Bean
    public KeyGenerator classKey() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            logger.info("缓存classKey-key："+sb.toString());
            return sb.toString();
        };

    }
    /**
     * @Title: classMethodParamsKey
     * @Description: 以【类名+方法名+参数】为缓存的key值
     * @Author: lxt
     * @Date: 2019-03-01 15:43
     * @return: org.springframework.cache.interceptor.KeyGenerator
     * @throws:
     */
    @Bean
    public KeyGenerator classMethodParamsKey() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj == null ? "" : obj.toString());
            }
            logger.info("缓存classMethodParamsKey-key："+sb.toString());
            return sb.toString();
        };

    }
}
