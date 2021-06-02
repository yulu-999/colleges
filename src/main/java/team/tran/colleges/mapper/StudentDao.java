package team.tran.colleges.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tran.colleges.entity.RemarkInfo;
import team.tran.colleges.entity.Student;

/**
 * @className: StudnetDao
 * @description: TODO 类描述
 * @author: tran
 * @date: 2021/6/1
 **/
@Mapper
public interface StudentDao extends BaseMapper<Student> {

}
