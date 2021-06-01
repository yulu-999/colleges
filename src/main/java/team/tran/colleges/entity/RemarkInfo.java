package team.tran.colleges.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @className: RemarkInfo
 * @description: TODO 点评信息表
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
@TableName("remarkinfo")
public class RemarkInfo {

    //点评信息id
    @TableId("remarkinfoid")
    private String remarkInfoId;
    // 点评等级
    private Integer remarkInfoMsg;
    // 点评信息
    private String remarkInfoText;
    // 学生id
    private String sid;
    // 课程id
    private String coid;
    // 点评时间
    private String createTime;


}
