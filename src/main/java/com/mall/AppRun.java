package com.mall;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @Author maiBangMin
 * @Description [启动类]
 * @Date 4:28 下午 2020/11/28
 * @Version 1.0
 **/
@SpringBootApplication
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }

}
