package team.tran.colleges.suggest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tran.colleges.entity.Suggest;

/**
 * @className: SuggestDao
 * @description: TODO 提出宝贵的建议Dao
 * @author: tran
 * @date: 2021/5/31
 **/
@Mapper
public interface SuggestDao  extends BaseMapper<Suggest>{
}
