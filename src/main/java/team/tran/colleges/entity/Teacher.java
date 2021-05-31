package team.tran.colleges.entity;

import lombok.Data;

/**
 * @className: Teacher
 * @description: TODO 教师表
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
public class Teacher {
    // 教师id
    private String  tid;
    // 教师姓名
    private String  tName;
    // 教师性别
    private String  sex;
    //  教师出生日期
    private String  birth;
    // 教师工号
    private String  tNumber;
    //  教师登录密码
    private String  password;
    //  学校名称
    private String  school;
    //  token
    private String  token;

}
