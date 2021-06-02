package team.tran.colleges.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @className: HotUtils
 * @description: TODO 课程点评热榜
 * @author: tran
 * @date: 2021/6/1
 **/
@Component
public class HotUtils {

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public HotUtils(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate=redisTemplate;
    }
    /**
     * @param:
     * @description: TODO 存入
     * @return: boolean
     * @author: tran
     * @date: 2021/6/1
     */
    public static void addCourse(String name ,String  key){
        redisTemplate.opsForZSet().incrementScore(name,key,1);

    }


    /**
     * @param:
     * @description: TODO 课程热榜获取
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: tran
     * @date: 2021/6/1
     */
    public static  List<Map<String, Object>> selectCourseHot(String page,String size){
        return  null;
    }


}
