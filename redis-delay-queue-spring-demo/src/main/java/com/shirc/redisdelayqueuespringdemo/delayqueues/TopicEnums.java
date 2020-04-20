package com.shirc.redisdelayqueuespringdemo.delayqueues;

/**
 * @Description TODO
 * @Author shirenchuang
 * @Date 2019/7/31 12:23 PM
 **/
public enum TopicEnums {

    /**
     * 延时队列主题
     */
    TOPIC_1("topic_1", "第一个测试TOPIC"),
    TOPIC_2("topic_2", "第二个测试TOPIC");

    public String topic;

    public String desc;

    TopicEnums(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public String getDesc() {
        return desc;
    }
}
