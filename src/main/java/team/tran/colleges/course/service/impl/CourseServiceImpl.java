package team.tran.colleges.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.tran.colleges.course.dao.CourseDao;
import team.tran.colleges.course.service.ICourseService;
import team.tran.colleges.entity.Course;
import team.tran.colleges.utils.DataUtil;

import java.util.List;
import java.util.Map;

/**
 * @className: CourseServiceImpl
 * @description: TODO 课程热榜 课程搜索 实现
 * @author: tran
 * @date: 2021/5/31
 **/
@Service
public class CourseServiceImpl implements ICourseService {


    @Autowired
    private CourseDao courseDao;


    /**
     * @param: page 页数
     * @param: size 每页大小
     * @param: msg 查询信息
     * @description: TODO 条件 1.课程名 2.教师名 3.
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> LikeCourse(Integer page, Integer size, String msg) {
        // 页数修改
        DataUtil.updatePage(page,size);
       // 修改page
        page = (page-1)*size;
       // 模糊查询
        List<Map<String, Object>> data = courseDao.selectCourseByMsg(page, size, msg);
        //类型转换
        data.forEach(item->{
            item.put("time",DataUtil.dataTime(item.get("time").toString()));
        });
        // 返回数据
        return DataUtil.printf(0,data.size(),"获取成功",data);
    }
    /**
     * @param: page
     * @param: size
     * @description: TODO 课程热榜
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectCourse(Integer page, Integer size) {
        return null;
    }

    /**
     * @param: page
     * @param: size
     * @description: TODO 最新信息
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectCourseByTime(Integer page, Integer size) {
        // 页数修改
        DataUtil.updatePage(page,size);
        // 查询数据
        List<Map<String, Object>> data = courseDao.selectCourse(page, size);
        //类型转换
        data.forEach(item->{
            item.put("time",DataUtil.dataTime(item.get("time").toString()));
        });
        // 返回数据
        return DataUtil.printf(0,data.size(),"获取成功",data);
    }

    /**
     * @param: id 课程id
     * @description: TODO 根据课程id获取课程信息
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectCourseById(String id) {
        // 验证参数
        if (id==null||id.equals(""))
            return DataUtil.printf(-5,"参数错误");
        // 查询数据
        Course course = courseDao.selectById(id);
        // 没有查询到数据
        if (course==null)
            return DataUtil.printf(-2,"没有该课程信息");
        else
            return DataUtil.printf(0,"获取成功",course);
    }






}
