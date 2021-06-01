package team.tran.colleges.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tran.colleges.entity.RemarkInfo;


/**
 * @className: CommentDao
 * @description: TODO 课程点评 Doo
 * @author: tran
 * @date: 2021/5/31
 **/
@Mapper
public interface CommentDao  extends BaseMapper<RemarkInfo> {
}
