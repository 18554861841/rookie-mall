package com.jnzy.mall.queue;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnzy.mall.common.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


/**
 * @author 14835
 */
@Configuration
public class RedisSubListener {
    /**
     * redis消息监听器容器
     * @param connectionFactory
     * @param productListenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter productListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
        //修改默认序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //设置序列化方式
        productListenerAdapter.setSerializer(jackson2JsonRedisSerializer);
        //订阅频道
        redisMessageListenerContainer.addMessageListener(productListenerAdapter,new PatternTopic(Constant.MESSAGE_TOPIC));
        return redisMessageListenerContainer;
    }
    /**
     * 利用反射来创建监听到消息之后的执行方法
     */
    @Bean
    MessageListenerAdapter ListenerAdapter(MQReceiver receiveSeckillMessage) {
        return new MessageListenerAdapter(receiveSeckillMessage, "receiveSeckillMessage");
    }
}