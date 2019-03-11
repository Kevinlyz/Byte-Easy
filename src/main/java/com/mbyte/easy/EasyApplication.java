package com.mbyte.easy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 */
@SpringBootApplication
@MapperScan("com.mbyte.easy.**.mapper")
public class EasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyApplication.class, args);
    }

}
