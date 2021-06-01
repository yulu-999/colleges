package team.tran.colleges.suggest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.tran.colleges.suggest.dao.SuggestDao;
import team.tran.colleges.suggest.service.ISuggestService;

import java.util.Map;

/**
 * @className: SuggestServiceImpl
 * @description: TODO 提出宝贵的建议
 * @author: tran
 * @date: 2021/5/31
 **/
@Service
public class SuggestServiceImpl implements ISuggestService {

    @Autowired
    private SuggestDao suggestDao;

    /**
     * 提出建议
     * @param token 学生的token
     * @param suggestMsg 提出的建议
     * @param id 课程id
     * @return Map
     */
    @Override
    public Map<String, Object> addSuggest(String token, String suggestMsg, String id) {
        
        return null;
    }
}
