package team.tran.colleges;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: CollegesApplication
 * @description: TODO springboot的启动类
 * @author: tran
 * @date: 2021/5/31
 **/

@SpringBootApplication
@MapperScan("team.tran.colleges.*.dao")
public class CollegesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollegesApplication.class, args);
    }
}
