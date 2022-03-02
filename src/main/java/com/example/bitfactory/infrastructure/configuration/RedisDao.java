package com.example.bitfactory.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, 15, TimeUnit.MINUTES);//15分钟过期
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    public Boolean isExpire(String key) {
        if (Objects.nonNull(template.getExpire(key)) && template.getExpire(key) == -2) {
            return true;
        }
        return false;
    }
}
