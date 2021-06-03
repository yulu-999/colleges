package team.tran.colleges.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tran.colleges.course.service.ICourseService;

import java.util.Map;

/**
 * @className: CourseController
 * @description: TODO 课程热榜 课程搜索 实现
 * @author: tran
 * @date: 2021/5/31
 **/
@RestController
@CrossOrigin("")
public class CourseController {

    @Autowired
    @Qualifier("CourServiceImpl")
    private ICourseService courseService;

    /**
     * 精品推荐
     * @param page 页数
     * @param size 条数
     * @return Map
     */
    @RequestMapping("/course/hot")
    Map<String,Object> selectCourse(Integer page,Integer size){
        return courseService.selectCourse(page,size);
    }

    /**
     * 根据条件模糊查询
     * @param page 页数
     * @param limit 条数
     * @param msg 条件
     * @return Map
     */
    @RequestMapping("course/search")
    Map<String ,Object> LikeCourse(Integer page, Integer limit, String msg){
        return courseService.LikeCourse(page,limit,msg);
    }

    /**
     * 最新课程
     * @param page 页数
     * @param size 条数
     * @return Map
     */
    @RequestMapping("/course/newest")
    Map<String ,Object> selectCourseByTime(Integer page,Integer size){
        return courseService.selectCourseByTime(page,size);
    }

    /**
     * @param: id 课程id
     * @description: TODO 根据课程id获取课程信息
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @RequestMapping("/course/courseById")
    Map<String ,Object> selectCourseById(String id){
        return courseService.selectCourseById(id);
    }

    /**
     * 课程热榜
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/course/boutique")
    Map<String ,Object> selectCourseHot(Integer page,Integer size){
        return courseService.selectCourseHot(page,size);
    }
}
