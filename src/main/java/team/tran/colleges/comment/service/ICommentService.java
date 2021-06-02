package team.tran.colleges.comment.service;

import java.util.Map;

/**
 * @className: ICommentService
 * @description: TODO 课程点评
 * @author: tran
 * @date: 2021/5/31
 **/
public interface ICommentService {


    /**
     * @param: token  学生的 token
     * @param: id 课程的id
     * @param: grade 评级  1 ：优 2 ：好  3 : 一般
     * @description: TODO 学生对课程经行评级 只能点评自已修过的课程
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/5/31
     */
    Map<String, Object> stuRemark(String token,String id,Integer grade,String text);


    /**
     * @param: token
     * @param: id
     * @description: TODO 对点评进行删除 只能删除自己的
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    Map<String, Object> delRemark(String token,String id);


    /**
     * @param: token 学生的 token
     * @description: TODO 获取自己所有点评过的信息
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    Map<String, Object> selectRemark(String token,Integer page,Integer size);

    /**
     * 教师和学生的登录
     * @param type 类型 为1学生登录 为2教师登录
     * @param name 账号
     * @param password 密码
     * @return Map
     */
    Map<String ,Object> login(String type,String name,String password);

}
