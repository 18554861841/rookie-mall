package com.jnzy.mall.queue;

import com.jnzy.mall.pojo.SeckillGoods;
import com.jnzy.mall.queue.domain.SeckillMessage;
import com.jnzy.mall.redis.RedisService;
import com.jnzy.mall.service.SeckillGoodsService;
import com.jnzy.mall.service.SeckillOrderService;
import com.jnzy.mall.service.impl.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 14835
 */
@Service
public class MQReceiver {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private SeckillService seckillService;

    /**
     * 秒杀商品订单的信息监听
     */
    public void receiveSeckillMessage(String message) {

        //接收消息体
        SeckillMessage seckillMessage = RedisService.stringToBean(message, SeckillMessage.class);
        logger.info("收到消息：" + seckillMessage);

        //从消息中拿到商品id和用户id
        Long goodsId = seckillMessage.getGoodsId();
        Long userId = seckillMessage.getUserId();
        //判断库存
        SeckillGoods seckillGoods = seckillGoodsService.selectSeckillGoodsById(goodsId);
        if (seckillGoods.getStock() <= 0) {
            return;
        }
        //生成秒杀订单
        seckillService.seckill(userId, goodsId);
    }
}