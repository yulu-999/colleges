package team.tran.colleges.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import team.tran.colleges.entity.RemarkInfo;

import javax.annotation.PostConstruct;
import java.util.*;

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
    public static void addCourse(Ranking name,String  key){
        redisTemplate.opsForZSet().incrementScore(name.getName(),key,1);

    }


    /**
     * @param:
     * @description: TODO 课程热榜获取
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: tran
     * @date: 2021/6/1
     */
    public static  List<Map<String, Object>> selectCourseHot(){
        List<Map<String,Object>> rank = new ArrayList<>();
        //只取前十条
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(Ranking.HOTCOURSE.getName(), 0, 99999, 0, 8);
        int index = 0;
        //循环遍历
        for(ZSetOperations.TypedTuple typle:typedTuples){
            Map<String, Object> userRankMap = new HashMap<>();
            userRankMap.put("coid",typle.getValue());
            userRankMap.put("count",typle.getScore().intValue());
            userRankMap.put("rank",++index);
            String userInfo = redisTemplate.opsForValue().get(Ranking.HOTCOURSE.getName()+ "_" + typle.getValue());
            rank.add(userRankMap);
        }
        return rank;
    }


    public static  List<Map<String, Object>> selectCourseHotBoutique(){
        List<Map<String,Object>> rank = new ArrayList<>();
        //只取前十条
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(Ranking.BOUTIQUE.getName(), 0, 99999, 0, 8);
        int index = 0;
        //循环遍历
        for(ZSetOperations.TypedTuple typle:typedTuples){
            Map<String, Object> userRankMap = new HashMap<>();
            userRankMap.put("coid",typle.getValue());
            userRankMap.put("count",typle.getScore().intValue());
            userRankMap.put("rank",++index);
            String userInfo = redisTemplate.opsForValue().get(Ranking.BOUTIQUE.getName()+ "_" + typle.getValue());
            rank.add(userRankMap);
        }
        return rank;
    }

}
