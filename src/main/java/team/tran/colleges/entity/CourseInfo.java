package team.tran.colleges.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 天才选手
 * @ClssName CourseInfo
 * @desc
 * @Time 2021/6/1 10:23
 * @Version 1.0
 **/
@Data
public class CourseInfo {
    //选课记录id
    private String lid;
    //学生id
    private String uid;
    //课程id
    private String cid;
    //加入时间
    private Date lBirth;
    //是否删除
    private Integer ldel;
}
