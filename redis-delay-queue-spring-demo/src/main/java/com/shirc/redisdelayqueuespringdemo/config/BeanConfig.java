package com.shirc.redisdelayqueuespringdemo.config;

import com.shirc.redis.delay.queue.core.RedisDelayQueueContext;
import com.shirc.redis.delay.queue.iface.RedisDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description 接入Redis_Delay_Queue
 * @Author shirenchuang
 * @Date 2019/8/1 9:41 AM
 **/
@Component
public class BeanConfig {

    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private Environment env;

    /**
     * 修改 redisTemplate 的key序列化方式
     **/
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }

    /******* 接入 RedisDelayQueue  *******/
    @Bean
    public RedisDelayQueueContext getRdctx() {
        String name = env.getProperty("spring.application.name");
        return new RedisDelayQueueContext(redisTemplate, StringUtils.hasText(name) ? name : "application");
    }

    @Bean
    public RedisDelayQueue getRedisOperation(RedisDelayQueueContext context) {
        return context.getRedisDelayQueue();
    }
}
