package team.tran.colleges.course.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import team.tran.colleges.course.dao.CourseDao;
import team.tran.colleges.course.service.ICourseService;
import team.tran.colleges.entity.Course;
import team.tran.colleges.entity.RemarkInfo;
import team.tran.colleges.entity.Suggest;
import team.tran.colleges.hotdata.HotCourse;
import team.tran.colleges.utils.DataUtil;
import team.tran.colleges.utils.HotUtils;
import team.tran.colleges.utils.Ranking;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @className: CourseServiceImpl
 * @description: TODO 课程热榜 课程搜索 实现
 * @author: tran
 * @date: 2021/5/31
 **/
@Service("CourServiceImpl")
public class CourseServiceImpl implements ICourseService {


    @Autowired
    private CourseDao courseDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * @param: page 页数
     * @param: size 每页大小
     * @param: msg 查询信息
     * @description: TODO 条件 1.课程名 2.教师名 3.
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> LikeCourse(Integer page, Integer size, String msg) {
        // 验证参数
        if (page == null || page == 0)
            page = 1;
        if (size == null)
            size = 20;
        page = (page - 1) * size;
        // 模糊查询
        List<Map<String, Object>> data = courseDao.selectCourseByMsg(page, size, msg);
        Integer integer = courseDao.selectCount(null);

        //类型转换
        data.forEach(item -> {
            item.put("time", DataUtil.dataTime(item.get("time").toString()));
        });
        // 返回数据
        return DataUtil.printf(0,  "获取成功", data,integer/size+1);
    }

    /**
     * @param: page
     * @param: size
     * @description: TODO 精品推荐
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectCourse(Integer page, Integer size) {
        // 验证参数
        if (page == null || page == 0)
            page = 1;
        if (size == null)
            size = 8;
        // 修改page
        page = (page - 1) * size;
        //获取精品
        List<Map<String, Object>> maps = HotUtils.selectCourseHot();
        System.out.println("这是排序了的数据" + maps);
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        List<JSONObject> list = new ArrayList();
        for (Map<String, Object> stringObjectMap : maps) {
            String coid = stringObjectMap.get("coid") + "_course";
            String s = stringStringValueOperations.get(coid);
            JSONObject jsonObject = JSON.parseObject(s);
            list.add(jsonObject);
        }
        // 返回数据
        return DataUtil.printf(0,"获取成功", list,maps.size()/size+1);
    }

    /**
     * @param: page
     * @param: size
     * @description: TODO 最新信息
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectCourseByTime(Integer page, Integer size) {
        // 页数修改
        DataUtil.updatePage(page, size);
        // 查询数据
        List<Map<String, Object>> data = courseDao.selectCourse(page, size);
        //类型转换
        data.forEach(item -> {
            item.put("time", DataUtil.dataTime(item.get("time").toString()));
        });
        // 返回数据
        return DataUtil.printf(0, data.size(), "获取成功", data);
    }

    /**
     * @param: id 课程id
     * @description: TODO 根据课程id获取课程信息
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectCourseById(String id) {
        // 验证参数
        if (id == null || id.equals(""))
            return DataUtil.printf(-5, "参数错误");


        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String courseJson = operations.get(id + "_course");
        Object parse = JSONObject.parse(courseJson);

        // 没有查询到数据
        if (parse == null) {
            return DataUtil.printf(-2, "没有该课程信息");
        }
        else {
            //添加到redis里
            HotUtils.addCourse(Ranking.BOUTIQUE, id);
            return DataUtil.printf(0, "获取成功", parse);
        }

    }

    /**
     * 热榜
     * @param page 页数
     * @param size 条数
     * @return
     */
    @Override
    public Map<String, Object> selectCourseHot(Integer page, Integer size) {
        // 验证参数
        if (page == null || page == 0)
            page = 1;
        if (size == null)
            size = 8;
        // 修改page
        page = (page - 1) * size;
        List<Map<String, Object>> data = HotUtils.selectCourseHot();
        System.out.println("这是排序了的数据" + data);
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        List<JSONObject> list = new ArrayList();
        for (Map<String, Object> datum : data) {
            String coid = datum.get("coid") + "_course";
            String s = stringStringValueOperations.get(coid);
            JSONObject jsonObject = JSON.parseObject(s);
            list.add(jsonObject);
        }

        // 返回数据
        return DataUtil.printf(0,"获取成功", list,data.size()/size+1);
    }
}
