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


    /**
     * 教师回复建议
     * @param token 教师的token
     * @param con 回复的内容
     * @param id   学生的id
     * @param sid   课程的id
     * @return Map
     */
    public Map<String, Object> replaySuggest(String token, String con, String id,String sid);


    /**
     * @param: token 学生 token
     * @param: state 状态
     * @description: TODO 根据状态获取提出的建议
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    Map<String, Object> getSuggestByState(String token,Integer state,Integer page,Integer size);

    /**
     * @param: token 教师 token
     * @param: state 是否回复
     * @description: TODO 获取课程 state ：1-已经回复 0-没有回复
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    Map<String, Object> getTeaSuggestByState(String token,Integer state,Integer page,Integer size);


}
