package cn.actional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @auther actional
 * @create 2020-05-18
 */
@SpringBootApplication
@MapperScan("cn.actional.blog.mapper")
public class ActionalBlogApplication {


    public static void main(String[] args) {
            SpringApplication.run( ActionalBlogApplication.class,args);
    }
}
