package team.tran.colleges.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import team.tran.colleges.comment.dao.CommentDao;
import team.tran.colleges.comment.service.ICommentService;

import team.tran.colleges.entity.RemarkInfo;
import team.tran.colleges.entity.RemarkInfoMongodb;
import team.tran.colleges.entity.Student;
import team.tran.colleges.entity.Teacher;
import team.tran.colleges.mapper.RemarkInfoDao;
import team.tran.colleges.mapper.RemarkInfoMongodbDao;
import team.tran.colleges.mapper.StudentDao;
import team.tran.colleges.mapper.TeacherDao;
import team.tran.colleges.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private RemarkInfoDao remarkInfoDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RemarkInfoMongodbDao remarkInfoMongobdDao;
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
        // 查询数据
        QueryWrapper<RemarkInfo> query = new QueryWrapper<>();
        query.eq("sid",myId);
        query.eq("coid",id);
        RemarkInfo select =remarkInfoDao.selectOne(query);
        if (select==null){
            String rid = IDUtil.getID();
            RemarkInfo remarkInfo = new RemarkInfo();
            //课程点评的id
            remarkInfo.setRemarkInfoId(rid);
            //学生id
            remarkInfo.setSid(myId);
            //课程id
            remarkInfo.setCoid(id);
            //点评信息
            remarkInfo.setRemarkInfoText(text);
            //点评的等级
            remarkInfo.setRemarkInfoMsg(grade);
            //点评时间
            remarkInfo.setCreateTime(System.currentTimeMillis() + "");
            //添加数据
            //List<Map<String, Object>> maps = remarkInfoMongobdDao.selectMonodb();
            // mongoTemplate.insert(maps);
            RemarkInfoMongodb remarkInfoMongodb=new RemarkInfoMongodb();
            //remarkinfomongdbId
            remarkInfoMongodb.setRemakerInfoId(rid);
            //查询学生姓名
            List<Student> list=remarkInfoMongobdDao.selectName(myId);
            System.out.println(list);
            //获取学生姓名
            remarkInfoMongodb.setName(list.get(0).getUname());
            //课程id
            remarkInfoMongodb.setCoId(id);
            //等级
            remarkInfoMongodb.setGarde(grade.toString());
            //备注
            remarkInfoMongodb.setText(text);
            //时间戳
            remarkInfoMongodb.setCreateTime(System.currentTimeMillis()+"");
            //添加到mongodb
            RemarkInfoMongodb insert = mongoTemplate.insert(remarkInfoMongodb);
            //添加到数据库
            commentDao.insert(remarkInfo);
            //判断如果星级大于2的话给他加入精品榜
            if (grade>2){
                HotUtils.addCourse(Ranking.HOTCOURSE,id);
            }
            return DataUtil.printf(0, "点评成功");
        }else {
            return DataUtil.printf(-1,"你已经点评过了");
        }



    }

    /**
     * @param: token
     * @param: id
     * @description: TODO 对点评进行删除 只能删除自己的
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> delRemark(String token, String id) {
        //1.验证参数
        if (token == null || token.equals("")) {
            return DataUtil.printf(-1, "参数错误");
        }
        if (id == null || id.equals("")) {
            return DataUtil.printf(-1, "参数错误");
        }
        String myId = TokenUtils.getToken(token);
        System.out.println(myId);
        if (myId == null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        //获取操作键值对象
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        System.out.println("用户id"+myId);
        QueryWrapper<RemarkInfo> delete = new QueryWrapper<>();
        delete.eq("sid", myId);
        delete.eq("remarkinfoid",id);
        int remarkInfo = commentDao.delete(delete);
        if (remarkInfo>0){
            return DataUtil.printf(0,"删除成功" );
        }else {
            return DataUtil.printf(-1,"删除失败");
        }

    }

    /**
     * @param: token 学生的 token
     * @description: TODO 获取自己所有点评过的信息
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: tran
     * @date: 2021/6/1
     */
    @Override
    public Map<String, Object> selectRemark(String token, Integer page, Integer size) {
        //  验证 token
        if (token == null || token.equals(""))
            return DataUtil.printf(-1, "参数为空");
        String myId = TokenUtils.getToken(token);
        System.out.println(myId);
        if (myId == null) {
            return DataUtil.printf(-1, "请重新登录");
        }
        // 页数修改
        DataUtil.updatePage(page, size);
        // 查询数据
        QueryWrapper<RemarkInfo> query = new QueryWrapper<>();
        HashMap<String, Object> abs = new HashMap<>();
        query.eq("sid", myId);
        query.last(" limit " + page + "," + size);
        List<RemarkInfo> remarkInfos = commentDao.selectList(query);
        // 返回数据
        return DataUtil.printf(0, "获取成功",remarkInfos);
    }

    /**
     * 教师和学生的登录
     * @param type 类型 为1学生登录 为2教师登录
     * @param name 账号
     * @param password 密码
     * @return Map
     */
    @Override
    public Map<String, Object> login(String type, String name, String password) {
        //1.验证参数
        if (type == null || type.equals("")) {
            type="1";
        }
        if (name == null || name.equals("") || password == null || password.equals("")) {
            return DataUtil.printf(-1, "请输入账号或密码");
        }
        //判断是学生登录还是教师登录
        if (type.equals("1")) {
            QueryWrapper<Student> query = new QueryWrapper<>();
            query.eq("unumber", name);
            Student student = studentDao.selectOne(query);
            //判断是不是该校学生
            if (student == null) {
                return DataUtil.printf(-1, "你不是本校学生");
            } else {
                String password1 = student.getPassword();
                //判断密码是否正确
                if (password1.equals(password)) {
                    String token = TokenUtils.createToken(student.getUid());
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", token);
                    return DataUtil.printf(0, "登录成功", map);
                } else {
                    return DataUtil.printf(-1, "密码错误");
                }
            }
        } else {
            QueryWrapper<Teacher> query = new QueryWrapper<>();
            query.eq("tnumber", name);
            Teacher teacher = teacherDao.selectOne(query);
            //判断是不是该校老师
            if (teacher == null) {
                return DataUtil.printf(-1, "你不是本校老师");
            } else {
                String password1 = teacher.getPassword();
                //判断密码是否正确
                if (password1.equals(password)) {
                    String token = TokenUtils.createToken(teacher.getTid());
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", token);
                    return DataUtil.printf(0, "登录成功", map);
                } else {
                    return DataUtil.printf(-1, "密码错误");
                }
            }
        }
    }

    /**
     * 根据课程id从mongodb里面找到留言
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> selectEvaluate(String id) {
        Map<String ,Object> map=new HashMap<>();
        //用来封装所有条件的对象
        Query query = new Query();
        //用来构建条件
        Criteria criteria = new Criteria();
        //在mongodb里查询课程=id的
        Query coid = query.addCriteria(criteria.and("coId").is(id));

        List<RemarkInfoMongodb> list = mongoTemplate.find(query, RemarkInfoMongodb.class);
        list.forEach(item -> {
            item.setCreateTime( DataUtil.dataTime(item.getCreateTime().toString()));
        });
        return DataUtil.printf(0,"获取所有评论成功",list);
    }
}
