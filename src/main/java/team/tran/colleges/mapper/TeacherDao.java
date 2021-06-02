package team.tran.colleges.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tran.colleges.entity.Student;
import team.tran.colleges.entity.Teacher;

@Mapper
public interface TeacherDao extends BaseMapper<Teacher>
{
}
