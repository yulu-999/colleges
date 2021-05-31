package team.tran.colleges.suggest.service;

import java.util.Map;

/**
 * @className: ISuggestService
 * @description: TODO 提出宝贵的建议
 * @author: tran
 * @date: 2021/5/31
 **/
public interface ISuggestService {

    /**
     * 提出建议
     * @param token 学生的token
     * @param suggestMsg 提出的建议
     * @param id 课程id
     * @return Map
     */
    Map<String ,Object> addSuggest(String token,String suggestMsg,String id);
}
