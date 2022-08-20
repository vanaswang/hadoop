package com.atguigu.springboot.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * SpringBoot的启动类
 */
//用于指定Mapper接口的位置
@MapperScan(basePackages = "com.atguigu.mapper")
// 用户扫描指定包以及子包下的带注解的类.通过类上的注解来明确当前类的作用是什么
@ComponentScan(value="com.atguigu")
@SpringBootApplication
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

}
