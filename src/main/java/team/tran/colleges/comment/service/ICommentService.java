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
     * @description: TODO 学生对课程经行评级
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/5/31
     */
    Map<String, Object> stuRemark(String token,String id,Integer grade);




}
