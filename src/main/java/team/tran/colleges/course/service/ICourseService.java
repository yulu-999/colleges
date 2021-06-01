package team.tran.colleges.course.service;

import java.util.Map;

/**
 * @className: ICourseService
 * @description: TODO 课程热榜 课程搜索
 * @author: tran
 * @date: 2021/5/31
 **/
public interface ICourseService {

    /**
     * 根据条件模糊查询
     * @param page 页数
     * @param size 条数
     * @param msg 条件
     * @return Map
     */
    Map<String,Object> LikeCourse(Integer page,Integer size,String msg);


    /**
     * 课程热榜
     * @param page 页数
     * @param size 条数
     * @return Map
     */
    Map<String ,Object> selectCourse(Integer page,Integer size);



    /**
     * 最新课程
     * @param page 页数
     * @param size 条数
     * @return Map
     */
    Map<String ,Object> selectCourseByTime(Integer page,Integer size);



    /**
     * @param: id 课程id
     * @description: TODO 根据课程id获取课程信息
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    Map<String, Object> selectCourseById(String id);







}
