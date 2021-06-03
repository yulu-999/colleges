package team.tran.colleges.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class CourseController {

    @Autowired
    @Qualifier("CourServiceImpl")
    private ICourseService courseService;

    @RequestMapping("/course/hot")
    Map<String,Object> selectCourse(Integer page,Integer size){
        return courseService.selectCourse(page,size);
    }

}
