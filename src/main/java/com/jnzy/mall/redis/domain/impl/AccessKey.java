package com.jnzy.mall.redis.domain.impl;

import com.jnzy.mall.redis.domain.BaseKeyPrefix;

/**
 * @author 14835
 */
public class AccessKey extends BaseKeyPrefix {

    private AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }

}