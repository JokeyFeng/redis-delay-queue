package com.shirc.redis.delay.queue.utils;

import com.shirc.redis.delay.queue.core.RedisDelayQueueContext;
import com.shirc.redis.delay.queue.common.RedisQueueKeys;

/**
 * @Description
 * @Author shirenchuang
 * @Date 2019/7/31 10:18 AM
 **/
public class RedisKeyUtil {

    public static final String LEFT_BRACKET = "{";
    public static final String RIGHT_BRACKET = "}";
    public static final String SINGLE_COLON = ":";

    /**
     * 获取RD_LIST_TOPIC 的Key前缀
     *
     * @return
     */
    public static String getTopicListPreKey() {
        //String.join(":",getProjectName(),RedisQueueKeys.RD_LIST_TOPIC_PRE)
        return getProjectName().concat(SINGLE_COLON).concat(RedisQueueKeys.RD_LIST_TOPIC_PRE);
    }

    /**
     * 获取RD_LIST_TOPIC某个TOPIC的 Key
     *
     * @param topic
     * @return
     */
    public static String getTopicListKey(String topic) {
        return getTopicListPreKey().concat(topic);
    }

    /**
     * 从member中解析出TopicList的key
     *
     * @param member
     * @return
     */
    public static String getTopicListKeyByMember(String member) {
        return RedisKeyUtil.getTopicListKey(RedisKeyUtil.getTopicKeyBymember(member));
    }

    /**
     * 拼接TOPIC:ID
     *
     * @param topic
     * @return
     */
    public static String getTopicId(String topic, String id) {
        return topic.concat(SINGLE_COLON).concat(id);
    }

    /**
     * 获取所有Job数据存放的Hash_Table 的Key
     *
     * @return
     */
    public static String getDelayQueueTableKey() {
        return getProjectName().concat(SINGLE_COLON).concat(RedisQueueKeys.REDIS_DELAY_TABLE);
    }

    /**
     * 获取延迟列表 ZSet的Key
     *
     * @return
     */
    public static String getBucketKey() {
        return getProjectName().concat(SINGLE_COLON).concat(RedisQueueKeys.RD_ZSET_BUCKET_PRE);
    }

    /**
     * 根据member获取Topic
     *
     * @param member
     * @return
     */
    private static String getTopicKeyBymember(String member) {
        String[] s = member.split(SINGLE_COLON);
        return s[0];
    }

    /**
     * 获取项目名; 加入{}   标记hash_tag; 将所有的可以都落在同一台机器上
     * TODO..  待验证是否放在一台机器上
     *
     * @return
     */
    private static String getProjectName() {
        return LEFT_BRACKET.concat(RedisDelayQueueContext.PROJECTNAME).concat(RIGHT_BRACKET);
    }
}
