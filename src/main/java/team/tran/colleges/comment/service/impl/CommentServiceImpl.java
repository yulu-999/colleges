package team.tran.colleges.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.tran.colleges.comment.dao.CommentDao;
import team.tran.colleges.comment.service.ICommentService;

import team.tran.colleges.entity.RemarkInfo;
import team.tran.colleges.entity.Suggest;
import team.tran.colleges.utils.DataUtil;
import team.tran.colleges.utils.IDUtil;
import team.tran.colleges.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: CommentServiceImpl
 * @description: TODO 课程点评接口实现
 * @author: tran
 * @date: 2021/5/31
 **/
@Service("CommentServiceImpl")
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * @param: token  学生的 token
     * @param: id 课程的id
     * @param: grade 评级  1 ：优 2 ：好  3 : 一般
     * @description: TODO 学生对课程经行评级 只能点评自已修过的课程
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/5/31
     */
    @Override
    public Map<String, Object> stuRemark(String token, String id, Integer grade, String text) {
        //1.验证参数
        if (token == null || token.equals("")) {
            return DataUtil.printf(-1, "参数为空");
        }
        if (id == null || id.equals("") || grade == null || grade.equals("")) {
            return DataUtil.printf(-1, "参数为空");
        }
        // 2. 验证 token
        String myId = TokenUtils.getToken(token);
        if (myId == null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        RemarkInfo remarkInfo = new RemarkInfo();
        if (grade == 1) {
            //课程点评的id
            remarkInfo.setRemarkInfoId(IDUtil.getID());
            //学生id
            remarkInfo.setSid(myId);
            //课程id
            remarkInfo.setCoid(id);
            //点评信息
            remarkInfo.setRemarkInfoText(text);
            //点评的等级
            remarkInfo.setRemarkInfoMsg(1);
            //点评时间
            remarkInfo.setCreateTime(System.currentTimeMillis() + "");
            //添加数据
            commentDao.insert(remarkInfo);
            return DataUtil.printf(0, "点评成功");
        } else if (grade == 2) {
            //课程点评的id
            remarkInfo.setRemarkInfoId(IDUtil.getID());
            //学生id
            remarkInfo.setSid(myId);
            //课程id
            remarkInfo.setCoid(id);
            //点评信息
            remarkInfo.setRemarkInfoText(text);
            //点评的等级
            remarkInfo.setRemarkInfoMsg(2);
            //点评时间
            remarkInfo.setCreateTime(System.currentTimeMillis() + "");
            //添加数据
            System.out.println("-----------------");
            System.err.println(myId);
            commentDao.insert(remarkInfo);
            return DataUtil.printf(0, "点评成功");
        } else if (grade == 3) {
            //课程点评的id
            remarkInfo.setRemarkInfoId(IDUtil.getID());
            //学生id
            remarkInfo.setSid(myId);
            //课程id
            remarkInfo.setCoid(id);
            //点评信息
            remarkInfo.setRemarkInfoText(text);
            //点评的等级
            remarkInfo.setRemarkInfoMsg(3);
            //点评时间
            remarkInfo.setCreateTime(System.currentTimeMillis() + "");
            //添加数据
            commentDao.insert(remarkInfo);
            return DataUtil.printf(0, "点评成功");
        } else
            return DataUtil.printf(-1, "请选择正确的等级");

    }

    /**
     * @param: token
     * @param: id
     * @description: TODO 对点评进行删除 只能删除自己的
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> delRemark(String token, String id) {
        return null;
    }

    /**
     * @param: token 学生的 token
     * @description: TODO 获取自己所有点评过的信息
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectRemark(String token,Integer page,Integer size) {

        //  验证 token
        if (token == null || token.equals(""))
            return DataUtil.printf(-1, "参数为空");
        String myId = TokenUtils.getToken(token);
        if (myId == null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        // 页数修改
        DataUtil.updatePage(page,size);
        // 查询数据
        QueryWrapper<RemarkInfo> query = new QueryWrapper<>();
        HashMap<String, Object> abs = new HashMap<>();
        query.eq("sid",myId);
        query.last(" limit " + page+","+size);
        commentDao.selectList(query);
        // 返回数据
        return DataUtil.printf(0,"获取成功",query.last(" limit " + page+","+size));

    }
}
