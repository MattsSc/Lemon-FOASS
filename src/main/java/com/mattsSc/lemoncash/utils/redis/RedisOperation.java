package com.mattsSc.lemoncash.utils.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

@RequiredArgsConstructor
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "lemon-fo.authorization")
public class RedisOperation {

    private final RedisTemplate<String, String> redisTemplate;
    private Integer limitTimeRange;
    private Integer limitRequest;

    public boolean exceedsRequest(String key){
        var requestCount = this.redisTemplate.opsForValue().get(key);

        if(Objects.isNull(requestCount)){
            return false;
        }

        return Integer.parseInt(requestCount) >= limitRequest;
    }

    public void saveAuthorizationKey(String key){
        var requestCount = this.redisTemplate.opsForValue().get(key);

        if(Objects.isNull(requestCount)){
            this.redisTemplate.opsForValue().setIfAbsent(key, "1", Duration.ofSeconds(limitTimeRange));
        }else{
            this.redisTemplate.opsForValue().increment(key);
        }
    }

}
