package team.tran.colleges.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * @author 天才选手
 * @ClssName RemarkInfoMongobd
 * @desc
 * @Time 2021/6/3 16:31
 * @Version 1.0
 **/
@Data
public class RemarkInfoMongodb {
    @TableId("remakerinfoid")
    private String remakerInfoId;
    private String name;
    private String coId;
    private String text;
    private String garde;
    private String createTime;
}
