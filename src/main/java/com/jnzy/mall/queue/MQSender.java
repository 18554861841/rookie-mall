package com.jnzy.mall.queue;

import com.jnzy.mall.common.Constant;
import com.jnzy.mall.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 14835
 */
@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 将秒杀的订单信息发送到redis队列
     */
    public void sendSeckillMessage(Object message) {
        String msg = RedisService.beanToString(message);
        logger.info("发送消息：", msg);
        redisTemplate.convertAndSend(Constant.MESSAGE_TOPIC, msg);
    }
}
