package team.tran.colleges.suggest.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.tran.colleges.entity.Suggest;
import team.tran.colleges.mapper.StudentDao;
import team.tran.colleges.suggest.dao.SuggestDao;
import team.tran.colleges.suggest.service.ISuggestService;
import team.tran.colleges.utils.DataUtil;
import team.tran.colleges.utils.IDUtil;
import team.tran.colleges.utils.TokenUtils;

import java.util.Date;
import java.util.Map;

/**
 * @className: SuggestServiceImpl
 * @description: TODO 提出宝贵的建议
 * @author: tran
 * @date: 2021/5/31
 **/
@Service("SuggestServiceImpl")
public class SuggestServiceImpl implements ISuggestService {


    @Autowired
    private SuggestDao suggestDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 提出建议
     * @param token 学生的token
     * @param suggestMsg 提出的建议
     * @param id 课程id
     * @return Map
     */
    @Override
    public Map<String, Object> addSuggest(String token, String suggestMsg, String id) {
        // 1. 验证参数
        if (token==null||token.equals(""))
            return DataUtil.printf(-1,"请重新登录");
        if (suggestMsg==null||suggestMsg.equals("")||id==null||id.equals(""))
            return  DataUtil.printf(-5,"参数错误");
        // 2. 验证 token
        String myId = TokenUtils.getToken(token);
        if (myId==null)
            return DataUtil.printf(-1,"请重新登录");
        // 3. 存入数据
        Suggest suggest = new Suggest();
        // id
        suggest.setSuggestId(IDUtil.getID());
        // 学生id
        suggest.setSid(myId);
        // 课程id
        suggest.setCoId(id);
        // 建议
        suggest.setSuggestMsg(suggestMsg);
        // 创建时间
        suggest.setCreateTime(System.currentTimeMillis()+"");
        // 是否删除
        suggest.setIsDelete(1);
        // 是否已读
        suggest.setIsRead(0);
        // 增加数据
        suggestDao.insert(suggest);

        return DataUtil.printf(0,"发送成功");
    }

    /**
     * 教师回复建议
     * @param token 教师的token
     * @param con 回复的内容
     * @param id   学生的id
     * @param sid   课程的id
     * @return Map
     */
    @Override
    public Map<String, Object> replaySuggest(String token, String con, String id,String sid) {
        //1.验证参数
        if (token==null||token.equals(" ")){
            return DataUtil.printf(-1,"请重新登录");
        }
        if (con==null||con.equals(" ")||id==null||id.equals(" ")){
            return DataUtil.printf(-1,"参数错误");
        }
        //2.验证token
        String myId = TokenUtils.getToken(token);
        if (myId==null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        Suggest suggest=new Suggest();
        //id
        suggest.setSuggestId(sid);
        //学生id
        suggest.setSid(id);
        //回复的信息
        suggest.setReplyMsg(con);
        //提出的时间
        suggest.setCreateTime(System.currentTimeMillis()+"");
        //是否删除
        suggest.setIsDelete(1);
        //是否已读
        suggest.setIsRead(0);
        //添加回复
        suggestDao.updateById(suggest);
        return DataUtil.printf(0,"回复建议成功");
    }


}
