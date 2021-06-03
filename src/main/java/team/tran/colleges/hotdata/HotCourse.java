package team.tran.colleges.hotdata;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.tran.colleges.course.dao.CourseDao;
import team.tran.colleges.utils.HotUtils;
import team.tran.colleges.utils.Ranking;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 天才选手
 * @ClssName HotCourse
 * @desc
 * @Time 2021/6/2 20:30
 * @Version 1.0
 **/
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class HotCourse {


    @Autowired
    private  CourseDao courseDao;


    @Autowired
    private   RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "* * * 1 * ? ")
    @PostConstruct
    public  void hot() {
        //连表查询
        List<Map<String, Object>> maps = courseDao.selectCouserAndTeacher();
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
//        redisTemplate.delete(Ranking.BOUTIQUE.getName());
        //循环添加到redis数据库里
        for (Map<String, Object> map : maps) {
            String s = JSONObject.toJSONString(map);
            operations.set(map.get("id") + "_course", s);
            zSetOperations.incrementScore(Ranking.BOUTIQUE.getName(),map.get("id"),1);
        }



    }
}
