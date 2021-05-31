package team.tran.colleges.entity;

import lombok.Data;

/**
 * @className: RemarkInfo
 * @description: TODO 点评信息表
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
public class RemarkInfo {
    //点评信息id
    private String remarkInfoId;
    // 点评等级
    private Integer remarkInfoMsg;
    // 学生id
    private String sid;
    // 课程id
    private String cid;
    // 点评时间
    private String createTime;

}
