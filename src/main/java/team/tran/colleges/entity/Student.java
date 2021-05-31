package team.tran.colleges.entity;

import lombok.Data;

/**
 * @className: Student
 * @description: TODO 学生表
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
public class Student {
    // 学生id
    private String  uid;
    // 学生姓名
    private String  uname;
    // 学生性别
    private String  sex;
    //  学生出生日期
    private String  birth;
    // 学生学号
    private String  uNumber;
    //  学生登录密码
    private String  password;
    //  学生班级id
    private String  cid;
    //  学校名称
    private String  school;
    //  token
    private String  token;

}
