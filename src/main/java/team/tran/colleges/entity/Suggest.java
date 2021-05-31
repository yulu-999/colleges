package team.tran.colleges.entity;

import lombok.Data;

/**
 * @className: Suggest
 * @description: TODO 课程建议表
 * @author: tran
 * @date: 2021/5/31
 **/
@Data
public class Suggest {
    // 程建议id
    private String suggestId;
    // 学生id
    private String sid;
    // 课程id
    private String coId;
    // 课程建议
    private String suggestMsg;
    // 提出建议时间
    private String createTime;
    // 是否删除
    private Integer isDelete;
}
