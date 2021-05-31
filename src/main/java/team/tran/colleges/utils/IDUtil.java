package team.tran.colleges.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

/**
 * @className: IDUtils
 * @description: TODO 类描述
 * @author: tran
 * @date: 2021/5/20
 **/
@Component
public class IDUtil {

    private IDUtil(){}

    private final static Snowflake  SNOWFLAKE= IdUtil.getSnowflake(1, 1);

    /**
     * @param:
     * @description: TODO 生成 ID
     * @return: java.lang.String
     * @author: tran
     * @date: 2021/5/20
     */
    public synchronized static  String getID(){
        return SNOWFLAKE.nextId()+"";
    }
}
