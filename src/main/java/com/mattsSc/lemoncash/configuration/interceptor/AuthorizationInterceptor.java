package com.mattsSc.lemoncash.configuration.interceptor;

import com.mattsSc.lemoncash.utils.redis.RedisOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final RedisOperation redisOperation;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("HANDLER - Validate authorization");
        var authorizationUser = request.getHeader("Authorization");

        if(Objects.isNull(authorizationUser)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        if(this.redisOperation.exceedsRequest(authorizationUser)){
            log.info(String.format("User: %s exceeds limit requests", authorizationUser));
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            return false;
        }else{
           this.redisOperation.saveAuthorizationKey(authorizationUser);
           return true;
        }

    }
}
