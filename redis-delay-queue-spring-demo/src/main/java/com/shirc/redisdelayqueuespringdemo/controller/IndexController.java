package com.shirc.redisdelayqueuespringdemo.controller;

import com.shirc.redis.delay.queue.common.RunTypeEnum;
import com.shirc.redis.delay.queue.iface.RedisDelayQueue;
import com.shirc.redisdelayqueuespringdemo.bo.MyArgs;
import com.shirc.redisdelayqueuespringdemo.delayqueues.DelayQueueDemo2;
import com.shirc.redisdelayqueuespringdemo.delayqueues.TopicEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

/**
 * @Description TODO
 * @Author shirenchuang
 * @Date 2019/8/1 9:40 AM
 **/
@Controller
@ResponseBody
public class IndexController {

    @Autowired
    private RedisDelayQueue redisDelayQueue;

    @Autowired
    private DelayQueueDemo2 delayQueueDemo2;

    @GetMapping("/addJob")
    public String addJob(Long rt, Integer type) {
        if (rt == null) {
            rt = System.currentTimeMillis() + 30000;
        }
        MyArgs myArgs = new MyArgs();
        String id = UUID.randomUUID().toString().replace("-", "");
        myArgs.setId(id);
        myArgs.setPutTime(new Date());
        myArgs.setShoudRunTime(new Date(rt));
        myArgs.setContent("我是测试用例");
        redisDelayQueue.add(myArgs, TopicEnums.TOPIC_1.getTopic(), rt, type == null ? RunTypeEnum.ASYNC : RunTypeEnum.SYNC);
        return myArgs.getId();
    }

    @GetMapping("/addJob2")
    public String addJob2(Long delayTime, String userId) {
        delayQueueDemo2.addDemo2DelayQueue(userId, delayTime);
        return userId;
    }

    @GetMapping("/delJob2")
    public void delJob2(Long delayTime, String userId) {
        delayQueueDemo2.delDemo2Queue(userId);
    }
}
