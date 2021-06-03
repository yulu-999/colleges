package team.tran.colleges.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tran.colleges.comment.service.ICommentService;

import java.util.Map;

/**
 * @className: CommentController
 * @description: TODO 课程点评 控制层
 * @author: tran
 * @date: 2021/5/31
 **/
@RestController
public class CommentController {

    @Autowired
    @Qualifier("CommentServiceImpl")
    private ICommentService commentService;


    /**
     * @param: token  学生的 token
     * @param: id 课程的id
     * @param: grade 评级  1 ：优 2 ：好  3 : 一般
     * @description: TODO 学生对课程经行评级 只能点评自已修过的课程
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/5/31
     */
    @RequestMapping("/comment/remark")
    Map<String,Object> stuRemark(@RequestHeader("token") String token, String id, Integer grade, String text){
        return commentService.stuRemark(token,id,grade,text);
    }

    @RequestMapping("/comment/del")
    Map<String ,Object> delRemark(@RequestHeader("token")String token,String id){
        return commentService.delRemark(token,id);
    }

    /**
     * 教师和学生的登录
     * @param type 类型 为1学生登录 为2教师登录
     * @param name 账号
     * @param password 密码
     * @return Map
     */
    @RequestMapping("/login")
    Map<String,Object> login(String type,String name,String password){
        return commentService.login(type,name,password);
    }

    /**
     * 获取自己的留言
     * @param token 学生的token
     * @param page 页数
     * @param size 大小
     * @return Map
     */
    @RequestMapping("/comment/get")
    Map<String ,Object> selectRemark(@RequestHeader("token")String token,Integer page,Integer size){
        return commentService.selectRemark(token,page,size);
    }


}
