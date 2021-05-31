package team.tran.colleges.entity;

import lombok.Data;

/**
 * @className: Course
 * @description: TODO 课程
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
public class Course {
    // 课程id
    private String coId;
    // 课程名字
    private String coName;
    // 教师id
    private String tid;
    // 课程最大人数
    private Integer coMax;
    // 课程类类型
    private String coType;
    // 课程是否开启
    private Integer coStart;
    // 周次
    private String coWeek;
}
