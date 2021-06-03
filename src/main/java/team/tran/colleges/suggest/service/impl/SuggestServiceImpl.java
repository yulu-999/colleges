package team.tran.colleges.suggest.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
import java.util.HashMap;
import java.util.List;
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
     * @param sid   建议的id
     * @return Map
     */
    @Override
    public Map<String, Object> replaySuggest(String token, String con, String id,String sid) {
        //1.验证参数
        if (token==null||token.equals("")){
            return DataUtil.printf(-1,"请重新登录");
        }
        if (con==null||con.equals("")||id==null||id.equals("")){
            return DataUtil.printf(-1,"参数错误");
        }
        //2.验证回复建议
        if (sid==null||sid.equals("")){
            return DataUtil.printf(-1,"请选择要回复的建议");
        }
        //3.验证token
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
       int i= suggestDao.updateById(suggest);

       //4.验证回复是否成功
       if (i>0){
           return DataUtil.printf(0,"回复建议成功");
       }else {
           return DataUtil.printf(-1,"回复建议失败");
       }
    }

    /**
     * @param: token 学生 token
     * @param: state 状态
     * @description: TODO 根据状态获取提出的建议
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> getSuggestByState(String token, Integer state,Integer page,Integer size) {
        // 修改page
        DataUtil.updatePage(page,size);
        // 验证 state
        if (state==null)
            return DataUtil.printf(-5,"参数为空");
        if (token==null||token.equals("")){
            return DataUtil.printf(-1,"请重新登录");
        }
        //验证token
        String myId = TokenUtils.getToken(token);
        if (myId==null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        // 查询数据
        QueryWrapper<Suggest> query = new QueryWrapper<>();
        HashMap<String, Object> abs = new HashMap<>();
        if (state==0)
            abs.put("isRead",0);
        else
            abs.put("isRead",1);
        query.allEq(abs);
        query.last(" limit " + page+","+size);
        // 查询
        List<Suggest> suggestList = suggestDao.selectList(query);
        return DataUtil.printf(0,"获取成功",suggestList);

    }

    /**
     * @param: token 教师 token
     * @param: state 是否回复
     * @description: TODO 获取课程 state ：1-已经回复 0-没有回复 null-获取全部
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> getTeaSuggestByState(String token, Integer state,Integer page,Integer size) {
        // 修改page
        DataUtil.updatePage(page,size);
        if (token==null||token.equals("")){
            return DataUtil.printf(-1,"请重新登录");
        }
        //验证token
        String myId = TokenUtils.getToken(token);
        if (myId==null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        QueryWrapper<Suggest> query = new QueryWrapper<>();
        HashMap<String, Object> abs = new HashMap<>();
        // 查询数据
        if (state==null){
            query.allEq(abs);

        }else if (state==0){
            query.eq("isRead",0);
        }else {
            query.eq("isRead",1);
        }

        query.last(" limit " + page+","+size);
        return DataUtil.printf(0,"获取成功",suggestDao.selectList(query));

    }

}
