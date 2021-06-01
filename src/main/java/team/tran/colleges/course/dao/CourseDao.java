package team.tran.colleges.course.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.tran.colleges.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * @className: CourseDao
 * @description: TODO 课程热榜 课程搜索 dao
 * @author: tran
 * @date: 2021/5/31
 **/
@Mapper
public interface CourseDao extends BaseMapper<Course> {

    /**
     * @param:
     * @description: TODO 模糊查询
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: tran
     * @date: 2021/6/1
     */
    @Select("SELECT course.coid as id,course.coname as coName ,teacher.tname as tName ,course.creatTime as time FROM course LEFT JOIN teacher on course.tid=teacher.tid WHERE course.coname LIKE '%${msg}%' or course.tid in (SELECT tid FROM teacher WHERE teacher.tname LIKE '%${msg}%' ) LIMIT #{page},#{size}")
    List<Map<String, Object>> selectCourseByMsg(@Param("page") Integer page ,@Param("size") Integer size ,@Param("msg") String msg);


    /**
     * @param: page
     * @param: size
     * @description: TODO 获取全部分页
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: tran
     * @date: 2021/6/1
     */
    @Select("SELECT course.coid as id,course.coname as coName ,teacher.tname as tName ,course.creatTime as time FROM course LEFT JOIN teacher on course.tid=teacher.tid RDER BY course.coid DESC  LIMIT #{page},#{size}")
    List<Map<String, Object>> selectCourse(Integer page, Integer size);
}
