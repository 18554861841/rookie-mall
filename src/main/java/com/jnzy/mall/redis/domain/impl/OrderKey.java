package com.jnzy.mall.redis.domain.impl;

import com.jnzy.mall.redis.domain.BaseKeyPrefix;

/**
 * @author 14835
 */
public class OrderKey extends BaseKeyPrefix {
    private OrderKey( String prefix) {
        super( prefix);
    }

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("soug");
}
