package com.code.report.blog.infra.util;

import com.code.report.blog.infra.mapper.ArticleVisitsMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.util.ObjectUtils;


/**
 * @author zhaotianxin
 * @date 2021-04-15 20:08
 */
public class RedisCountUtils {

    public static Long getCount(String key, Long articleId) {
        RedisTemplate<String, Object> redisTemplate = SpringUtil.getBean(RedisUtil.class).getRedisTemplate();
        RedisAtomicLong atomicLong = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
        if (atomicLong.get() == 0) {
            synchronized (RedisCountUtils.class) {
                atomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
                if (atomicLong.get() == 0) {
                    ArticleVisitsMapper visitsMapper = SpringUtil.getBean(ArticleVisitsMapper.class);
                    Long maxCount = visitsMapper.visitsCount(articleId);
                    if(ObjectUtils.isEmpty(maxCount)){
                        maxCount = 0L;
                    }
                    atomicLong = new RedisAtomicLong(key+articleId, redisTemplate.getConnectionFactory(), maxCount);
                }
            }
        }
        return atomicLong.incrementAndGet();
    }

    public static Long notAutoIncrement(String key, Long articleId) {
        RedisTemplate<String, Object> redisTemplate = SpringUtil.getBean(RedisUtil.class).getRedisTemplate();
        RedisAtomicLong atomicLong = new RedisAtomicLong(key+articleId,redisTemplate.getConnectionFactory());
        if (atomicLong.get() == 0) {
            synchronized (RedisCountUtils.class) {
                atomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
                if (atomicLong.get() == 0) {
                    ArticleVisitsMapper visitsMapper = SpringUtil.getBean(ArticleVisitsMapper.class);
                    Long maxCount = visitsMapper.likeCount(articleId);
                    if(ObjectUtils.isEmpty(maxCount)){
                        maxCount = 0L;
                    }
                    atomicLong = new RedisAtomicLong(key+articleId, redisTemplate.getConnectionFactory(), maxCount);
                }
            }
        }
        return atomicLong.get();
    }
}
