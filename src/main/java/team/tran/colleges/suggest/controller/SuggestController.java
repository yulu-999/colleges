package team.tran.colleges.suggest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tran.colleges.suggest.service.ISuggestService;

import java.util.Map;

/**
 * @className: SuggestController
 * @description: TODO 控制层
 * @author: tran
 * @date: 2021/5/31
 **/
@RestController
@CrossOrigin("*")
public class SuggestController {


    @Autowired
    @Qualifier("SuggestServiceImpl")
    private ISuggestService suggestService;

    /**
     * 提出建议
     * @param token 学生的token
     * @param suggestMsg 提出的建议
     * @param id 课程id
     * @return Map
     */
    @RequestMapping("/course/suggest")
    Map<String ,Object> addSuggest(@RequestHeader("token") String token, String suggestMsg, String id){
        return suggestService.addSuggest(token,suggestMsg,id);
    }

    /**
     * 教师回复建议
     * @param token 教师的token
     * @param con 回复的内容
     * @param id   学生的id
     * @param sid   课程的id
     * @return Map
     */
    @RequestMapping("/course/reply")
    Map<String ,Object> replySuggest(@RequestHeader("token") String token,String con,String id,@RequestHeader("sid")String sid){
        return suggestService.replaySuggest(token,con,id,sid);
    }

    /**
     * @param: token 学生 token
     * @param: state 状态
     * @description: TODO 根据状态获取提出的建议
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @RequestMapping("/course/set")
    Map<String ,Object> getSuggestByState(@RequestHeader("token")String token,Integer state,Integer page,Integer size){
        return suggestService.getSuggestByState(token,state,page,size);
    }

    /**
     * @param: token 教师 token
     * @param: state 是否回复
     * @description: TODO 获取课程 state ：1-已经回复 0-没有回复
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @RequestMapping("/course/state")
    Map<String ,Object> getTeaSuggestByState(@RequestHeader("token")String token,Integer state,Integer page,Integer size){
        return suggestService.getTeaSuggestByState(token,state,page,size);
    }

}
