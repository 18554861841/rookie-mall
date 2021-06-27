package com.jnzy.mall.service.impl;

import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.redis.RedisService;
import com.jnzy.mall.redis.domain.impl.SeckillKey;
import com.jnzy.mall.service.SeckillGoodsService;
import com.jnzy.mall.service.SeckillOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 14835
 */
@Service
public class SeckillService {

    private Logger logger = LoggerFactory.getLogger(SeckillService.class);

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private RedisService redisService;

    /**
     * 秒杀事务
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Transactional
    public SeckillOrder seckill(Long userId, Long goodsId) {

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsId);
        seckillOrder.setUserId(userId);

        //减库存 下订单 写入秒杀订单
        int stock = seckillGoodsService.deductSeckillGoodsStock(goodsId);
        if (stock > 0) {
            seckillOrderService.insertSeckillOrder(seckillOrder);
            return seckillOrder;
        } else {
            return null;
        }
    }

    /**
     * 在redis中设置秒杀商品是否秒杀成功
     *
     * @param goodsId
     */
    private void setGoodsOver(Long goodsId) {
        redisService.set(SeckillKey.isGoodsOver, "" + goodsId, true);
    }

    /**
     * 在Redis中获取秒杀商品是否秒杀成功
     *
     * @param goodsId
     * @return
     */
    private boolean getGoodsOver(Long goodsId) {
        return redisService.exists(SeckillKey.isGoodsOver, "" + goodsId);
    }

    /**
     * orderId: 成功
     * -1: 秒杀失败
     * 0: 排队中
     */
    public long getSeckillResult(Long userId, Long goodsId) {
        SeckillOrder seckillOrder = seckillOrderService.selectSeckillOrderByGoodsIdUserId(goodsId, userId);
        logger.info("秒杀订单:" + seckillOrder);
        if (seckillOrder != null) {
            return seckillOrder.getId();
        } else {
            Boolean is_over = getGoodsOver(goodsId);
            if (is_over) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}