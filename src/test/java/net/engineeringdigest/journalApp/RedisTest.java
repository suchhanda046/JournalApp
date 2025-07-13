package net.engineeringdigest.journalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    public void getRedisValue(){
        redisTemplate.opsForValue().set("name","Gina");
        String namevalue = redisTemplate.opsForValue().get("name").toString();
    }
}
