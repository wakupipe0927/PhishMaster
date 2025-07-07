package com.example.demo0706;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demo0706", "com.example.demo0706.repository" })
public class Demo0706Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo0706Application.class, args);
    }

}
