package team.tran.colleges.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import team.tran.colleges.entity.RemarkInfoMongodb;
import team.tran.colleges.entity.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface RemarkInfoMongodbDao extends BaseMapper<RemarkInfoMongodb> {
    @Select("SELECT\n" +
            "\tremarkinfo.remarkinfoid AS id,\n" +
            "\tstudent.uname AS sname,\n" +
            "  remarkinfo.coid as coid,\n" +
            "\tcourse.coname AS coname,\n" +
            "\tremarkinfo.remarkinfotext AS text,\n" +
            "\tremarkinfo.remarkinfomsg AS grade,\n" +
            "\tremarkinfo.createTime AS time\n" +
            "FROM\n" +
            "\tremarkinfo\n" +
            "LEFT JOIN course ON remarkinfo.coid = course.coid LEFT JOIN student on remarkinfo.sid=student.uid")
    List<Map<String, Object>> selectMonodb();

    @Select("select * from student where uid=#{myId}")
    List<Student> selectName(String myId);
}
