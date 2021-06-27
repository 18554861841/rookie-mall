package com.jnzy.mall.redis.domain;

/**
 * 模板模式：不同的pojo存储在redis中会增加不同的前缀（<前缀+key，value>）
 * @author 14835
 */
public interface KeyPrefix {

    /**
     * 获取过期时间
     * @return
     */
    public int expireSeconds();

    /**
     * 获取前缀
     * @return
     */
    public String getPrefix();

}
