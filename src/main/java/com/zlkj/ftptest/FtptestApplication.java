package com.zlkj.ftptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FtptestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtptestApplication.class, args);
    }

}
