package team.tran.colleges.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: DataUtils
 * @description: TODO  返回给前端 工具类  | pom版本 8.1 | redis 版本 3.2.1 支持最新版
 * @author: tran
 * @date: 2021/5/13
 **/
@Component
public final class DataUtil {


    private DataUtil() {
    }

    /**
     * @param: code  状态码
     * @param: msg  提示信息
     * @param: data 交互数据
     * @description: TODO 返回给前端的数据的封装
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: tran
     * @date: 2021/5/14
     */
    public static Map<String,Object> printf(Integer code, String msg,Object data){
        Map<String, Object> map = printf(code, msg);
        map.put("data",data);
        return map;
    }

    public static Map<String,Object> printf(Integer code,Long count, String msg,Object data){
        Map<String, Object> map = printf(code, msg, data);
        map.put("count",count);
        return map;
    }

    public static Map<String,Object> printf(Integer code,Integer count, String msg,Object data){
        Map<String, Object> map = printf(code, msg, data);
        map.put("count",count);
        return map;
    }

    public static Map<String,Object> printf(Integer code, String msg){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }


    /**
     * @param: data 时间
     * @description: TODO 时间转换
     * @return: java.lang.String
     * @author: tran
     * @date: 2021/6/1
     */
    public static  String dataTime(String data){
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(data));
    }





    /**
     * @param: page
     * @param: size
     * @description: TODO 页数修改
     * @return: void
     * @author: tran
     * @date: 2021/6/1
     */
    public static void updatePage(Integer page,Integer size){
        // 验证参数
        if (page==null||page==0)
            page=1;
        if (size==null)
            size=20;
        page=(page-1)*size;
    }

}
