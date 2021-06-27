package com.jnzy.mall.redis.domain.impl;

import com.jnzy.mall.redis.domain.BaseKeyPrefix;

/**
 * @author 14835
 */
public class SeckillKey extends BaseKeyPrefix {

    private SeckillKey(String prefix) {
        super(prefix);
    }
    private SeckillKey(int time, String prefix) {
        super(time,prefix);
    }
    /**
     * 是否秒杀了该商品
     */
    public static SeckillKey isGoodsOver = new SeckillKey("goodsIsOver");
    /**
     * 秒杀path
     */
    public static SeckillKey getSeckillPath = new SeckillKey(60, "seckillPath");
    /**
     * 验证码的结果
     */
    public static SeckillKey getSeckillVerifyCode = new SeckillKey(120,"verifyCode");

}
