package team.tran.colleges.suggest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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



}
