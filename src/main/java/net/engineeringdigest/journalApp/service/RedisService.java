package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.server.ui.OneTimeTokenSubmitPageGeneratingWebFilter;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> object) {

        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), object);
        } catch (Exception e) {
            log.error("Error while fetching opsForValue: " + e.getMessage());
            return null;
        }
    }

    public void set(String key, Object o, Long expiery) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,json,expiery, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Error while setting opsForValue: " + e.getMessage());
        }
    }
}
