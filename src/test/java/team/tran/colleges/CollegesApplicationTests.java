package team.tran.colleges;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @className: CollegesApplicationTests
 * @description: TODO 类描述
 * @author: tran
 * @date: 2021/5/31
 **/
@SpringBootTest
public class CollegesApplicationTests {

    @Autowired
    private  RedisTemplate<String, String> redisTemplate;
    @Test
    void contextLoads() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("admin"+"_token","admin",1, TimeUnit.DAYS);


    }
}
