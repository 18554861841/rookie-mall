package com.jnzy.mall.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 此配置文件会与application.yml中的redis.host和redis.port字段关联起来，
 * @author 14835
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
