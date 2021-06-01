package team.tran.colleges.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @className: TokenUtils
 * @description: TODO 类描述
 * @author: tran
 * @date: 2021/6/1
 **/

@Component
public class TokenUtils {

    @Autowired
    private static RedisTemplate<String, String> redisTemplate;


    /**
     * @param: token 用户的token
     * @description: TODO 验证token获取id
     * @return: java.lang.String 用户id
     * @author: tran
     * @date: 2021/6/1
     */
    public static String getToken(String token) {
        //获取操作键值对象
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        //更新失效时间
        String id = operations.get(token);
        assert id != null;
        operations.set(token,id,1, TimeUnit.DAYS);
        return id;
    }


    /**
     * @param: id 用户的id
     * @description: TODO 生成token 把token存入redis
     * @return: java.lang.String 生成的 token
     * @author: tran
     * @date: 2021/6/1
     */
    public static String createToken(String id){
        //获取操作键值对象
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        // 生成token
        String token = IDUtil.getID()+"token";
        // 存入并设置失效时间为1天时间为
        operations.set(token,id,1, TimeUnit.DAYS);
        // 返回 Token
        return token;
    }




}
