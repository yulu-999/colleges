package team.tran.colleges.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @className: Suggest
 * @description: TODO 课程建议表
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
public class Suggest {
    @TableId("suggestid")
    // 程建议id
    private String suggestId;
    // 学生id
    private String sid;
    // 教师 id
    private String tid;
    // 回复的信息
    private  String replyMsg;
    // 课程id
    private String coId;
    // 课程建议
    private String suggestMsg;
    // 提出建议时间
    private String createTime;
    // 是否删除
    private Integer isDelete;
    //是否已读
    private Integer isRead;
}
